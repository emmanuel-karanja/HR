package com.atticus.hr.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException() {
		this("Resource Not Found");
	}

	public ResourceNotFoundException(String message) {
		// TODO Auto-generated constructor stub
		this(message,null);
	}

	public ResourceNotFoundException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
	}
	
	

}
