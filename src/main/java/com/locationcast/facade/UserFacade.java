/**
 * 
 */
package com.locationcast.facade;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;

/**
 * @author Khoa
 *
 */
public interface UserFacade {

	 User registerUser(User user) throws InvalidDomainModelException;
	
	 User findUserByIndexFields(User user);
	 
	 User findUserByUserName(String userName);
	 
	 User getUserSchema();
}
