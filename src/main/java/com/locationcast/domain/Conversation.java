package com.locationcast.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.locationcast.util.ConversationScope;

/**
 * 
 * @author Khoa
 * @since 0.1
 */
@Document
public class Conversation extends AbstractDomainModel {

	private static final long serialVersionUID = 1L;

	private Poster poster = null;
	
	private Content content = null;
	
	private String title = "";
	
	private int numberofFollower = 0;
	
	private List<String> tags = new ArrayList<String>(); 
	
	private int overAllRatingLevel;
	
	private List<Comment> comments = new ArrayList<Comment>();
	
	private long topicId;
	
	private ConversationScope scope = ConversationScope.PROTECTED;
	
	private Location location;
	
	
	
	public Conversation(Poster poster, double[] longitudeAndLatitude){
		this.poster = poster;
		this.location = new Location(longitudeAndLatitude);
	}

	/**
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the numberofFollower
	 */
	public int getNumberofFollower() {
		return numberofFollower;
	}

	/**
	 * @param numberofFollower the numberofFollower to set
	 */
	public void setNumberofFollower(int numberofFollower) {
		this.numberofFollower = numberofFollower;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @return the poster
	 */
	public Poster getPoster() {
		return poster;
	}

	/**
	 * @param poster the poster to set
	 */
	public void setPoster(Poster poster) {
		this.poster = poster;
	}
	
	/**
	 * @return the overAllRatingLevel
	 */
	public int getOverAllRatingLevel() {
		return overAllRatingLevel;
	}

	/**
	 * @param overAllRatingLevel the overAllRatingLevel to set
	 */
	public void setOverAllRatingLevel(int overAllRatingLevel) {
		this.overAllRatingLevel = overAllRatingLevel;
	}

	/**
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * @return the topicId
	 */
	public long getTopicId() {
		return topicId;
	}

	/**
	 * @param topicId the topicId to set
	 */
	public void setTopicId(long topicId) {
		this.topicId = topicId;
	}

	/**
	 * @return the scope
	 */
	public ConversationScope getScope() {
		return scope;
	}

	/**
	 * @param scope the scope to set
	 */
	public void setScope(ConversationScope scope) {
		this.scope = scope;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

}
