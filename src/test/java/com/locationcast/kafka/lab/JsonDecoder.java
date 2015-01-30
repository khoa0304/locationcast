package com.locationcast.kafka.lab;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.locationcast.domain.User;

import kafka.serializer.Decoder;

public class JsonDecoder implements Decoder<Object>{

	@Override
	public Object fromBytes(byte[] arg0) {
		   ObjectMapper objectMapper = new ObjectMapper();
	        try {
	        	User user = objectMapper.readValue(arg0, User.class);
	            return user;
	        } catch (JsonProcessingException e) {
	           System.out.println(e.toString());
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

}
