/**
 * 
 */
package com.locationcast.exception;

import com.locationcast.domain.AbstractDomainModel;


/**
 * @author Khoa
 *
 */
public abstract class AbstractLocationCastException extends Exception {

	private static final long serialVersionUID = 1L;

	private long occuredTime = System.currentTimeMillis();
	
	protected String messageKey = "";
	
	private AbstractDomainModel domainModel;

	/**
	 * 
	 */
	public AbstractLocationCastException(AbstractDomainModel domainModel, String messageKey) {
		this.domainModel = domainModel;
		this.messageKey = messageKey;
	}
	
	public long getOccuredTime(){
		return this.occuredTime;
	}
	
	public String getMessageKey(){
		return this.messageKey;
	}
	
	public AbstractDomainModel getDomainModel(){
		return this.domainModel;
	}
	
	
	@Override
	public String toString(){
		String reason = String.format("Exception class %s - Reason: %s - Domain Model: %s",InvalidDomainModelException.class.getSimpleName(), this.messageKey,this.domainModel.toString());
		return reason;
	}
}
