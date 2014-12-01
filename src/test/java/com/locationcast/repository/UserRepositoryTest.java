/**
 * 
 */
package com.locationcast.repository;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.Test;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;
import com.locationcast.facade.impl.UserFacadeImpl;
import com.locationcast.mongodb.config.MongoConfig;
import static org.testng.Assert.*;

/**
 * @author Khoa
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MongoConfig.class })
public class UserRepositoryTest extends AbstractMongoDBReposTest{

	public static final String userName ="khoatran";
	public static final String nickName ="Mr. Founder";
	public static final String email ="khoa0304@yahoo.com";
	public static final String password = "welcome1";
	
	UserFacade userFacade = null;
	
	@org.junit.BeforeClass
	public void initClass(){
		userFacade = applicationContext.getBean(UserFacadeImpl.class);
		assertNotNull(userFacade);
		
	}
	
	@Test
	public void testInsertNewUser() throws InvalidDomainModelException{
		User user = new User();
		user = user.setUserName(userName).setNickName(nickName).setEmail(email).setPassword(password).buildUserObject();
		userFacade.createUser(user);
		User queriedUser = userFacade.findUser(user);
		assertNotNull(queriedUser.getId());
		assertEquals(nickName, user.getNickName());
		
	}
}
