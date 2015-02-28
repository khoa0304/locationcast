/**
 * 
 */
package com.locationcast.rest;

import static com.locationcast.constant.LocationCastConstant.APPLICATION_JSON_TYPE;
import static com.locationcast.constant.LocationCastConstant.USER_REST_SERVICE_PATH.USER_REGISTER_PATH;
import static com.locationcast.constant.LocationCastConstant.USER_REST_SERVICE_PATH.USER_SCHEMA_PATH;
import static com.locationcast.constant.LocationCastConstant.USER_REST_SERVICE_PATH.USER_SERVICE_PATH;

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
	
	
	@RequestMapping(value=USER_REGISTER_PATH,method = RequestMethod.POST,consumes = APPLICATION_JSON_TYPE,produces = APPLICATION_JSON_TYPE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.CREATED)
	public User registerUser(@RequestBody User user) throws InvalidDomainModelException,DuplicatedDomainModelException{
	
		
		User registeredUsers = userFacade.registerUser(user);
		logger.info("Finished adding user %s",registeredUsers);
		
		return registeredUsers;
	}
	
	@RequestMapping(value=USER_SCHEMA_PATH,method = RequestMethod.GET,produces = APPLICATION_JSON_TYPE)
	@ResponseBody
	@ResponseStatus(value=HttpStatus.ACCEPTED)
	public User getUserSchema() throws InvalidDomainModelException,DuplicatedDomainModelException{
	
		User user = userFacade.getUserSchema();
		
		logger.info("Return user schema %s",user);
		
		return user;
	}
	
}
