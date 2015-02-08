package com.locationcast.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service(value="conversationRealtimePublisherService")
@PropertySource("classpath:kafka.properties")
public class ConversationRealtimePublisherService extends AbstractKafkaConfig{

	private static final Logger logger = LoggerFactory
			.getLogger(ConversationRealtimePublisherService.class);

	private ConsumerConnector consumerConnector;
	
	private ExecutorService executorService = Executors
			.newSingleThreadExecutor();

	

	@PostConstruct
	public void startConsumer() throws InterruptedException {
		

		String zookeperList = env.getProperty(KAFKA_ZOOKEEPER_LIST_PROPERTY);
		logger.info("Read zookeper list {}",zookeperList);
		
		Properties properties = new Properties();
		properties.put("zookeeper.connect", zookeperList);
		properties.put("group.id", getClass().getName());
		properties.put("auto.offset.reset", "smallest");
		
		ConversationRealtimePublisherConsumer consumer = new ConversationRealtimePublisherConsumer(consumerConnector, properties,env);
	
		consumerConnector = consumer.getConsumerConnector();
		executorService.execute(consumer);
		logger.info("Starting new Conversation Publisher consumer thread");
	
	}

	@PreDestroy
	public void shutdownConsumer() {
		logger.info("Start shutting down Conversation Publisher consumer thread");
		consumerConnector.shutdown();
		executorService.shutdown();
	}

}
