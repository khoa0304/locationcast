/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

/**
 * @author Khoa
 * @since 0.1
 *
 */
public class AbstractDocument implements Serializable{

	
	private static final long serialVersionUID = 3136223572483839806L;

    protected Date lastModifiedDate;
	
    public static enum AbstractAttributeName{
    	
       id("id");
	   public String name;
	   
	   private AbstractAttributeName(String name) {
		this.name = name;
	   }
	   
	   public String getName(){
		   return this.name;
	   }
    }
    
    
	@Id
	protected long id = System.currentTimeMillis();


	/**
	 * @return the lastModifiedDate
	 */
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	/**
	 * @param lastModifiedDate the lastModifiedDate to set
	 */
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	
}
