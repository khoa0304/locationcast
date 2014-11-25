package com.locationcast.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
 
public class SMSLab {

	
	 // Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = "AC4aa6086a5bb199e75a4dd4a324a2cbad";
	  public static final String AUTH_TOKEN = "73e0a2bff877ee748ff5ca060809e30c";
	 
	  public static void main(String[] args) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
	 
	    // Build a filter for the MessageList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("Body", "I love you EM, who is it? It is your fiancee'. Don't forget you are engaged. This is " +
	    		" sent from my test program. However, you can't reply to this message. <3"));
	    params.add(new BasicNameValuePair("To", "+14158127968"));
	    params.add(new BasicNameValuePair("From", "+14085569730"));
	 
	    MessageFactory messageFactory = client.getAccount().getMessageFactory();
	    Message message = messageFactory.create(params);
	    System.out.println(message.getSid());
	  }
}
