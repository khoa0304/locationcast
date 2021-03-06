package com.locationcast.kafka.lab;

import java.io.IOException;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonEncoder implements Encoder<Object> {

	private static final Logger logger = LoggerFactory
			.getLogger(JsonEncoder.class);

	public JsonEncoder(VerifiableProperties verifiableProperties) {}

	@Override
	public byte[] toBytes(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			
			logger.debug("Write JSON object as byte of String {}",object.toString());
			return objectMapper.writeValueAsString(object).getBytes();
			
		} catch (JsonProcessingException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		
		return "".getBytes();
	}

}
