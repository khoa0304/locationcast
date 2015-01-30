/**
 * 
 */
package com.locationcast.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.locationcast.util.LocationCastConstant;

/**
 * @author Khoa
 *
 */
@Document(collection="User")
public class User extends AbstractDomainModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Indexed(sparse=true,unique=true,
			expireAfterSeconds=LocationCastConstant.SESSION_EXPIRATION_IN_SECOND)
	private String userName;
	
	private String aliasName;
	
	private String firstName = null;
	
	private String lastName = null;
	
	private String email;
	
	private String mobileNumber = null;
	
	private List<Long> conversationFollowed = new ArrayList<Long>();
	
	private List<Long> conversationPosted = new ArrayList<Long>();
	
	private String password = "";

	
	public static enum FieldNames {
		
		USERNAME("userName"),
		ALIASNAME("aliasName");
		
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
	
	public User(){};
	
	public User(String userName,String emailAddress, String password){
		this.userName = userName;
		this.email = emailAddress;
		this.password = password;
	}
	
	public User(String userName, String firstName, String lastName, String emailAddress, String password){
		
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = emailAddress;
		this.password = password;
	}

	public User(String userName, String firstName, String lastName, String emailAddress,String mobileNumber, String password){
		
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = emailAddress;
		this.mobileNumber = mobileNumber;
		this.password = password;
	}
	
	public User setUserName(String userName){
		this.userName = userName;
		return this;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public User setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
		return this;
	}

	/**
	 * @return the conversationFollowed
	 */
	public List<Long> getConversationFollowed() {
		return conversationFollowed;
	}

	/**
	 * @param conversationFollowed the conversationFollowed to set
	 */
	public void setConversationFollowed(List<Long> conversationFollowed) {
		this.conversationFollowed = conversationFollowed;
	}

	/**
	 * @return the conversationPosted
	 */
	public List<Long> getConversationPosted() {
		return conversationPosted;
	}

	/**
	 * @param conversationPosted the conversationPosted to set
	 */
	public void setConversationPosted(List<Long> conversationPosted) {
		this.conversationPosted = conversationPosted;
	}
	
	
	
	@Override
	public String toString(){
		String st = String.format(" Username: %s - Email: %s - Alias Name: %s", userName,email, aliasName);
		return st;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public User setPassword(String password) {
		this.password = password;
		return this;
	}
	
	

	/**
	 * @return the nickName
	 */
	public String getAliasName() {
		return aliasName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public User setAliasName(String nickName) {
		this.aliasName = nickName;
		return this;
	}

	
}
