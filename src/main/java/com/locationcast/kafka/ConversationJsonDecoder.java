package com.locationcast.kafka;

import java.io.IOException;

import kafka.serializer.Decoder;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locationcast.domain.Conversation;

public class ConversationJsonDecoder implements Decoder<Conversation>{

	
	private static final Logger logger = LoggerFactory
			.getLogger(ConversationJsonDecoder.class);
	
	
	@Override
	public Conversation fromBytes(byte[] arg0) {
		   ObjectMapper objectMapper = new ObjectMapper();
	        try {
	        	Conversation obj = objectMapper.readValue(arg0,Conversation.class);
	            return obj;
	        } catch (JsonProcessingException e) {
	        	logger.error(e.toString());
	        } catch (IOException e) {
	        	logger.error(e.toString());
			}
		return null;
	}

}
