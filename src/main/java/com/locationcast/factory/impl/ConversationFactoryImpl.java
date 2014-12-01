/**
 * 
 */
package com.locationcast.factory.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.locationcast.domain.Conversation;
import com.locationcast.domain.Poster;
import com.locationcast.factory.ConversationFactory;

/**
 * @author Khoa
 *
 */
@Service
public class ConversationFactoryImpl implements ConversationFactory{

	private static final Logger logger = LoggerFactory.getLogger(ConversationFactoryImpl.class);

	public Conversation createConversation(Poster poster, long topicId, double[] longitudeAndLatitude){
		Conversation conversation = new Conversation(poster, longitudeAndLatitude);
		return conversation;
	}
}
