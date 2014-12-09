/**
 * 
 */
package com.locationcast.repository;

import static com.locationcast.test.data.UserTestData.email;
import static com.locationcast.test.data.UserTestData.nickName;
import static com.locationcast.test.data.UserTestData.password;
import static com.locationcast.test.data.UserTestData.userName;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.locationcast.domain.Content;
import com.locationcast.domain.Conversation;
import com.locationcast.domain.Location;
import com.locationcast.domain.Poster;
import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;
import com.locationcast.facade.UserFacade;
import com.locationcast.test.data.LocationTestData;
import com.locationcast.util.GeoLocationConstant;
import com.locationcast.util.LocationCastConstant;

import static org.testng.Assert.*;
/**
 * @author Khoa
 *
 */
public class ConversationRepositoryTest  extends AbstractMongoDBReposTest {

	@Autowired
    ConversationFacade conversationFacade;
	
	@Autowired
	UserFacade userFacade;
	
	@Before
	public void initClass(){

		if(mongoOperation.collectionExists(Conversation.class)){
			mongoOperation.dropCollection(Conversation.class);
			mongoOperation.createCollection(Conversation.class);
		}
		
		DefaultIndexOperations indexOp = new DefaultIndexOperations(
				mongoOperation,Conversation.class.getSimpleName() );
		GeospatialIndex geoIndex = new GeospatialIndex(Conversation.FieldNames.LongAndLat.getFieldName());
		geoIndex.typed(GeoSpatialIndexType.GEO_2D);
		indexOp.ensureIndex(geoIndex);
		
		TextIndexDefinitionBuilder textIndexBuilder = new TextIndexDefinitionBuilder();
		textIndexBuilder.named("content");
		textIndexBuilder.onField("content.contentString");
		TextIndexDefinition textDef = textIndexBuilder.build();
		indexOp.ensureIndex(textDef);
	}
	
	@Test
	public void testCreateConversation() throws InvalidDomainModelException{
		
		User user = new User();
		user = user.setUserName(userName).setAliasName(nickName).setEmail(email).setPassword(password);
		userFacade.createUser(user);
		
		Poster poster = user.getPoster();
		double[] longAndLat = LocationTestData.getHomeLongitudeAndLatitude();
		Content content = new Content();
		content.setText("This is my first post. I hope it will go far!!!!");
		Conversation conversation = new Conversation(poster,longAndLat);
		
		conversation.setContent(content);
		conversationFacade.createConversation(conversation);
		
		String[] words = {"post","far"};
		
		List<Conversation> list = conversationFacade.findConverstaionByContentKeyWords(words);
		
		assertEquals(list.size(), 1);
		
		Criteria criteria = new Criteria("longAndLat").near(
				new Point(longAndLat[0], longAndLat[1])).maxDistance(
				GeoLocationConstant.getInMile(0.0001));
		Query query = new Query(criteria);
		List<Conversation> locationList = mongoOperation
				.find(query, Conversation.class);
		assertEquals(locationList.size(), 1);
	}
}
