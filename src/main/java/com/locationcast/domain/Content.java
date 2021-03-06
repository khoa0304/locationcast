/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 *
 */

public class Content implements Serializable{

	
	private static final long serialVersionUID = 1L;

	//@TextIndexed
	private String contentString = "";
	
	private String hrefLink = "";
	
	private List<byte[]> imageList = new ArrayList<byte[]>();

	public Content(){};
	

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


	public String getContentString() {
		return contentString;
	}


	public void setContentString(String contentString) {
		this.contentString = contentString;
	}
}
