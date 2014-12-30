/**
 * 
 */
package com.locationcast.security.rest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Khoa
 *
 */
public class CorsFilter extends OncePerRequestFilter {
	 
	
	private static Set<String> whiteList = new HashSet<String>();
	
	static {
		whiteList.add("http://localhost:8080");
		whiteList.add("http://127.0.0.1:8080");
				
	}
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                                    throws ServletException, IOException {
        
    	
    	
    	String method = request.getMethod();
    	if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {
        	
        	String headers = request.getHeader("Access-Control-Request-Headers");
        	String authorization = request.getHeader("authorization");
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
            response.addHeader("Access-Control-Max-Age", "1800");
            response.addHeader("Content-Type", "application/json");
           
        }
        
        String origin = request.getHeader("Origin");
        
        if (origin != null && whiteList.contains(origin)) {
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Credentials", "true");
        }
        filterChain.doFilter(request, response);
    }
}
