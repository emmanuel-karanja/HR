package com.atticus.hr.Exceptions;

public class PostDeletionException extends RuntimeException {
	public PostDeletionException() {
		this("Post could not be Deleted");
	}
	public PostDeletionException(String message) {
		this(message, null);
	}
	public PostDeletionException(String message, Throwable cause){
		super(message,cause);
	}

}
