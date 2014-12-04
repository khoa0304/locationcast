/**
 * 
 */
package com.locationcast.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;

/**
 * @author Khoa
 *
 */
public abstract class AbstractRepository<T> implements ApplicationListener<MongoMappingEvent<T>>{
	
	@Autowired
    @Qualifier("locationcastdb")
	protected MongoOperations mongoOperation;

	public abstract void onApplicationEvent(MongoMappingEvent<T> event);
}
