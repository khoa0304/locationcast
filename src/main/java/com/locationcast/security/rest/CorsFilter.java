/**
 * 
 */
package com.locationcast.security.rest;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Khoa
 *
 */
public class CorsFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws ServletException, IOException {

		HttpServletResponse response = (HttpServletResponse) res;
		HttpServletRequest request = (HttpServletRequest) req;

		String method = request.getMethod();
		if (request.getHeader("Access-Control-Request-Method") != null
				&& "OPTIONS".equals(method)) {

			String headers = request
					.getHeader("Access-Control-Request-Headers");
			String authorization = request.getHeader("authorization");

		
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods",
					"GET,POST,PUT,DELETE,OPTIONS");
			response.addHeader("Access-Control-Allow-Headers", headers);
			response.addHeader("Access-Control-Max-Age", "3600");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			
			

		}

		
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}
}
