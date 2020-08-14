package com.atticus.hr.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.atticus.hr.Service.ContactDTO;
import com.atticus.hr.Service.ContactService;
import com.atticus.hr.domain.Contact;
import com.atticus.hr.domain.User;

@Component
public class ContactConstraintValidator implements Validator{

	@Autowired
	private ContactService contactService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ContactDTO contact=(ContactDTO) target;
		String email=contact.getEmail();
	    String phoneNumber=contact.getPhoneNumber();
		if((contactService.findByEmail(email)!= null)) {
			errors.rejectValue("email", 
					"email.exists",
					 new Object[] {email},
					 "Email "+email+" already in use");
		}
		if(contactService.findByPhoneNumber(phoneNumber)!= null) {
			errors.rejectValue("PhoneNumber", 
					"phonenumber.exists",
					new Object[] {phoneNumber},
					"PhoneNumber "+ phoneNumber+" already in use");
		}
		
	
		
		
		
	
		
		
	}
}
