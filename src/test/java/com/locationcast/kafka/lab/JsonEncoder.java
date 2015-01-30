package com.locationcast.kafka.lab;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class JsonEncoder implements Encoder<Object>{
	 
	public JsonEncoder(VerifiableProperties verifiableProperties) {
	        /* This constructor must be present for successful compile. */
    }

	 @Override
	    public byte[] toBytes(Object object) {
	        ObjectMapper objectMapper = new ObjectMapper();
	        try {
	            return objectMapper.writeValueAsString(object).getBytes();
	        } catch (JsonProcessingException e) {
	           
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return "".getBytes();
	    }

}
