/**
 * 
 */
package com.locationcast.security.rest;

import org.springframework.security.core.Authentication;
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
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(authentication != null){
		
			String principal = (String) authentication.getPrincipal();
			return principal;
		}
		
		return null;
	}
	
	
}
