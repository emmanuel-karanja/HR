package com.atticus.hr.Exceptions;

import java.awt.TrayIcon.MessageType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.atticus.hr.controllers.ValidationException;

@ControllerAdvice
public class RestValidationHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ValidationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<FieldValidationErrorDetails> handleValidationErrors(
			ValidationException mNotValidException,
			HttpServletRequest request){
		
		FieldValidationErrorDetails fErrorDetails= new FieldValidationErrorDetails();
		fErrorDetails.setTimeStamp(new Date().getTime());
		fErrorDetails.setErrorStatus(HttpStatus.BAD_REQUEST.value());
		fErrorDetails.setErrorTitle("Field Validation Error");
		fErrorDetails.setErrorDetail("Input Field Validation Failed");
		fErrorDetails.setDevMessage(mNotValidException.getClass().getName());
		fErrorDetails.setErrorPath(request.getRequestURI());
		
		BindingResult result=mNotValidException.getBindingResult();
		
		List<FieldError> fieldErrors=result.getFieldErrors();
		
		for(FieldError error: fieldErrors) {
			FieldValidationError fError=processFieldError(error);
			
			List<FieldValidationError> fValidationErrorsList=
					fErrorDetails.getErrors().get(error.getField());
			
			if(fValidationErrorsList == null) {
				fValidationErrorsList= new ArrayList<FieldValidationError>();
			}
				fValidationErrorsList.add(fError);
				fErrorDetails.getErrors().put(error.getField(),fValidationErrorsList);				
			}
		return new ResponseEntity<FieldValidationErrorDetails>(fErrorDetails,HttpStatus.BAD_REQUEST);
		}
	

	private FieldValidationError processFieldError(final FieldError error) {
		// TODO Auto-generated method stub
		FieldValidationError fieldValidationError= new FieldValidationError();
		if(error !=null) {
			
			fieldValidationError.setField(error.getField());
			fieldValidationError.setMessage(error.getDefaultMessage());
			fieldValidationError.setType(MessageType.ERROR);
		}
		return fieldValidationError;
	}

}
