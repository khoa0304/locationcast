/**
 * 
 */
package com.locationcast.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.locationcast.domain.User;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;

/**
 * @author Khoa
 *
 */
@Controller
@RequestMapping("/rest/user")
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFacade userFacade;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	@ResponseBody
	public User registerUser(@RequestBody User user) throws InvalidDomainModelException{
		
		try {
			
			userFacade.createUser(user);
			
		} catch (InvalidDomainModelException e) {
			throw e;
		}
		
		logger.info("Finished adding user %s",user);
		
		return user;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public User testUser(){
		
		
		User user = new User("khoa0304", "khoa0304@Yahoo.com", "welcome");
		return user;
	}
}
