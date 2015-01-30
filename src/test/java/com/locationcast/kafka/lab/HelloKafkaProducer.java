package com.locationcast.kafka.lab;


import static com.locationcast.test.data.UserTestData.email;
import static com.locationcast.test.data.UserTestData.nickName;
import static com.locationcast.test.data.UserTestData.password;
import static com.locationcast.test.data.UserTestData.userName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import com.locationcast.domain.Poster;
import com.locationcast.domain.User;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
* Created by user on 8/4/14.
*/
public class HelloKafkaProducer {
   final static String TOPIC = "javatest";


   public static void main(String[] argv) throws InterruptedException{
 
       Properties properties = new Properties();
       properties.put("metadata.broker.list","localhost:9092");
       //properties.put("serializer.class","kafka.serializer.StringEncoder");
       properties.put("serializer.class","com.locationcast.kafka.lab.JsonEncoder");
       
       ProducerConfig producerConfig = new ProducerConfig(properties);
       kafka.javaapi.producer.Producer<String,User> producer = new kafka.javaapi.producer.Producer<String, User>(producerConfig);
       SimpleDateFormat sdf = new SimpleDateFormat();
      
       
       for(int i = 1; i < 500; i++){
    		
    	   User user = new User();
    		user = user.setUserName(userName).setAliasName(String.valueOf(i)).setEmail(email).setPassword(password);
    		
    	
    	     KeyedMessage<String, User> message =new KeyedMessage<String, User>(TOPIC,user);
    	     TimeUnit.MILLISECONDS.sleep(500);
    	     producer.send(message);
       }
  
       producer.close();
   }
}
