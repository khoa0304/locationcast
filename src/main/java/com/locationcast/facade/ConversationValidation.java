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
public interface ConversationValidation {

	void validationBasicConversationInfo(Conversation conversation) throws InvalidDomainModelException;
}
