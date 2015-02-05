package com.locationcast.kafka;

import java.io.IOException;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locationcast.domain.Conversation;

public class ConversationJsonEncoder implements Encoder<Conversation> {

	private static final Logger logger = LoggerFactory
			.getLogger(ConversationJsonEncoder.class);

	public ConversationJsonEncoder(VerifiableProperties verifiableProperties) {}

	@Override
	public byte[] toBytes(Conversation object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			logger.debug("Write JSON object as byte of String {}",object.toString());
			byte[] byteArray = objectMapper.writeValueAsString(object).getBytes();
			
			return byteArray;
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		
		return "".getBytes();
	}

}
