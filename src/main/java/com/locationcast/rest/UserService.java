/**
 * 
 */
package com.locationcast.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locationcast.domain.User;
import com.locationcast.exception.DuplicatedDomainModelException;
import com.locationcast.exception.InvalidDomainModelException;
import com.locationcast.facade.UserFacade;
import static com.locationcast.util.LocationCastConstant.USER_REST_SERVICE_PATH.USER_SERVICE_PATH;
/**
 * @author Khoa
 *
 */
@Controller
@RequestMapping(USER_SERVICE_PATH)
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserFacade userFacade;
	
	
	@RequestMapping(value="/add",method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public User registerUser(@RequestBody User user) throws InvalidDomainModelException,DuplicatedDomainModelException{
	
		User queriedUser = userFacade.findUserByIndexFields(user);
		if(queriedUser != null){
			throw new DuplicatedDomainModelException(user, "User already exists with the same username");
		}
		
		userFacade.createUser(user);
		logger.info("Finished adding user %s",user);
		
		return user;
	}
	
}
