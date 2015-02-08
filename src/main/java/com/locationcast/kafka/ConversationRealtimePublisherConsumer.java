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
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import com.locationcast.constant.LocationCastConstant;
import com.locationcast.domain.Conversation;

public class ConversationRealtimePublisherConsumer extends AbstractKafkaConsumer implements Runnable{

	private static final Logger logger = LoggerFactory
			.getLogger(ConversationRealtimePublisherConsumer.class);

	private String URL = null;
	
	public static final String URL_FORMAT = "http://%s:%s/%s%s%s";
	
	private RestTemplate restTemplate = null;

	public ConversationRealtimePublisherConsumer(ConsumerConnector consumerConnector, Properties properties,Environment env) {
	
		this.consumerConnector = consumerConnector;
		
    	ConsumerConfig consumerConfig = new ConsumerConfig(properties);
		
		this.consumerConnector = Consumer
				.createJavaConsumerConnector(consumerConfig);
		
		restTemplate = new RestTemplate();
		Thread.currentThread().setName(getClass().getName());
		
		String hostName = env.getProperty(AbstractKafkaConfig.HOST_NAME);
		String port = env.getProperty(AbstractKafkaConfig.HOST_PORT);
		String context = env.getProperty(AbstractKafkaConfig.APPLICATION_CONTEXT);
		
	    URL =  String.format(URL_FORMAT, hostName,port,context,
	    		LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_SERVICE_PATH,
	    		LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_PUBLISH_PATH);
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
	
		logger.info("Publishing conversation thread started to URL {}",URL);
				
		while (consumerIterator.hasNext()) {

			Conversation conversation = (Conversation) decoder.fromBytes(consumerIterator.next()
					.message());
			
			logger.info("Consume conversation {}", conversation.getContent().getContentString());
			
			HttpEntity<Conversation> conversationEntity = getHttpHeadersWithUserCredentials(conversation);
			
			try{
				
				ResponseEntity<Conversation> response = restTemplate.exchange(URL,HttpMethod.POST, conversationEntity, Conversation.class);	
	
				logger.info("Finished publishing conversation with statuc code {}", response.getStatusCode().name());
			}catch(Exception e){
				
				logger.error("Error in publishing conversation to URL {} with exception {} ",URL,e.toString());
			}
		}
		
	
		
		logger.info("Publishing conversation thread ended ");
		
	}
	
	private  HttpEntity<Conversation> getHttpHeadersWithUserCredentials(Conversation conversation) {
   
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		String username = "ConversationRealtimePublisherConsumer";
		String password = "welcome";

		String combinedUsernamePassword = username + ":" + password;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String(base64Token);
		
		// adding Authorization header for HTTP Basic authentication
		headers.add("Authorization", "Basic  " + base64EncodedToken);

		// Pass the new person and header
	    HttpEntity<Conversation> entity = new HttpEntity<Conversation>(conversation, headers);
				
		return entity;
	}
}
