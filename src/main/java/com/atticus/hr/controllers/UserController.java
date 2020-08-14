package com.atticus.hr.controllers;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Exceptions.UserCreationException;
import com.atticus.hr.Exceptions.UserDeletionException;
import com.atticus.hr.Repository.UserRepository;
import com.atticus.hr.Validators.UserValidator;
import com.atticus.hr.domain.User;

@CrossOrigin
@RestController
@RequestMapping(value="/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private UserValidator userValidator;
	//create
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user, BindingResult result ){
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		if(userRepository.findByName(user.getName()) != null)  
		{
			throw new UserCreationException("User with name "+ user.getName()+" already exists!");
		}
		if(userRepository.findByEmail(user.getEmail()) != null){
			throw new UserCreationException("User with email "+ user.getEmail()+" already exists!");
		}
		
		userRepository.save(user);
	    return new ResponseEntity<>(user, HttpStatus.CREATED);
		
	}
	//get all
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}
	//get one by id
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException
						("No posts found with Id =" +id));
	}
	
	
	@DeleteMapping(value="/users")
	public ResponseEntity<User> deleteUser(@RequestBody @Valid User user, BindingResult result) {
	
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		userRepository.findById(user.getId())
		  .orElseThrow(()-> new ResourceNotFoundException
				  ("No user found with id = "+ user.getId()));
		
		try {
		userRepository.delete(user);
	      }
		catch(Exception e) {
			throw new UserDeletionException("User could not be deleted");
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody @Valid User user, BindingResult result){
	
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		User updatedUser=userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"User doesn't exist"));
	   updatedUser.setName(user.getName());
	   updatedUser.setEmail(user.getEmail());
	   updatedUser.setPassword(user.getPassword());
	   updatedUser.setAddress(user.getAddress());
	   userRepository.saveAndFlush(updatedUser);
	   
	   return new ResponseEntity<User>(user, HttpStatus.CREATED);
		
	}
	
	
}
