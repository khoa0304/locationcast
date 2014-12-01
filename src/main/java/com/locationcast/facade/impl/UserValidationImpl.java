/**
 * 
 */
package com.locationcast.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserValidation;

/**
 * @author Khoa
 *
 */
@Service
public class UserValidationImpl implements UserValidation{

	public void validateBasicUserRegistrationInfo(User user) throws InvalidDomainModelException{
		if(StringUtils.isEmpty(user.getUserName())){
			throw new InvalidDomainModelException(user, " Missing user name");
		}
		if(StringUtils.isEmpty(user.getEmail())){
			throw new InvalidDomainModelException(user, " Missing Email");
		}
		if(StringUtils.isEmpty(user.getPassword())){
			throw new InvalidDomainModelException(user, " Missing password");
		}
	}
}
