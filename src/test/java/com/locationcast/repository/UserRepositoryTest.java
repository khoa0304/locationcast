/**
 * 
 */
package com.locationcast.repository;

import static com.locationcast.test.data.UserTestData.email;
import static com.locationcast.test.data.UserTestData.nickName;
import static com.locationcast.test.data.UserTestData.password;
import static com.locationcast.test.data.UserTestData.userName;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;
/**
 * @author Khoa
 *
 */

public class UserRepositoryTest extends AbstractMongoDBReposTest {
	
	@Autowired
    UserFacade userFacade;
	
	@Before
	public void initClass(){

		if(mongoOperation.collectionExists(User.class)){
			mongoOperation.dropCollection(User.class);
			mongoOperation.createCollection(User.class);
		}
	}
	
	@Test
	public void testInsertAndQueryNewUser() throws InvalidDomainModelException{
		User user = new User();
		user = user.setUserName(userName).setAliasName(nickName).setEmail(email).setPassword(password);
		userFacade.createUser(user);
		User queriedUser = userFacade.findUserByIndexFields(user);
		assertNotNull(queriedUser.getId());
		assertEquals(user.getAliasName(),nickName);
		
	}
}
