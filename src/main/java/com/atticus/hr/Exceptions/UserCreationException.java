package com.atticus.hr.Exceptions;

public class UserCreationException extends RuntimeException {
	public UserCreationException() {
		this("User Creation Failure");
	}

	public UserCreationException(String message) {
		// TODO Auto-generated constructor stub
		this(message,null);
	}

	public UserCreationException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message,cause);
	}

}
