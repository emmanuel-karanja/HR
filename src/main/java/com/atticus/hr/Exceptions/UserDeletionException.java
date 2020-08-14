package com.atticus.hr.Exceptions;

public class UserDeletionException extends RuntimeException {
	public UserDeletionException() {
		this("User could not be deleted");
	}

	public UserDeletionException(String message) {
		// TODO Auto-generated constructor stub
		this(message, null);
	}

	public UserDeletionException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}
	

}
