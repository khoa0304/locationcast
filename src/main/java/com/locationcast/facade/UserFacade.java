/**
 * 
 */
package com.locationcast.facade;

/**
 * @author Khoa
 *
 */
public interface UserFacade {

	 boolean addUser(String userName, String firstName, String lastName, String emailAddress,String password);
	 boolean addUser(String userName,String email,String password);
}
