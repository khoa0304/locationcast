/**
 * 
 */
package com.locationcast.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.Conversation;
import com.locationcast.domain.Topic;
import com.locationcast.factory.TopicFactory;
import com.locationcast.repository.TopicRepository;



/**
 * @author Khoa
 *
 */
@Repository
public class TopicRepositoryImpl extends AbstractRepository<Topic> implements TopicRepository {

	private static final Logger logger = LoggerFactory.getLogger(TopicRepositoryImpl.class);
	
	
	
	@Autowired
	TopicFactory topicFactory;
	
	@PostConstruct
	public void initTopicCollections(){
	
		List<Topic> topicList = topicFactory.getListOfAvailableTopic();
		
		for(Topic topic :topicList){
			String topicCollectionName = topic.getTopicName();
			if(!mongoOperation.collectionExists(topicCollectionName)){
				logger.info("Create Topic Collection: "+topicCollectionName);
				mongoOperation.createCollection(topicCollectionName);
			}
		}
	}
	
	
	public List<Topic> getAvailableTopicLits(){
		
		List<Topic> topicList = topicFactory.getListOfAvailableTopic();
		
//		for(Topic topic :topicList){
//			
//			 mongoOperation.find(topic.getTopicName());
//		}
//		List<Topic> topicList =  mongoOperation.findAll(Topic.class);
		
		return topicList;
	}

	/* (non-Javadoc)
	 * @see com.locationcast.repository.impl.AbstractRepository#onApplicationEvent(org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent)
	 */
	@Override
	public void onApplicationEvent(MongoMappingEvent<Topic> event) {
		// TODO Auto-generated method stub
		
	}
}
