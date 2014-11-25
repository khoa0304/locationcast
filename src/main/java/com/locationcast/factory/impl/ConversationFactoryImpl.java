/**
 * 
 */
package com.locationcast.factory.impl;

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

	
	public Conversation createConversation(Poster poster, long topicId, double[] longitudeAndLatitude){
		Conversation conversation = new Conversation(poster, longitudeAndLatitude);
		return conversation;
	}
}
