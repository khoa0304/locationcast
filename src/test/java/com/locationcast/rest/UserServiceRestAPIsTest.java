/**
 * 
 */
package com.locationcast.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationcast.domain.User;

/**
 * @author Khoa
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@WebAppConfiguration
//@IntegrationTest("server.port:8080")
//@PropertySource({ "classpath:application.properties"})
@ContextConfiguration(locations={"classpath*:META-INF/spring/integration/http-outbound-config.xml"})
public class UserServiceRestAPIsTest {

	@Autowired
	private RestTemplate restTemplate;

	//private MockRestServiceServer mockServer;

	private HttpMessageConverterExtractor<User> responseExtractor;

	
	@Autowired
	private ObjectMapper jaxbJacksonObjectMapper;

	@Before
	public void setUp() throws Exception {
	//	this.mockServer = MockRestServiceServer.createServer(restTemplate);
		responseExtractor = new HttpMessageConverterExtractor<User>(User.class,
				restTemplate.getMessageConverters());
		
	}

	@After
	public void tearDown(){}
	
	@Test
	public void testRest() {
		final String fullUrl = "http://localhost:8080/LocationCast/rest/user/test";
		User user = restTemplate.execute(fullUrl, HttpMethod.GET, new RequestCallback() {

			@Override
			public void doWithRequest(ClientHttpRequest request)
					throws IOException {
				HttpHeaders headers = getHttpHeadersWithUserCredentials(request);
				headers.add("Accept", "application/json");
			}
		}, responseExtractor, new Object());
		
		System.out.print(user);
	}

	private HttpHeaders getHttpHeadersWithUserCredentials(
			ClientHttpRequest request) {
		return (getHttpHeadersWithUserCredentials(request.getHeaders()));
	}

	private HttpHeaders getHttpHeadersWithUserCredentials(HttpHeaders headers) {

		String username = "user";
		String password = "welcome";

		String combinedUsernamePassword = username + ":" + password;
		byte[] base64Token = Base64.encode(combinedUsernamePassword.getBytes());
		String base64EncodedToken = new String(base64Token);
		// adding Authorization header for HTTP Basic authentication
		headers.add("Authorization", "Basic  " + base64EncodedToken);

		return headers;
	}

}
