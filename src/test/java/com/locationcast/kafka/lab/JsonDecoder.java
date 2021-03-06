package com.locationcast.kafka.lab;

import java.io.IOException;

import kafka.serializer.Decoder;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.locationcast.domain.User;

public class JsonDecoder  implements Decoder<Object>{

	
	private static final Logger logger = LoggerFactory
			.getLogger(JsonDecoder.class);
	
	
	@Override
	public Object fromBytes(byte[] arg0) {
		   ObjectMapper objectMapper = new ObjectMapper();
	        try {
	        	Object obj = objectMapper.readValue(arg0,User.class);
	            return obj;
	        } catch (JsonProcessingException e) {
	        	logger.error(e.toString());
	        } catch (IOException e) {
	        	logger.error(e.toString());
			}
		return null;
	}

}
