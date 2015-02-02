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

	
	private static final long serialVersionUID = 1L;
	
	
	private long userId ;
	
	
	private String ipAddress = "";
	
	private String aliasUserName = "";

	public Poster() {}
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

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Alias name ").append(aliasUserName);
		sb.append(" Ip Address: ").append(ipAddress);
		return sb.toString();
	}
	
}
