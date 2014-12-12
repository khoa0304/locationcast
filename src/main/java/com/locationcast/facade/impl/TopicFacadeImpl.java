/**
 * 
 */
package com.locationcast.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationcast.domain.Topic;
import com.locationcast.facade.TopicFacade;
import com.locationcast.repository.TopicRepository;

/**
 * @author Khoa
 *
 */
@Service
public class TopicFacadeImpl implements TopicFacade {

	
	@Autowired
	TopicRepository topicRepos;
	
	public List<Topic> getAllAvailableTopic(){
		
		List<Topic> topicList = topicRepos.getAvailableTopicLits();
		
		return topicList;
	}
	
	

}
