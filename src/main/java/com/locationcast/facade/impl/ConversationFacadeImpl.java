/**
 * 
 */
package com.locationcast.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationcast.domain.Conversation;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;
import com.locationcast.facade.ConversationValidation;
import com.locationcast.repository.ConversationRepository;

/**
 * @author Khoa
 *
 */
@Service
public class ConversationFacadeImpl implements ConversationFacade {

	@Autowired
	ConversationRepository conversationRepos;
	
	@Autowired
	ConversationValidation conversationValidator;
	
	public void createConversation(Conversation conversation) throws InvalidDomainModelException{
		
		conversationValidator.validationBasicConversationInfo(conversation);
		conversationRepos.insertConversation(conversation);
	}
	
}
