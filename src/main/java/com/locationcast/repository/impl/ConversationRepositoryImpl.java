/**
 * 
 */
package com.locationcast.repository.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Term;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.core.query.Term.Type;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.Conversation;
import com.locationcast.event.ConversationEvent;
import com.locationcast.event.ConversationEvent.EventType;
import com.locationcast.event.ConversationEventDispatcher;
import com.locationcast.repository.ConversationRepository;

/**
 * @author Khoa
 *
 */
@Repository
public class ConversationRepositoryImpl extends AbstractRepository<Conversation> implements ConversationRepository{

	@Autowired
	ConversationEventDispatcher eventDispatcher;
	
	@PostConstruct
	public void createCollectionAndSetIndex(){
		if(!mongoOperation.collectionExists(Conversation.class)){
			mongoOperation.createCollection(Conversation.class);
		}
		
		String name = Conversation.class.getSimpleName();
		DefaultIndexOperations indexOp = new DefaultIndexOperations(
				mongoOperation,"Conversation" );
		GeospatialIndex geoIndex = new GeospatialIndex(Conversation.FieldNames.LongAndLat.getFieldName());
		geoIndex.typed(GeoSpatialIndexType.GEO_2D);
		indexOp.ensureIndex(geoIndex);
		
	}
	
	public void insertConversation(Conversation conversation){
		mongoOperation.insert(conversation);
	}


	public void findConversationByPoster(){
		
	}
	
    public List<Conversation> findConverstaionByContentKeyWords(String[] words){
    	TextCriteria textCriteria = TextCriteria.forDefaultLanguage();
    	textCriteria.matchingAny(words);
    	//Query query = TextQuery.queryText(textCriteria).sortByScore().with(new PageRequest(0, size));
    	Query query = TextQuery.queryText(textCriteria).sortByScore();
    	
    	List<Conversation> conversationList = mongoOperation.find(query, Conversation.class);
    	return conversationList;
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
