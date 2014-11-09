package com.locationcast.mongodb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;

@Configuration
@EnableMongoRepositories
@ComponentScan(basePackages={"com.locationcast.repository"})
@PropertySource("classpath:application.properties")
public class MongoConfig extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "location";
	}

	@Override
	@Bean
	public MongoClient mongo() throws Exception {
		MongoClient client = new MongoClient((new ServerAddress("localhost",27017)));
		client.setWriteConcern(WriteConcern.SAFE);
		
		return client;
	}

	@Override
	protected String getMappingBasePackage() {
		return "com.locationcast.domain";
	}

	// ---------------------------------------------------- MongoTemplate

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongo(), getDatabaseName());
	}

}
