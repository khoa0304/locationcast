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
import com.locationcast.kafka.ConversationServiceProducer;
import com.locationcast.repository.ConversationRepository;
import com.locationcast.security.rest.SecurityManagementService;
import com.locationcast.util.UserUtil;
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
	
	@Autowired
	ConversationServiceProducer conversationServiceProducer;
	

	public void createConversation(String ipAddress, Conversation conversation) throws InvalidDomainModelException{
		
		setPoster(ipAddress,conversation);
		conversationValidator.validationBasicConversationInfo(conversation);
		conversationServiceProducer.publishConversation(conversation);
	}
	
	public List<Conversation> findConverstaionByContentKeyWords(String[] words){
	
		if(words == null){
			throw new IllegalArgumentException("argument.npe");
		}
		
		List<Conversation> conversationList = conversationRepos.findConverstaionByContentKeyWords(words);
		return conversationList;
	}
	
	public List<Conversation> findConversationsByCoordinates(double[] longitudeAndLatitude){
		
		return conversationRepos.findConversationByLongitudeAndLatitude(longitudeAndLatitude);
	}

	@Override
	public Conversation getEmptyConversation(String ipAddress) {
		
		Conversation conversation = new Conversation(new double[]{0,0});
		setPoster(ipAddress, conversation);
		return conversation;
		
	}
	
	
	private Poster setPoster(String ipAddress, Conversation conversation){
	
		Poster poster = conversation.getPoster();
		if(poster == null){
			String userPrincipal = securityMgmtService.getCurrentSessionPrincipalName();
			
			if(userPrincipal == null){
				 poster = UserUtil.getAnonymousPoster(conversation.getLongAndLat(), ipAddress);
			}
			else{
				User user = userFacade.findUserByUserName(userPrincipal);
				if(user == null){
					 poster = UserUtil.getAnonymousPoster(conversation.getLongAndLat(), ipAddress);
				}
				poster = UserUtil.getPoster(user.getAliasName(),user.getId());
			}
			conversation.setPoster(poster);
		}
		
		poster.setIpAddress(ipAddress);
		return poster;
	}

}
