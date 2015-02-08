/**
 * 
 */
package com.locationcast.constant;

/**
 * @author Khoa
 *
 */
public class LocationCastConstant {

	public static final int SESSION_EXPIRATION_IN_SECOND = 30;

	public static final String APPLICATION_JSON_TYPE = "application/json";
	
	public static class USER_REST_SERVICE_PATH{
		
		public static final String USER_SERVICE_PATH = "/rest/user";
		
		public static final String USER_REGISTER_PATH =  "/register";
		
		public static final String USER_SCHEMA_PATH =  "/schema";
		
	}
	
	
    public static class CONVERSATION_REST_SERVICE_PATH{
		
		public static final String CONVERSATION_SERVICE_PATH = "/rest/conversation";
		
		public static final String CONVERSATION_CREATE_PATH = "/create";
		
		public static final String CONVERSATION_SCHEMA_PATH = "/schema";
		
		public static final String CONVERSATION_PUBLISH_PATH = "/publish";
		
	}
	

}
