/**
 * 
 */
package com.locationcast.rest;

import static com.locationcast.util.LocationCastConstant.APPLICATION_JSON_TYPE;
import static com.locationcast.util.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_CREATE_PATH;
import static com.locationcast.util.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_SERVICE_PATH;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locationcast.domain.Conversation;
import com.locationcast.exception.DuplicatedDomainModelException;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;
/**
 * @author Khoa
 *
 */
@Controller
@RequestMapping(CONVERSATION_SERVICE_PATH)
public class ConversationService {

	
	private static final Logger logger = LoggerFactory.getLogger(ConversationService.class);
	
	@Autowired
	ConversationFacade conversationFacade;
	
	@RequestMapping(value=CONVERSATION_CREATE_PATH,method = RequestMethod.POST,consumes = APPLICATION_JSON_TYPE, produces = APPLICATION_JSON_TYPE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public void createConversation(@RequestBody Conversation conversation) throws InvalidDomainModelException,DuplicatedDomainModelException{
	
	
		conversationFacade.createConversation(conversation);
		
		logger.info("Created new conversation %s", conversation);
	}
	
}
