/**
 * 
 */
package com.locationcast.factory.impl;

import org.springframework.stereotype.Service;

import com.locationcast.domain.User;
import com.locationcast.factory.UserFactory;

/**
 * @author Khoa
 *
 */
@Service
public class UserFactoryImpl implements UserFactory{

	public User createUser(String userName, String firstName, String lastName, String emailAddress,String password){
		
		User user =  new User(userName,firstName, lastName,emailAddress,password);
		return user;
	}
	
    public User createUser(String userName, String firstName, String lastName, String emailAddress, String mobilePhone,String password){
		
		User user =  new User(userName,firstName, lastName,emailAddress,mobilePhone,password);
		return user;
	}
    
    public User createUser(String userName,String emailAddress,String password){
		
		User user =  new User(userName,emailAddress,password);
		return user;
	}
}
