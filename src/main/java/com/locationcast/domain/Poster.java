/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;

/**
 * @author Khoa
 *
 */

public class Poster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long userId = 0l;
	
	private String posterNickName = "";

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the posterNickName
	 */
	public String getPosterNickName() {
		return posterNickName;
	}

	/**
	 * @param posterNickName the posterNickName to set
	 */
	public void setPosterNickName(String posterNickName) {
		this.posterNickName = posterNickName;
	}
	
}
