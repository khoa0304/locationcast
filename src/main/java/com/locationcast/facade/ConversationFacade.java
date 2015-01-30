/**
 * 
 */
package com.locationcast.facade;

import java.util.List;

import com.locationcast.domain.Conversation;
import com.locationcast.exception.InvalidDomainModelException;

/**
 * @author Khoa
 *
 */
public interface ConversationFacade {

	
	void createConversation(String ipAddress,Conversation conversation) throws InvalidDomainModelException;
	
	List<Conversation> findConverstaionByContentKeyWords(String[] words);
	
	List<Conversation> findConversationsByLongitudeAndLatitude(double[] longitudeAndLatitude);
	
	Conversation getEmptyConversation(String ipAddress);
}
