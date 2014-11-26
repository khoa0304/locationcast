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
public class User extends AbstractDocument implements Serializable {

	private static final long serialVersionUID = 1L;

	@Indexed(sparse=true,unique=true,expireAfterSeconds=LocationCastConstant.SESSION_EXPIRATION_IN_SECOND)
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobileNumber;
	
	private List<Long> conversationFollowed = new ArrayList<Long>();
	
	private List<Long> conversationPosted = new ArrayList<Long>();
	
	private String password = "";
	
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
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public void setEmail(String email) {
		this.email = email;
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
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
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
		String st = String.format(" Username: %s - Email: %s", userName,email);
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
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
