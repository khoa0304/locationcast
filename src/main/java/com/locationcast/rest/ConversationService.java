/**
 * 
 */
package com.locationcast.rest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.locationcast.domain.Content;
import com.locationcast.domain.Conversation;
import com.locationcast.exception.DuplicatedDomainModelException;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.ConversationFacade;

import static com.locationcast.constant.ConversationServiceConstant.*;
import static com.locationcast.constant.LocationCastConstant.APPLICATION_JSON_TYPE;
import static com.locationcast.constant.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_CREATE_PATH;
import static com.locationcast.constant.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_PUBLISH_PATH;
import static com.locationcast.constant.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_SCHEMA_PATH;
import static com.locationcast.constant.LocationCastConstant.CONVERSATION_REST_SERVICE_PATH.CONVERSATION_SERVICE_PATH;
/**
 * @author Khoa
 *
 */
@Controller
@RequestMapping(CONVERSATION_SERVICE_PATH)
public class ConversationService {

	
	private static final Logger logger = LoggerFactory.getLogger(ConversationService.class);
	
	@Autowired
	private ConversationFacade conversationFacade;
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@RequestMapping(value=CONVERSATION_CREATE_PATH,method = RequestMethod.POST,consumes = APPLICATION_JSON_TYPE,produces = APPLICATION_JSON_TYPE)
	@ResponseStatus(value=HttpStatus.CREATED)
	@ResponseBody
	public Conversation createConversation(HttpServletRequest request,@RequestBody Conversation conversation) throws InvalidDomainModelException,DuplicatedDomainModelException{
	
		logger.info("Start creating new conversation {}", conversation);
	
		String ipAddress = request.getRemoteAddr();
	
		conversationFacade.createConversation(ipAddress,conversation);
		
		logger.info("Finish creating new conversation {}", conversation);
		
		return conversation;
	}
	
	
	@RequestMapping(value=CONVERSATION_SCHEMA_PATH,method = RequestMethod.GET, produces = APPLICATION_JSON_TYPE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	public Conversation getConversationSchema(HttpServletRequest request,Principal principal) throws InvalidDomainModelException,DuplicatedDomainModelException{
	
		String ipAddress = request.getRemoteAddr();
	
		Conversation conversation = conversationFacade.getEmptyConversation(ipAddress);
		Content content = new Content();
		content.setContentString("Push From ipaddress: "+ipAddress);;
		conversation.setContent(content);
		
		logger.info("Return conversation schema {}", conversation);
		
	
		return conversation;
	}
	
	
	@RequestMapping(value=CONVERSATION_PUBLISH_PATH,method = RequestMethod.POST,consumes = APPLICATION_JSON_TYPE)
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	public void pushConversationToClient(Principal principal, @RequestBody Conversation conversation){
		
		logger.info("About to publish conversation {}", conversation);
		
		this.template.convertAndSend(CONVERSATION_TOPIC,conversation);
		
		logger.info("Finished publishing conversation {}", conversation);
		
	}
	
	
	@RequestMapping(value = "/image", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(MultipartFile file) {
		
		String name = "test";
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				return "You successfully uploaded " + name + "!";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name
					+ " because the file was empty.";
		}
	}
}
