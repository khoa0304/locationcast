package com.locationcast.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locationcast.domain.Conversation;
import com.locationcast.repository.ConversationRepository;


public class MongodbConversationServiceConsumer extends AbstractKafkaConfig implements Runnable{

	private static final Logger logger = LoggerFactory
			.getLogger(MongodbConversationServiceConsumer.class);

	private ConsumerConnector consumerConnector;

	private ConversationRepository conversationRepos;
	
	public MongodbConversationServiceConsumer(ConversationRepository conversationRepository,Properties properties) {
		
		ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		this.consumerConnector = Consumer
				.createJavaConsumerConnector(consumerConfig);
		this.conversationRepos = conversationRepository;
		logger.info("Finished initializing {}",getClass().getName());
	}


	@Override
	public void run() {

		Thread.currentThread().setName(getClass().getName());

		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(ConversationTopic, new Integer(1));
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = this.consumerConnector
				.createMessageStreams(topicCountMap);
		KafkaStream<byte[], byte[]> stream = consumerMap.get(ConversationTopic)
				.get(0);
		
		ConsumerIterator<byte[], byte[]> consumerIterator = stream.iterator();

		ConversationJsonDecoder decoder = new ConversationJsonDecoder();
	
		logger.info("Consumer conversation thread started {}");
				
		while (consumerIterator.hasNext()) {

			Conversation conversation = (Conversation) decoder.fromBytes(consumerIterator.next()
					.message());
			logger.info("Consume conversation {}", conversation.getContent().getContentString());
			
			conversationRepos.insertConversation(conversation);
			
			logger.info("Finished inserting conversation {}", conversation.getContent().getContentString());
		}
		
		logger.info("Returned from consumer connector thread {}",Thread.currentThread().getName());
	}
	
	public ConsumerConnector getConsumerConnector(){
		return this.consumerConnector;
	}
}
