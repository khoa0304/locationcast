package com.locationcast.kafka;

import kafka.javaapi.consumer.ConsumerConnector;

public abstract class AbstractKafkaConsumer {

	protected ConsumerConnector consumerConnector;
	
	
	public ConsumerConnector getConsumerConnector(){
		return this.consumerConnector;
	}
	
}
