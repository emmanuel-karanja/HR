package com.atticus.hr.Exceptions;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;


public class ErrorDetails {
	private String errorCode;
	private String errorMessage;
	private String devErrorMessage;
	
	private Map<String, Object> additionalData = new HashMap<>();
	
	public String getErrorCode() {
		return this.errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode=errorCode;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage=errorMessage;
	}
	
	public String getDevErrorMessage() {
		return this.devErrorMessage;
	}
	
	public void setDevErrorMessage(String devErrorMessage) {
		this.devErrorMessage=devErrorMessage;
	}
	
	public Map<String,Object> getAdditionalData() {
		return this.additionalData;
		
	}
	
	public void setAdditionalData(Map<String, Object> additionalData) {
		this.additionalData=additionalData;
	}

}
