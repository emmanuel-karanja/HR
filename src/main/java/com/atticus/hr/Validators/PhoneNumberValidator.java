package com.atticus.hr.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Service.ContactService;
import com.atticus.hr.domain.Contact;

@Component
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberUnique,String>{

	@Autowired
	private ContactService contactService;
	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		Contact contact;
		try {
		contact=contactService.findByPhoneNumber(phoneNumber);
		}catch(ResourceNotFoundException e) {
			return true;
		}
	
		if(contact != null)
			return false;
		return true;
	}

}
