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
	
	protected String message = "";
	
	private AbstractDomainModel domainModel;

	/**
	 * 
	 */
	public AbstractLocationCastException(AbstractDomainModel domainModel, String msg) {
		this.domainModel = domainModel;
		this.message = msg;
	}
	
	public long getOccuredTime(){
		return this.occuredTime;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	
	@Override
	public String toString(){
		String reason = String.format("Exception class %s - Reason: %s - Domain Model: %s",InvalidDomainModelException.class.getSimpleName(), this.message,this.domainModel.toString());
		return reason;
	}
}
