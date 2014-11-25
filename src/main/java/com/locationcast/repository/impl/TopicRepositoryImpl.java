/**
 * 
 */
package com.locationcast.repository.impl;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.Topic;
import com.locationcast.factory.TopicFactory;
import com.locationcast.repository.TopicRepository;



/**
 * @author Khoa
 *
 */
@Repository
public class TopicRepositoryImpl implements TopicRepository {

	private static final Logger logger = LoggerFactory.getLogger(TopicRepositoryImpl.class);
	
	@Autowired
    @Qualifier("locationcastdb")
	MongoOperations mongoOperation;
	
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
}
