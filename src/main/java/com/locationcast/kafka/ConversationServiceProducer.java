package com.locationcast.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.locationcast.domain.Conversation;
import static com.locationcast.constant.ConversationServiceConstant.*;

@Service
@PropertySource("classpath:kafka.properties")
public class ConversationServiceProducer extends AbstractKafkaConfig{

	
	private static final Logger logger = LoggerFactory.getLogger(ConversationServiceProducer.class);
	
	private kafka.javaapi.producer.Producer<String, Conversation> producer = null;
	
	private Properties properties = new Properties();
	
	private ProducerConfig producerConfig;
	
	
	@PostConstruct
	public void initConversationProducer(){
		
		String brokerHostPortList = env.getProperty(KAFKA_BROKER_LIST_PROPERTY);
		logger.info("Read kafka broker list {}",brokerHostPortList);
		
		properties.put("metadata.broker.list", brokerHostPortList);
		properties.put("serializer.class",
				"com.locationcast.kafka.ConversationJsonEncoder");

		producerConfig = new ProducerConfig(properties);
		
		producer = new kafka.javaapi.producer.Producer<String, Conversation>(producerConfig);
		
		logger.info("Finished intializing Kafka producer.");
	}
	
	@PreDestroy
	public void closeProducer(){
	
		if(producer != null){
			
			producer.close();
			
			logger.info("Finished closing Kafka producer.");
		}
	}
	
	public void publishConversation(Conversation conversation){
		
		KeyedMessage<String, Conversation> message = new KeyedMessage<String, Conversation>(
					ConversationTopic, conversation);
			
		producer.send(message);
		
		logger.info("Finished sending Conversation message {}",message.message().getContent().getContentString());
	}
}
