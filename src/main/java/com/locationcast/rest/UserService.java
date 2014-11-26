/**
 * 
 */
package com.locationcast.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locationcast.domain.User;
import com.locationcast.facade.UserFacade;

/**
 * @author Khoa
 *
 */
@Controller
@RequestMapping("/rest/user")
public class UserService {

	
	@Autowired
	private UserFacade userFacade;
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/*",produces = "application/json")
	@ResponseBody
	public User registerUser(@RequestBody User user){
		
		userFacade.addUser(user.getUserName(),user.getEmail(),user.getPassword());
		
		return user;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET,produces = "application/json")
	@ResponseBody
	public User testUser(){
		
		
		User user = new User("khoa0304", "khoa0304@Yahoo.com", "welcome");
		return user;
	}
}
