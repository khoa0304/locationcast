/**
 * 
 */
package com.locationcast.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.locationcast.domain.Topic;
import com.locationcast.factory.TopicFactory;

/**
 * @author Khoa
 *
 */
@Service
public class TopicFactoryImpl  implements TopicFactory{

	
	
	public List<Topic> getListOfAvailableTopic(){
		
		List<Topic> topicList =  new ArrayList<Topic>(TopicNames.values().length);
		
		for(TopicNames topicEnum : TopicNames.values()){
		
			Topic topic = new Topic(topicEnum.getTopicName());
			topicList.add(topic);
		}
		return topicList;
	}
}
