/**
 * 
 */
package com.locationcast.rest;

import static com.locationcast.test.data.UserTestData.email;
import static com.locationcast.test.data.UserTestData.nickName;
import static com.locationcast.test.data.UserTestData.password;
import static com.locationcast.test.data.UserTestData.userName;
import static com.locationcast.util.LocationCastConstant.USER_REST_SERVICE_PATH.USER_REGISTER_PATH;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.fest.assertions.AssertExtension;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.locationcast.domain.User;
import com.locationcast.exception.DuplicatedDomainModelException;

/**
 * @author Khoa
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:META-INF/spring/integration/http-outbound-config.xml","classpath*:applicationContext.xml" })

public class UserServiceRestAPIsTest extends AbstractRestServiceTest {

	
	@Autowired
	protected  ApplicationContext applicationContext;
	
	@Autowired
	private RestTemplate restTemplate;

	// private MockRestServiceServer mockServer;

	private HttpMessageConverterExtractor<User> responseExtractor;

	@Autowired
	private ObjectMapper jaxbJacksonObjectMapper;


	protected MongoOperations mongoOperation;
	
	@Before
	public void setUp() throws Exception {
		// this.mockServer = MockRestServiceServer.createServer(restTemplate);
		responseExtractor = new HttpMessageConverterExtractor<User>(User.class,
				restTemplate.getMessageConverters());
		
		mongoOperation = (MongoOperations) applicationContext
				.getBean("locationcastdb");
		
		assertNotNull(mongoOperation);
	}

	@After
	public void tearDown() {
	}

	// @Test
	// public void testRest() {
	//
	// final String fullUrl = userServiceURL;
	// User user = restTemplate.execute(fullUrl, HttpMethod.POST, new
	// RequestCallback() {
	//
	// @Override
	// public void doWithRequest(ClientHttpRequest request)
	// throws IOException {
	// HttpHeaders headers = getHttpHeadersWithUserCredentials(request);
	// headers.add("Accept", "application/json");
	// }
	// }, responseExtractor, getUser());
	//
	// System.out.print(user);
	// }
	
	final String fullUrl = getRestURLPrefix() + USER_REGISTER_PATH;
	
	@Test
	public void testRegisterUser() {

		dropAndCreateUserCollection();
		HttpEntity<User> entity = getHttpEntityUser();
		
		try {
			ResponseEntity<User> result = restTemplate.exchange(fullUrl,HttpMethod.POST, entity, User.class);
			User returnedUser = result.getBody();
			assertEquals(getUser().getAliasName(), returnedUser.getAliasName());
		} catch (Exception e) {
			assertNotNull("Error resgistering new user", e);
		}
	}

	@Test 
	public void testRegisterDuplicatedUser() {

		HttpEntity<User> entity = getHttpEntityUser();
		HttpClientErrorException e = null;
		try {
			ResponseEntity<User> result = restTemplate.exchange(fullUrl,HttpMethod.POST, entity, User.class);
		    assertFalse("Should not reach here. Expect DuplicatedDomainModelException.",true);
		} catch (HttpClientErrorException e1) {
			e = e1;
		}
		System.out.println( e.getResponseBodyAsString());
		assertNotNull("Expected DuplicatedDomainModelException when resgistering duplicated user", e.getResponseBodyAsString());
	}
	
	protected HttpEntity<User> getHttpEntityUser(){
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers = getHttpHeadersWithUserCredentials(headers);
		headers.setAccept(acceptableMediaTypes);
		headers.setContentType(MediaType.APPLICATION_JSON);
		// Pass the new person and header
		HttpEntity<User> entity = new HttpEntity<User>(getUser(), headers);
		return entity;
	}
	private void dropAndCreateUserCollection(){
		if(mongoOperation.collectionExists(User.class)){
			mongoOperation.dropCollection(User.class);
			mongoOperation.createCollection(User.class);
		}
	}
	

	private User getUser() {
		User user = new User();
		user = user.setUserName(userName).setAliasName(nickName)
				.setEmail(email).setPassword(password);

		return user;
	}

}
