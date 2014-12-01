/**
 * 
 */
package com.locationcast.lab;

import java.util.ArrayList;
import java.util.List;

import com.locationcast.domain.Topic;

/**
 * @author Khoa
 *
 */
public class TestUtility {

	public static List<Topic> createTopicList(int numberofTopics,String[] topicNames){
		
		List<Topic> list = new ArrayList<Topic>(numberofTopics);
		
		for(int i = 0 ;i< numberofTopics; i++){
			Topic topic = new Topic();
		}
		
		return list;
	}
}
