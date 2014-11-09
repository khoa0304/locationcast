package com.locationcast.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.testng.Assert.assertNotNull;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.locationcast.mongodb.config.MongoConfig;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MongoConfig.class})

public class LocationRepositoryTest {

//	@Rule
//    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");

	// nosql-unit requirement
	@Autowired private ApplicationContext applicationContext;
	
	private LocationRepository locationRepository;

	@BeforeSuite
	public void init(){
		applicationContext= new AnnotationConfigApplicationContext(MongoConfig.class);
	}
	
	@Test
	public void testInit(){
		assertNotNull(applicationContext);
		
		locationRepository = applicationContext.getBean(LocationRepository.class);
		assertNotNull(locationRepository);
		locationRepository.insertLocaiton(3.333, 4.444);
	}
}
