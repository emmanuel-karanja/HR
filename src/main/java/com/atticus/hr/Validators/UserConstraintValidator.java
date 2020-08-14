package com.atticus.hr.Validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atticus.hr.Repository.UserRepository;
import com.atticus.hr.domain.User;

@Component
public class UserConstraintValidator implements ConstraintValidator<CheckUserIsUnique, User>{
 
	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean isValid(User value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		String email=value.getEmail();
		
		User userByEmail;
		try {
		   userByEmail=userRepository.findByEmail(email);
		}
		catch(Exception e) {
			return true;
		}
		if(userByEmail != null)
			return false;
		return true;
	}

}
