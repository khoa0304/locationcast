/**
 * 
 */
package com.locationcast.repository;

import com.locationcast.domain.User;

/**
 * @author Khoa
 *
 */
public interface UserRepository {

	void insertUser(User user);
	
	User findUserByUserName(String userName, User.Fields field);

	/**
	 * @param id
	 * @param idKey
	 * @return
	 */
	User findUserById(long id, String idKey);
}
