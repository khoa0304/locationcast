/**
 * 
 */
package com.locationcast.exception;


/**
 * @author Khoa
 *
 */
public abstract class AbstractLocationCastException extends Exception {

	private static final long serialVersionUID = 1L;

	private long occuredTime = System.currentTimeMillis();
	
	protected String message = "";

	public long getOccuredTime(){
		return this.occuredTime;
	}
}
