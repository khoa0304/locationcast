/**
 * 
 */
package com.locationcast.repository;

import static com.locationcast.test.data.UserTestData.email;
import static com.locationcast.test.data.UserTestData.nickName;
import static com.locationcast.test.data.UserTestData.password;
import static com.locationcast.test.data.UserTestData.userName;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;

import com.locationcast.domain.Content;
import com.locationcast.domain.Conversation;
import com.locationcast.domain.Poster;
import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;
import com.locationcast.facade.UserFacade;
import com.locationcast.test.data.LocationTestData;
import com.locationcast.util.UserUtil;
/**
 * @author Khoa
 *
 */
public class ConversationRepositoryTest  extends AbstractMongoDBReposTest {

	@Autowired
    ConversationFacade conversationFacade;
	
	@Autowired
	UserFacade userFacade;
	
	@Autowired
	ConversationRepository conversationRepos;
	
	
	HttpServletRequest request;
	
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
		textIndexBuilder.named(Conversation.FieldNames.Content.getFieldName());
		textIndexBuilder.onField(Conversation.FieldNames.ConentString.getFieldName());
		TextIndexDefinition textDef = textIndexBuilder.build();
		indexOp.ensureIndex(textDef);
	}
	
	@Before
	public void initVariables(){
		
       request = EasyMock.createMock(HttpServletRequest.class);
       EasyMock.expect(request.getRemoteAddr()).andReturn("localhost").anyTimes();
       EasyMock.replay(request);
	}
	
	@After
	public void tearDown(){
		
		EasyMock.verify(request);
	
	}
	@Test
	public void testCreateConversation() throws InvalidDomainModelException, InterruptedException{
		
		final String contentString = "This is my first post. I hope it will go far 12-08-2014!!!!";
		
		User user = new User();
		user = user.setUserName(userName).setAliasName(nickName).setEmail(email).setPassword(password);
		userFacade.registerUser(user);
		
		Poster poster = UserUtil.getPoster(user.getAliasName(),user.getId());
		double[] longitudeAndLatitude = LocationTestData.getHomeLongitudeAndLatitude();
		
		Content content = new Content();
		content.setContentString(contentString);
		
		Conversation conversation = new Conversation(poster,longitudeAndLatitude);
		conversation.setContent(content);
		
		
		conversationFacade.createConversation(request.getRemoteAddr(),conversation);
		
		TimeUnit.SECONDS.sleep(2);
		
		
		String[] words = {"post","far"};
		
		List<Conversation> list = conversationFacade.findConverstaionByContentKeyWords(words);
		
		assertEquals(list.size(), 1);
		assertEquals(list.get(0).getContent().getContentString(),contentString);
		
		List<Conversation> listByGeoQuery = conversationFacade.findConversationsByCoordinates(longitudeAndLatitude,1.0d);
		assertEquals(listByGeoQuery.size(), 1);
		assertEquals(list.get(0).getContent().getContentString(),contentString);
	}
	
	
	@Test
	public void testQueryConversationByCoordinates() throws InvalidDomainModelException, IOException{
		
		int totalLineFromSampleText = 17;///text/SampleText.txt
		
		double[][] coordinatesDataSet = LocationTestData.getLongitudeLatitudeDataset(totalLineFromSampleText,0.001);
		
		int totalConversation = createCoversation("/text/SampleText.txt",coordinatesDataSet);
		
		assertEquals(totalConversation, totalLineFromSampleText);
		
		int counter = 0;
		for(double[] longAndLat : coordinatesDataSet){
			
			counter ++;
			
			List<Conversation> list =  conversationRepos.findConversationByLongitudeAndLatitude(longAndLat,328.0d);
		
		    int size = list.size();
		    System.out.println("\n\n=============================\n\n");
		    String format = String.format(" Longitude %f  - Lat %f - counter %d - size %d ", new Object[]{longAndLat[0],longAndLat[1],counter,size});
		    System.out.println(format);
	
		    for(Conversation conversation : list ){
		    	double[] location = conversation.getLongAndLat();
		    	String format2 = String.format(" Long %f - Lat %f", location[0],location[1]);
		        System.out.println(">>>> " +format2);
		    }
		    
		    System.out.println("\n\n=============================\n\n");
		    
		}
	}
	
	private int createCoversation(String fileLocation, double[][] coordinatesDataSet) throws InvalidDomainModelException, IOException{

		InputStream is = this.getClass().getResourceAsStream(fileLocation);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String strLine;
	    int i = 0;
		while ((strLine = br.readLine()) != null)   {
	
			Content content = new Content();
			content.setContentString(strLine);
			
			Conversation conversation = new Conversation(coordinatesDataSet[i++]);
			conversation.setContent(content);
			
			
			conversationFacade.createConversation(request.getRemoteAddr(),conversation);
		}
		return i;
	}
	
}
