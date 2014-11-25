/**
 * 
 */
package com.locationcast.factory;

import com.locationcast.domain.User;

/**
 * @author Khoa
 *
 */
public interface UserFactory {

	User createUser(String userName, String firstName, String lastName, String emailAddress,String password);
	
	User createUser(String userName, String firstName, String lastName, String emailAddress, String mobilePhone,String password);
	
	 User createUser(String userName, String emailAddress,String password);
}
