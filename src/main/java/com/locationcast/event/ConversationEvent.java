/**
 * 
 */
package com.locationcast.event;

import java.util.EventObject;

/**
 * @author Khoa
 *
 */
public class ConversationEvent extends EventObject{

	private static final long serialVersionUID = 7786808532487153907L;

	/** System time when the event happened */
	private final long timestamp;
	
	private final EventType eventType;
	
	public static enum EventType{
		CREATED,UPDATED,DELETED
	}

	/**
	 * @param source
	 */
	public ConversationEvent(Object source,EventType eventType) {
		super(source);
		this.timestamp = System.currentTimeMillis();
		this.eventType = eventType;
	}

	/**
	 * @return the timestamp
	 */
	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}

	
}
