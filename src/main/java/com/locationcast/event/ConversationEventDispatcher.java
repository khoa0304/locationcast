/**
 * 
 */
package com.locationcast.event;

/**
 * @author Khoa
 *
 */
public interface ConversationEventDispatcher {

	 void registerListerner(ConversationEventListener listener);
	 
	 void dispatchConverationChangedEvent(ConversationEvent conversationEvent);
}
