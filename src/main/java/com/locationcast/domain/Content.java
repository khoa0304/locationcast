/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * @author Khoa
 *
 */

public class Content implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@TextIndexed
	private String contentString = "";
	
	private String hrefLink = "";
	
	private List<byte[]> imageList = new ArrayList<byte[]>();

	/**
	 * @return the text
	 */
	public String getText() {
		return contentString;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.contentString = text;
	}

	/**
	 * @return the hrefLink
	 */
	public String getHrefLink() {
		return hrefLink;
	}

	/**
	 * @param hrefLink the hrefLink to set
	 */
	public void setHrefLink(String hrefLink) {
		this.hrefLink = hrefLink;
	}

	/**
	 * @return the imageList
	 */
	public List<byte[]> getImageList() {
		return imageList;
	}

	/**
	 * @param imageList the imageList to set
	 */
	public void setImageList(List<byte[]> imageList) {
		this.imageList = imageList;
	}
}
