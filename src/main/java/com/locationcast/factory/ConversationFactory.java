/**
 * 
 */
package com.locationcast.factory;

import com.locationcast.domain.Conversation;
import com.locationcast.domain.Poster;

/**
 * @author Khoa
 *
 */
public interface ConversationFactory {

	
	Conversation createConversation(Poster poster,long topicId, double[] longitudeAndLatitude);
}
