package com.appsdeveloperblog.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.appsdeveloperblog.app.ws.ui.model.response.ErrorMessage;

//this annotation will help the application to consider this exception class across all the mapping annotations.
//If we do not have this annotation our application will not listen this exception class
@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
	
	//@ExceptionHandler value is exception class
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR
				);
	}
	
	
	@ExceptionHandler(value = {NullPointerException.class,UserServiceException.class})
	public ResponseEntity<Object> handleNullPointerException(Exception ex, WebRequest request){
		
		String errorMessageDescription = ex.getLocalizedMessage();
		
		if(errorMessageDescription == null) errorMessageDescription = ex.toString();
		
		
		ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
		
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR
				);
	}

	
	
}
