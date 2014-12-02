package com.locationcast.repository;

import static org.testng.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.locationcast.mongodb.config.MongoConfig;
public abstract class AbstractMongoDBReposTest {

	protected static ApplicationContext applicationContext;

	protected static MongoOperations mongoOperation = null;
	
	@BeforeClass
	public static void initSuite(){
		
		applicationContext = new AnnotationConfigApplicationContext(
				MongoConfig.class);
		
		assertNotNull(applicationContext);
		
		mongoOperation = (MongoOperations) applicationContext
				.getBean("testTemplateDB");
		
		assertNotNull(mongoOperation);
	}
}
