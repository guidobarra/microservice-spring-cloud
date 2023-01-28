package com.guba.spring.microservice.exceptions;

import com.guba.spring.microservice.web.model.response.ErrorMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@Log4j2
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
		log.error("error in {}", request.getContextPath());

    	String errorMessageDescription = ex.getLocalizedMessage();
    	
    	if(errorMessageDescription == null) errorMessageDescription = ex.toString();
    	
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
    	
    	return new ResponseEntity<>(
    			errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
    @ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
    public ResponseEntity<Object> handleSpecificExceptions(Exception ex) {
        
    	String errorMessageDescription = ex.getLocalizedMessage();
    	
    	if(errorMessageDescription == null) errorMessageDescription = ex.toString();
    	
    	ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
    	
    	return new ResponseEntity<>(
    			errorMessage,
				new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR);
    	
    }
    
  
    
}
