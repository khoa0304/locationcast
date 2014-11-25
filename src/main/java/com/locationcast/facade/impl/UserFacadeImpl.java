/**
 * 
 */
package com.locationcast.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.locationcast.domain.User;
import com.locationcast.facade.UserFacade;
import com.locationcast.factory.UserFactory;
import com.locationcast.repository.UserRepository;

/**
 * @author Khoa
 *
 */
@Service
public class UserFacadeImpl implements UserFacade {

	@Autowired
	public UserFactory userFactory;
	
	@Autowired
    UserRepository userRepository;
	
	public boolean addUser(String userName, String firstName, String lastName, String emailAddress,String password){
		
		User user = userFactory.createUser(userName, firstName, lastName, emailAddress,password);
		userRepository.insertUser(user);
		
		return true;
	}
	
    public boolean addUser(String userName, String emailAddress,String password){
		
		User user = userFactory.createUser(userName,emailAddress,password);
		userRepository.insertUser(user);
		
		return true;
	} 
}
