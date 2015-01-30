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
import static com.locationcast.util.LocationCastConstant.USER_REST_SERVICE_PATH.*;
import static com.locationcast.util.LocationCastConstant.*;
import static com.locationcast.util.i18UserKeysConstant.*;
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
	
		User queriedUser = userFacade.findUserByIndexFields(user);
		
		if(queriedUser != null){
			throw new DuplicatedDomainModelException(user, DUPLICATED_USER_KEY);
		}
		
		userFacade.createUser(user);
		logger.info("Finished adding user %s",user);
		
		return user;
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
