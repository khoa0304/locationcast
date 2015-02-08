package com.locationcast.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.locationcast.domain.Content;
import com.locationcast.domain.Conversation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring/integration/http-outbound-config.xml","classpath*:applicationContext.xml" })
public class KafkaConversationPublisherTest {

	@Autowired
	protected  ApplicationContext applicationContext;
	
	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testPublishRealTimeConversation(){
	
		String URL = "http://localhost:8080/LocationCast/rest/conversation/publish";
		
		Conversation conversation = new Conversation(new double[]{-121,32});
		Content content = new Content();
		content.setContentString("Hello world from the server side");
	
		conversation.setContent(content);
		
		HttpEntity<Conversation> conversationEntity = getHttpHeadersWithUserCredentials(conversation);
		ResponseEntity<Conversation> response = restTemplate.exchange(URL,HttpMethod.POST, conversationEntity, Conversation.class);
        System.out.println(response.getStatusCode().name());
	}
	
	
	private  HttpEntity<Conversation> getHttpHeadersWithUserCredentials(Conversation conversation) {
		
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(acceptableMediaTypes);
		
		String username = "ConversationRealtimePublisherConsumer";
		String password = "welcome";

		String combinedUsernamePassword = username + ":" + password;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String(base64Token);
		
		// adding Authorization header for HTTP Basic authentication
		headers.add("Authorization", "Basic  " + base64EncodedToken);
		
		// Pass the new person and header
		HttpEntity<Conversation> entity = new HttpEntity<Conversation>(conversation, headers);
				
		
		return entity;
	}
}
