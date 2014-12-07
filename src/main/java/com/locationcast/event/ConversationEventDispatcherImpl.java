/**
 * 
 */
package com.locationcast.event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * @author Khoa
 *
 */
@Service
public class ConversationEventDispatcherImpl implements ConversationEventDispatcher{

	final List<ConversationEventListener> eventListener = new ArrayList<ConversationEventListener>();


	public void registerListerner(ConversationEventListener listener){
		eventListener.add(listener);
	}
	
	public void dispatchConverationChangedEvent(ConversationEvent conversationEvent){
		for(ConversationEventListener listener : eventListener){
			listener.dipatchConversationEvent(conversationEvent);
		}
	}
}
