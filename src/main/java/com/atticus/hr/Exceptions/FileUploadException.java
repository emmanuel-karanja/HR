package com.atticus.hr.Exceptions;

public class FileUploadException extends Exception {
	public FileUploadException() {
		this("file upload failed");
	}

	public FileUploadException(String message) {
		// TODO Auto-generated constructor stub
		this(message,null);
	}

	public FileUploadException(String message, Throwable cause) {
		// TODO Auto-generated constructor stub
		super(message, cause);
	}

}
