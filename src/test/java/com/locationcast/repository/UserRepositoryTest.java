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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;
import com.locationcast.facade.impl.UserFacadeImpl;
/**
 * @author Khoa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext.xml"})
public class UserRepositoryTest {
	
	@Autowired
	protected  ApplicationContext applicationContext;

	protected static MongoOperations mongoOperation = null;
	
    UserFacade userFacade = null;
	
	@Before
	public void initClass(){
		
		userFacade = applicationContext.getBean(UserFacadeImpl.class);
		assertNotNull(userFacade);
		
		mongoOperation = (MongoOperations) applicationContext
				.getBean("locationcastdb");
		
		assertNotNull(mongoOperation);
		
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
