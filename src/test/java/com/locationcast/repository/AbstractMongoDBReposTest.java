package com.locationcast.repository;

import static org.testng.Assert.assertNotNull;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.locationcast.mongodb.config.MongoConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public abstract class AbstractMongoDBReposTest {

	protected static ApplicationContext applicationContext;

	protected static MongoOperations mongoOperation = null;
	
	@BeforeClass
	public static void initSuite(){
		
		applicationContext = new AnnotationConfigApplicationContext(
				MongoConfig.class);
		
		assertNotNull(applicationContext);
		
		mongoOperation = (MongoOperations) applicationContext
				.getBean("locationcastdb");
		
		assertNotNull(mongoOperation);
		
		
		
		
	}
	
//	@AfterClass
//	public static void cleanupCollection(){
//
//		Set<String> collectionSet = mongoOperation.getCollectionNames();
//		
//		for(String collectionName : collectionSet){
//			
//			mongoOperation.dropCollection(collectionName);
//			System.out.println("Dropped collection: "+collectionName);
//		}
//	}
	
}
