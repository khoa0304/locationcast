package com.locationcast.kafka;

import static com.locationcast.constant.ConversationServiceConstant.ConversationTopic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locationcast.domain.Conversation;
import com.locationcast.repository.ConversationRepository;

public class ConversationMongodbConsumer extends AbstractKafkaConsumer implements Runnable{

	private static final Logger logger = LoggerFactory
			.getLogger(ConversationMongodbConsumer.class);

	
	private ConversationRepository conversationRepos;
	

	public ConversationMongodbConsumer(ConversationRepository conversationRepository,Properties properties) {
		
		ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		
		this.consumerConnector = Consumer
				.createJavaConsumerConnector(consumerConfig);
		this.conversationRepos = conversationRepository;
		
		Thread.currentThread().setName(getClass().getName());
		
		logger.info("Finished initializing {}",getClass().getName());
	}


	@Override
	public void run() {
	
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
		
		logger.info("Returned from consumer connector thread ");
	}
	
}
