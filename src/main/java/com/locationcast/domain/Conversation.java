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
@Document(collection="Conversation")
public class Conversation extends AbstractDomainModel {

	private static final long serialVersionUID = 1L;

	//@Field(value="poster")
	private Poster poster = null;
	
    //@Field(value="content")
	private Content content = null;
	
	private String title = "";
	
	private int numberofFollower = 0;
	
	private List<String> tags = new ArrayList<String>(); 
	
	private int overAllRatingLevel;
	
	//@Field(value="comments")
	private List<Comment> comments = new ArrayList<Comment>();
	
	//private long topicId;
	
	private ConversationScope scope = ConversationScope.PROTECTED;
	
	private double[] longAndLat;
	
	public static enum FieldNames{
	
		LongAndLat("longAndLat"),
		Content("content"),
		ConentString("content.contentString");
		
		private String fieldName;
		
		FieldNames(String fieldName){
			this.fieldName = fieldName;
		}
		
		/**
		 * @return the fieldName
		 */
		public String getFieldName() {
			return fieldName;
		}
	}
	
	public Conversation(){}
	
	public Conversation(Poster poster, double[] longAndLat){
		this.poster = poster;
		this.longAndLat = longAndLat;
	}

	public Conversation(double[] longAndLat){
		this.longAndLat = longAndLat;
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
	public Conversation setContent(Content content) {
		this.content = content;
		return this;
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
	

	/**
	 * @return the longAndLat
	 */
	public double[] getLongAndLat() {
		return longAndLat;
	}

	/**
	 * @param longAndLat the longAndLat to set
	 */
	public void setLongAndLat(double[] longAndLat) {
		this.longAndLat = longAndLat;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(" Longitude : ").append(longAndLat[0]);
		
		sb.append(" Latitude : ").append(longAndLat[1]);
		
		sb.append(poster.toString());
		
		return sb.toString();
	}

}
