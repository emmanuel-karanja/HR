package com.atticus.hr.Exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldValidationErrorDetails {
   private String errorTitle;
   private int errorStatus;
   private String errorDetail;
   private long timeStamp;
   private String errorPath;
   private String devMessage;
   
   private Map<String, List<FieldValidationError>> errors=new HashMap<String,List<FieldValidationError>>();
   
   
   public String getErrorTitle() {
	   return this.errorTitle;
   }
   
   public void setErrorTitle(String errorTitle) {
	   this.errorTitle=errorTitle;
   }
   public int getErrorStatus() {
	   return this.errorStatus;
   }
   
   public void setErrorStatus(int errorStatus) {
	   this.errorStatus=errorStatus;
   }
   
   public String getErrorDetail() {
	   return this.errorDetail;
   }
   public void setErrorDetail(String errorDetail) {
	   this.errorDetail=errorDetail;
   }
   
   public long getTimeStamp() {
	   return this.timeStamp;
   }
   
   public void setTimeStamp(long timeStamp) {
	   this.timeStamp=timeStamp;
   }
   
   public String getErrorPath() {
	   return this.errorPath;
   }
   public void setErrorPath(String errorPath) {
	   this.errorPath=errorPath;
   }
   
   public String getDevMessage() {
	   return this.devMessage;
   }
   public void setDevMessage(String devMessage) {
	   this.devMessage=devMessage;
   }
   
   public Map<String,List<FieldValidationError>> getErrors(){
	   return this.errors;
   }
   public void setErrors(Map<String,List<FieldValidationError>> errors) {
	   this.errors=errors;
   }
}
