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
public interface UserValidation {

	
	void validateBasicUserRegistrationInfo(User user) throws InvalidDomainModelException;
}
