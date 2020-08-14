package com.atticus.hr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Exceptions.UserCreationException;
import com.atticus.hr.Repository.AuthUserRepository;
import com.atticus.hr.domain.AuthUser;
import com.atticus.hr.domain.Role;

@RestController
@RequestMapping("/api/authusers")
public class AuthUserController {
	@Autowired
	private AuthUserRepository userRepository;
	
	@GetMapping("")
	public ResponseEntity<List<AuthUser>> getAll(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuthUser> getById(@PathVariable("id") Integer id){
		return ResponseEntity.ok(userRepository.findUserById(id));
	}
	
  /*@GetMapping("/{email}")
	public ResponseEntity<AuthUser> getByEmail(@PathVariable("email") String email){
		AuthUser user;
		try {
			user=userRepository.findByEmail(email);
		}catch(Exception e) {
			throw new ResourceNotFoundException("User with email ="+email+"could not be found");
		}
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("")
	public ResponseEntity<AuthUser> createUser(@RequestBody AuthUser user) {
		
		AuthUser savedUser= new AuthUser();
		savedUser.getRoles().add(new Role());
		try {
			savedUser=userRepository.save(user);
		} catch(Exception e) {
			throw new UserCreationException("User could not be created");
		}
		
		return ResponseEntity.ok(savedUser);
	}*/
	
	

}
