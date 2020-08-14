package com.atticus.hr.controllers;

import org.springframework.validation.BindingResult;

public class ValidationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7703039266874355649L;
	/**
	 * 
	 */

	BindingResult result;
	public ValidationException() {
		this("Validation Errors In User");
	}

	public ValidationException(String message) {
		// TODO Auto-generated constructor stub
		this(message,null);
		
	}

	public ValidationException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
	
	public ValidationException(BindingResult result) {
		this.result=result;
	}
	public BindingResult getBindingResult() {
		return this.result;
	}

}
