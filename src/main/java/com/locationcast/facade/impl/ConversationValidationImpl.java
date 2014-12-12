/**
 * 
 */
package com.locationcast.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.locationcast.domain.Conversation;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationValidation;

/**
 * @author Khoa
 *
 */
@Service
public class ConversationValidationImpl implements ConversationValidation {

	public void validationBasicConversationInfo(Conversation conversation) throws InvalidDomainModelException{
		
		if(conversation.getPoster() == null){
			throw new InvalidDomainModelException(conversation,"conversation.poster.missing");
		}
		
		if(conversation.getLongAndLat() == null){
			throw new InvalidDomainModelException(conversation,"conversation.longitudeAndLatitude.missing");
		}
		
		if(StringUtils.isEmpty(conversation.getContent())){
			throw new InvalidDomainModelException(conversation,"conversation.content.missing");
		}
	}
}
