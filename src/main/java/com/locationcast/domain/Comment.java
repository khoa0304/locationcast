/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Khoa
 *
 */

public class Comment extends AbstractDomainModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Field(value="poster")
	private Poster poster;
	
	private int likeCounter = 0;
	
	private int dislikeCounter = 0;
	
	@Field(value="content")
	private Content content = null;
	

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
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	/**
	 * @return the likeCounter
	 */
	public int getLikeCounter() {
		return likeCounter;
	}

	/**
	 * @param likeCounter the likeCounter to set
	 */
	public void setLikeCounter(int likeCounter) {
		this.likeCounter = likeCounter;
	}

	/**
	 * @return the dislikeCounter
	 */
	public int getDislikeCounter() {
		return dislikeCounter;
	}

	/**
	 * @param dislikeCounter the dislikeCounter to set
	 */
	public void setDislikeCounter(int dislikeCounter) {
		this.dislikeCounter = dislikeCounter;
	}
	
}
