/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;

/**
 * @author Khoa
 *
 */
public class ErrorMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	
	public ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
