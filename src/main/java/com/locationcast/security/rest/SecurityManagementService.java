/**
 * 
 */
package com.locationcast.security.rest;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * @author Khoa
 *
 */
@Service
public class SecurityManagementService {

	
	public String getCurrentSessionPrincipalName(){
		
		String principal = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return principal;
	}
	
	
}
