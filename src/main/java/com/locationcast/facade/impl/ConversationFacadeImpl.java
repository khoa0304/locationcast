/**
 * 
 */
package com.locationcast.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import com.locationcast.domain.Conversation;
import com.locationcast.domain.Poster;
import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;
import com.locationcast.facade.ConversationValidation;
import com.locationcast.facade.UserFacade;
import com.locationcast.repository.ConversationRepository;
import com.locationcast.security.rest.SecurityManagementService;
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
	
	@Autowired
	UserFacade userFacade;
	
	@Autowired
	SecurityManagementService securityMgmtService;
	
	public void createConversation(Conversation conversation) throws InvalidDomainModelException{
		
		String userPrincipal = securityMgmtService.getCurrentSessionPrincipal().getUsername();
		
		User user = userFacade.findUserByUserName(userPrincipal);
		Poster poster = user.getPoster();
		conversation.setPoster(poster);
		
		conversationValidator.validationBasicConversationInfo(conversation);
		conversationRepos.insertConversation(conversation);
	}
	
	public List<Conversation> findConverstaionByContentKeyWords(String[] words){
		if(words == null){
			throw new IllegalArgumentException("argument.npe");
		}
		List<Conversation> conversationList = conversationRepos.findConverstaionByContentKeyWords(words);
		return conversationList;
	}
	
	public List<Conversation> findConversationsByLongitudeAndLatitude(double[] longitudeAndLatitude){
		
		return conversationRepos.findConversationByLongitudeAndLatitude(longitudeAndLatitude);
	}
	
}
