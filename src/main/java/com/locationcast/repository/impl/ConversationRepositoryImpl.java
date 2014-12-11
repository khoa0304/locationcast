/**
 * 
 */
package com.locationcast.repository.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;
import org.springframework.data.mongodb.core.index.TextIndexDefinition.TextIndexDefinitionBuilder;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.AbstractDomainModel;
import com.locationcast.domain.Conversation;
import com.locationcast.event.ConversationEvent;
import com.locationcast.event.ConversationEvent.EventType;
import com.locationcast.event.ConversationEventDispatcher;
import com.locationcast.repository.ConversationRepository;
import com.locationcast.util.GeoLocationConstant;

/**
 * @author Khoa
 *
 */
@Repository
public class ConversationRepositoryImpl extends AbstractRepository<Conversation>
					implements ConversationRepository{

	@Autowired
	ConversationEventDispatcher eventDispatcher;
	
	@PostConstruct
	public void createCollectionAndSetIndex(){
	
		if(!mongoOperation.collectionExists(Conversation.class)){
			mongoOperation.createCollection(Conversation.class);
		}
		
		setIndexFields();
	}
	
    private void setIndexFields(){
		
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
	
	public void insertConversation(Conversation conversation){
		mongoOperation.insert(conversation);
	}

	//@org.springframework.data.mongodb.repository.Query(value = "{ 'userId' : ?0, 'questions.questionID' : ?1 }", fields = "{ 'questions.questionID' : 1 }")
	public void findConversationByPoster(){
		
	}
	
	
	
    public List<Conversation> findConverstaionByContentKeyWords(String[] words){
    	TextCriteria textCriteria = TextCriteria.forDefaultLanguage();
    	textCriteria.matchingAny(words);
    	//Query query = TextQuery.queryText(textCriteria).sortByScore().with(new PageRequest(0, size));
    	Query query = TextQuery.queryText(textCriteria).sortByScore();
    	
    	final List<Conversation> conversationList = mongoOperation.find(query, Conversation.class);
    	return conversationList;
    }
	
    public List<Conversation> findConversationByLongitudeAndLatitude(double[] longitudeAndLatitude){
    	
    	final Criteria criteria = new Criteria(Conversation.FieldNames.LongAndLat.getFieldName()).near(
				new Point(longitudeAndLatitude[0], longitudeAndLatitude[1])).maxDistance(
				GeoLocationConstant.getInMile(GeoLocationConstant.defaultMinConversationDistance));
	
    	Query query = new Query(criteria);
		
		Sort sortByIdDesc = new Sort(Sort.Direction.DESC, AbstractDomainModel.IndexFieldNames.id.getIdKey());
		
		query.with(sortByIdDesc);
		
		List<Conversation> listByGeoQuery = mongoOperation
				.find(query, Conversation.class);
		
		return listByGeoQuery;
    }
    
    
	@Override
	public void onApplicationEvent(MongoMappingEvent<Conversation> event) {
		
		if(!(event.getSource() instanceof Conversation)){
			return;
		}

		if(event instanceof AfterSaveEvent){
			
			event = (AfterSaveEvent<Conversation>) event;
			Conversation conversation = event.getSource();
			
			ConversationEvent conversationEvent = new ConversationEvent(conversation, EventType.CREATED);
			eventDispatcher.dispatchConverationChangedEvent(conversationEvent);
		}
		
	}
}
