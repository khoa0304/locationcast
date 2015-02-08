package com.locationcast.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Property is defined in kafka.properties 
 * @author khoa
 *
 */
@Service
public abstract class AbstractKafkaConfig {

	@Autowired
	protected Environment env;

	public static final String KAFKA_BROKER_LIST_PROPERTY = "kafka.broker.list";
	
	public static final String KAFKA_ZOOKEEPER_LIST_PROPERTY = "kafka.zookeeper.list";
	
	public static final String HOST_NAME = "host.name";
	
	public static final String HOST_PORT = "host.port";
	
	public static final String APPLICATION_CONTEXT = "application.context"; 

}
