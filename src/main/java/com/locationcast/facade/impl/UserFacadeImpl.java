/**
 * 
 */
package com.locationcast.facade.impl;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;
import com.locationcast.facade.UserValidation;
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
	
	@Autowired
	UserValidation userValidation;
	
	@PreDestroy
	public void init(){
		System.out.println("Test");
	}
	public boolean createUser(User user) throws InvalidDomainModelException{
		
		userValidation.validateBasicUserRegistrationInfo(user);
		
		userRepository.insertUser(user);
		
		return true;
	}
	
	
	@Override
	public User findUser(User user) {
		
		User queriedUser = null;
		
		if(!StringUtils.isEmpty(user.getUserName())){
		    queriedUser = userRepository.findUserByUserName(user.getUserName());
		}
		
		return queriedUser;
			
	}
	
  
}
