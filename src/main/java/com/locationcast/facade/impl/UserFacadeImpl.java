/**
 * 
 */
package com.locationcast.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.locationcast.domain.AbstractDomainModel;
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
	

	public User registerUser(User user) throws InvalidDomainModelException{
		
		userValidation.validateBasicUserRegistrationInfo(user);
		
        User queriedUser = findUserByIndexFields(user);
		
		if(queriedUser != null){
		  return queriedUser;
		}
		
		
		userRepository.insertUser(user);
		
		return user;
	}
	
	
	@Override
	public User findUserByIndexFields(User user) {
		
		User queriedUser = null;
		
		if(!StringUtils.isEmpty(user.getUserName())){
		    queriedUser = userRepository.findUserByUserName(user.getUserName(),User.FieldNames.USERNAME);
		}
		
		else if(!StringUtils.isEmpty(user.getAliasName())){
		    queriedUser = userRepository.findUserByUserName(user.getAliasName(),User.FieldNames.ALIASNAME);
		}
			
		return queriedUser;
			
	}
	
	public User findUserById(long id){
		User queriedUser = null;
		queriedUser = userRepository.findUserById(id,AbstractDomainModel.IndexFieldNames.id.getIdKey());
		
		return queriedUser;
		
	}


	/* (non-Javadoc)
	 * @see com.locationcast.facade.UserFacade#findUserByUserName(java.lang.String)
	 */
	@Override
	public User findUserByUserName(String userName) {
		User queriedUser =  userRepository.findUserByUserName(userName,User.FieldNames.USERNAME);
		return queriedUser;
	}


	@Override
	public User getUserSchema() {
		User user = new User();
		return user;
	}
	
  
}
