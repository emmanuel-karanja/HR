package com.atticus.hr.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.atticus.hr.Repository.UserRepository;
import com.atticus.hr.domain.User;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user=(User) target;
		String email=user.getEmail();
		User userByEmail=userRepository.findByEmail(email);
		if(userByEmail != null) {
			errors.rejectValue("email", 
					"email.exists",
					 new Object[] {email},
					 "Email "+email+" already in use");
		}
		
	}
	

}
