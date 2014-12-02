/**
 * 
 */
package com.locationcast.exception;

import com.locationcast.domain.AbstractDomainModel;

/**
 * @author Khoa
 *
 */
public class DuplicatedDomainModelException extends AbstractLocationCastException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedDomainModelException(AbstractDomainModel domainModel, String msg){
		super(domainModel,msg);
	}

}
