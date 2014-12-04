/**
 * 
 */
package com.locationcast.repository.impl;

import javax.annotation.PostConstruct;

import org.springframework.data.mongodb.core.DefaultIndexOperations;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.Conversation;
import com.locationcast.domain.Location;
import com.locationcast.repository.ConversationRepository;

/**
 * @author Khoa
 *
 */
@Repository
public class ConversationRepositoryImpl extends AbstractRepository<Conversation> implements ConversationRepository{

	
	@PostConstruct
	public void createCollectionAndSetIndex(){
		if(!mongoOperation.collectionExists(Conversation.class)){
			mongoOperation.createCollection(Conversation.class);
		}
		
		DefaultIndexOperations indexOp = new DefaultIndexOperations(
				mongoOperation, Conversation.class.getSimpleName());
		GeospatialIndex geoIndex = new GeospatialIndex(
				Location.AttributeNames.point.getName());
		geoIndex.typed(GeoSpatialIndexType.GEO_2D);
		indexOp.ensureIndex(geoIndex);
	}
	
	public void insertConversation(Conversation conversation){
		mongoOperation.insert(conversation);
	}

	/* (non-Javadoc)
	 * @see com.locationcast.repository.impl.AbstractRepository#onApplicationEvent(org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent)
	 */
	@Override
	public void onApplicationEvent(MongoMappingEvent<Conversation> event) {
		// TODO Auto-generated method stub
		
	}
}
