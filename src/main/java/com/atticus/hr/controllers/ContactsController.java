package com.atticus.hr.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atticus.hr.Service.ContactDTO;
import com.atticus.hr.Service.ContactService;
import com.atticus.hr.Validators.ContactConstraintValidator;
import com.atticus.hr.domain.Contact;

@RestController
@RequestMapping("/api/contacts")
public class ContactsController {
	
	@Autowired
	private ContactService contactService;
	
	@Autowired
	private ContactConstraintValidator contactValidator;
	
	//get all contacts
	@GetMapping("")
	public ResponseEntity<List<Contact>> getAll(){
		return ResponseEntity.ok(contactService.findAll());
	}
	
	//get one contact
    @GetMapping("/{id}")
	public ResponseEntity<Contact> getOne(@PathVariable("id") Integer id){
		return ResponseEntity.ok(contactService.findById(id));
	}
    
    //create a contact 
    @PostMapping("")
    public ResponseEntity<Contact> createContact(@RequestBody @Valid ContactDTO added, BindingResult result){
    	contactValidator.validate(added, result);
    	if(result.hasErrors())
    		throw new ValidationException(result);
    	return ResponseEntity.ok(contactService.add(added));
    }
    
    //update a contact
    @PutMapping("")
    public ResponseEntity<Contact> updateContact(@RequestBody @Valid ContactDTO updated, BindingResult result){
    	if(result.hasErrors()) {
    		throw new ValidationException(result);
    	}
    		    
    	return ResponseEntity.ok(contactService.update(updated));
    }
    
    //delete a contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Contact> deleteById(@PathVariable("id") Integer id){
    	return ResponseEntity.ok(contactService.deleteById(id));
    }

}
