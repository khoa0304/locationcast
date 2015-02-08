package com.locationcast.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.locationcast.domain.Content;
import com.locationcast.domain.Conversation;
import com.locationcast.facade.ConversationFacade;

@Controller
@MessageMapping("/rest/conversation/")
public class ConversationServiceRealTime {

	private static final Logger logger = LoggerFactory
			.getLogger(ConversationServiceRealTime.class);

	@Autowired
	ConversationFacade conversationFacade;

	@MessageMapping("/conversationAdd")
	@SendTo("/topic/showConversation")
	public Conversation getConversation() throws Exception {

		// String ipAddress = request.getRemoteAddr();

		
		Conversation conversation = new Conversation(new double[]{-121,32});
		Content content = new Content();
		content.setContentString("Hello world from the server side");
	
		conversation.setContent(content);

		logger.info("Return conversation schema {}", conversation);

		return conversation;

	}

}
