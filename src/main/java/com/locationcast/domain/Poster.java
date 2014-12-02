/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Khoa
 *
 */
@Document
public class Poster implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long userId ;
	
	private String aliasUserName = "";

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
	 * @return the aliasUserName
	 */
	public String getAliasUserName() {
		return aliasUserName;
	}

	/**
	 * @param aliasUserName the aliasUserName to set
	 */
	public void setAliasUserName(String aliasUserName) {
		this.aliasUserName = aliasUserName;
	}

	
	
}
