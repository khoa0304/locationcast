/**
 * 
 */
package com.locationcast.repository;

import java.util.List;

import com.locationcast.domain.Conversation;

/**
 * @author Khoa
 *
 */
public interface ConversationRepository {

	void insertConversation(Conversation conversation);
	
	List<Conversation> findConverstaionByContentKeyWords(String[] words);
	
	List<Conversation> findConversationByLongitudeAndLatitude(double[] longitudeAndLatitude, double proximity);

}
