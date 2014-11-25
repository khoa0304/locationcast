/**
 * 
 */
package com.locationcast.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,produces = "application/json")
	@ResponseBody
	public User registerUser(User user){
		
		userFacade.addUser(user.getUserName(),user.getEmail(),user.getPassword());
		
		return user;
	}
}
