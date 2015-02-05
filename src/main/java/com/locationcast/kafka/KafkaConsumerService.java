package com.locationcast.kafka;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.locationcast.repository.ConversationRepository;

@Service
@PropertySource("classpath:kafka.properties")
public class KafkaConsumerService extends AbstractKafkaConfig{

	private static final Logger logger = LoggerFactory
			.getLogger(KafkaConsumerService.class);

	@Autowired
	ConversationRepository conversationRepos;

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
		
		
		MongodbConversationServiceConsumer consumer = 
				new MongodbConversationServiceConsumer(conversationRepos,properties);
		consumerConnector = consumer.getConsumerConnector();
		executorService.execute(consumer);
		logger.info("Starting new Mongodb consumer thread");
	}

	@PreDestroy
	public void shutdownConsumer() {
		logger.info("Start shutting down Mongodb consumer");
		consumerConnector.shutdown();
		executorService.shutdown();
	}

}
