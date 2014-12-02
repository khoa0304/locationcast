/**
 * 
 */
package com.locationcast.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.crypto.codec.Base64;

/**
 * @author Khoa
 *
 */
public abstract class AbstractRestServiceTest {

	public static final String HOST = "localhost";
	public static final String PORT = "8080";
	public static final String CONTEXT = "LocationCast";
	
	
	
	public String getRestURLPrefix(){
		String url = String.format("http://%s:%s/%s", HOST,PORT,CONTEXT);
		return url;
	}
	
	protected HttpHeaders getHttpHeadersWithUserCredentials(HttpHeaders headers) {
        // User is defined in applicationContext-security.xml for now
		String username = "user";
		String password = "welcome";

		String combinedUsernamePassword = username + ":" + password;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String(base64Token);
		// adding Authorization header for HTTP Basic authentication
		headers.add("Authorization", "Basic  " + base64EncodedToken);

		return headers;
	}
	
	protected HttpHeaders getHttpHeadersWithUserCredentials(
			ClientHttpRequest request) {
		return (getHttpHeadersWithUserCredentials(request.getHeaders()));
	}
}
