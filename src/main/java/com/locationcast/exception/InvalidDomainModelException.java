/**
 * 
 */
package com.locationcast.exception;

import com.locationcast.domain.AbstractDomainModel;

/**
 * @author Khoa
 *
 */
public class InvalidDomainModelException extends AbstractLocationCastException {

	private static final long serialVersionUID = -3577655298827755989L;

	private AbstractDomainModel domainModel;
	
	public InvalidDomainModelException(){};
	
	public InvalidDomainModelException(AbstractDomainModel domainModel, String msg){
		this.domainModel = domainModel;
		this.message = msg;
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
