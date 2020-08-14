package com.atticus.hr.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.atticus.hr.Exceptions.ErrorDetails;
import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Exceptions.UserCreationException;

@ControllerAdvice
public class RestGlobalExceptionHandler{
		
	@ExceptionHandler(ServletRequestBindingException.class)
	public ResponseEntity<?> servletRequestBindingException(ServletRequestBindingException e){
		ErrorDetails errorDetails= new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		errorDetails.setDevErrorMessage(getStackTraceAsString(e));	
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);		
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception e){
		ErrorDetails errorDetails=new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		errorDetails.setDevErrorMessage(getStackTraceAsString(e));		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(UserCreationException.class)
	public ResponseEntity<?> userCreationFailure(UserCreationException e){
		ErrorDetails errorDetails=new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		errorDetails.setDevErrorMessage(getStackTraceAsString(e));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
		
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException e){
		ErrorDetails errorDetails=new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		errorDetails.setDevErrorMessage(getStackTraceAsString(e));
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
	public String getStackTraceAsString( Exception e) {
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();				
	}
	

}
