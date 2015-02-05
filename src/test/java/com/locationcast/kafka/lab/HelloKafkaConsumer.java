package com.locationcast.kafka.lab;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.locationcast.domain.User;


import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.javaapi.message.ByteBufferMessageSet;
import kafka.message.MessageAndOffset;
 
 
public class HelloKafkaConsumer extends  Thread {

	final static String clientId = "SimpleConsumerDemoClient";
    final static String TOPIC = HelloKafkaProducer.TOPIC;
 
    ConsumerConnector consumerConnector;
 
 
    
    public static void main(String[] argv) throws UnsupportedEncodingException {
        HelloKafkaConsumer helloKafkaConsumer = new HelloKafkaConsumer();
        helloKafkaConsumer.start();
    }
 
    public HelloKafkaConsumer(){
        Properties properties = new Properties();
        properties.put("zookeeper.connect","localhost:2181");
        properties.put("group.id","consumer-group-4");
        properties.put("auto.offset.reset", "smallest");
        ConsumerConfig consumerConfig = new ConsumerConfig(properties);
        consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);
    }
 
    @Override
    public void run() {
    	
    	Thread.currentThread().setName("4th  Consumer");
    	
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(TOPIC, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        KafkaStream<byte[], byte[]> stream =  consumerMap.get(TOPIC).get(0);
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        
        JsonDecoder decoder = new JsonDecoder();
        while(it.hasNext()){
        	
        	User user = (User) decoder.fromBytes(it.next().message());
        	System.out.println("Thread Name :"+ Thread.currentThread().getName()+". User: " +user);
        }
            
 
    }
 
//    private static void printMessages(ByteBufferMessageSet messageSet) throws UnsupportedEncodingException {
//        for(MessageAndOffset messageAndOffset: messageSet) {
//            ByteBuffer payload = messageAndOffset.message().payload();
//            byte[] bytes = new byte[payload.limit()];
//            payload.get(bytes);
//            System.out.println(new String(bytes, "UTF-8"));
//        }
//    }
}