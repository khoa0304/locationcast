package com.locationcast.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author Khoa
 * @since 0.1 
 */
@Document
public class Topic extends AbstractDomainModel{

	private static final long serialVersionUID = 1L;

	private String topicName;

	/**
	 * 
	 */
	public Topic() {}
	
	public Topic(String topicName){
		this.topicName = topicName;
	}
	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}

	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
