/**
 * 
 */
package com.locationcast.rest.exceptionHandler;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.locationcast.domain.ErrorMessage;
import com.locationcast.exception.DuplicatedDomainModelException;
import com.locationcast.exception.InvalidDomainModelException;

/**
 * @author Khoa
 *
 */
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends AbstractExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomResponseEntityExceptionHandler.class);
 
    @ExceptionHandler(InvalidDomainModelException.class)
    @ResponseStatus(value=HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ErrorMessage handleInvalidEntity(InvalidDomainModelException e){
    	String errorMessage = getResourceBundle().getMessage(e.getMessageKey(), new Object[]{ e.getDomainModel()},Locale.getDefault());
    	logger.error(errorMessage);
    	ErrorMessage em = new ErrorMessage(errorMessage);
    	return em;
    }
    
    
    @ExceptionHandler(DuplicatedDomainModelException.class)
    @ResponseStatus(value=HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage handleDuplicatedEntity(DuplicatedDomainModelException e){
    	
    	logger.warn(e.toString());
    	ErrorMessage em = new ErrorMessage(e.toString());
    	return em;
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value=HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorMessage handleIllegalArgumentException(IllegalArgumentException e){
    	
    	logger.warn(e.toString());
    	ErrorMessage em = new ErrorMessage(e.toString());
    	return em;
    }
}
