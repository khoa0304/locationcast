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
	
	public InvalidDomainModelException(AbstractDomainModel domainModel, String msg){
		super(domainModel,msg);
	}
	
	
	
}
