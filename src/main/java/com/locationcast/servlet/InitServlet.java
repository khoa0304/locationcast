package com.locationcast.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitServlet extends HttpServlet{

	
	private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	private static ClassPathXmlApplicationContext ctx = null;
	
	public void init() throws ServletException{
		 
		
		if(ctx == null){
			
			logger.info("Initialize LocaitonCast application spring container");
			
			ctx = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
			
			logger.info("Finished initializing LocaitonCast application spring container");
		}
	    
	  
   
	}

	
	public static ClassPathXmlApplicationContext getApplicationContext() {
		final ClassPathXmlApplicationContext context = ctx;
		return context;
	}


}
