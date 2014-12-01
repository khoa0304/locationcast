package com.locationcast.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.testng.annotations.BeforeSuite;

import com.locationcast.mongodb.config.MongoConfig;

import static org.testng.Assert.*;
public abstract class AbstractMongoDBReposTest {

	@Autowired
	protected ApplicationContext applicationContext;

	protected MongoOperations mongoOperation = null;
	
	@BeforeSuite
	public void initSuite(){
		
		applicationContext = new AnnotationConfigApplicationContext(
				MongoConfig.class);
		
		assertNotNull(applicationContext);
		
		mongoOperation = (MongoOperations) applicationContext
				.getBean("testTemplateDB");
		
		assertNotNull(mongoOperation);
	}
}
