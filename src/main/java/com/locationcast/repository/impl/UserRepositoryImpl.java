/**
 * 
 */
package com.locationcast.repository.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.User;
import com.locationcast.repository.UserRepository;

/**
 * @author Khoa
 *
 */
@Repository
public class UserRepositoryImpl implements UserRepository,ApplicationListener<MongoMappingEvent<User>>{

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
    @Qualifier("locationcastdb")
	MongoOperations mongoOperation;
	
	@PostConstruct
	public void createCollectionAndSetIndex(){
		if(!mongoOperation.collectionExists(User.class)){
			mongoOperation.createCollection(User.class);
		}
	}
	
	public void insertUser(User user){
		logger.info("Insert user %s", user);
		mongoOperation.insert(user);
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(MongoMappingEvent<User> event) {
		 System.out.println(event.toString());
	}
}
