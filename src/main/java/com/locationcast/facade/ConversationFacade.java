/**
 * 
 */
package com.locationcast.facade;

import com.locationcast.domain.Conversation;
import com.locationcast.exception.InvalidDomainModelException;

/**
 * @author Khoa
 *
 */
public interface ConversationFacade {

	
	void createConversation(Conversation conversation) throws InvalidDomainModelException;
}
