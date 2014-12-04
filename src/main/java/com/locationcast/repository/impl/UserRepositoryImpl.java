/**
 * 
 */
package com.locationcast.repository.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.locationcast.domain.User;
import com.locationcast.repository.UserRepository;

/**
 * @author Khoa
 *
 */
@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository{

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
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

	public User findUserByUserName(String userName, User.Fields field){
		Query query = new Query();
		query.addCriteria(Criteria.where(field.getFieldName()).is(userName));
		User user = mongoOperation.findOne(query, User.class);
		return user;
	}


	@Override
	public User findUserById(long id, String idKey) {
		Query query = new Query();
		query.addCriteria(Criteria.where(idKey).is(id));
		User user = mongoOperation.findOne(query, User.class);
		return user;
	}

	
	@Override
	public void onApplicationEvent(MongoMappingEvent<User> event) {
	
		if(event instanceof AfterSaveEvent){
			
			event = (AfterSaveEvent<User>) event;
			User user = event.getSource();
			
			System.out.println(user.toString());
		}
		 
	}

	

}
