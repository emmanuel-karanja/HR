package com.atticus.hr.controllers;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.atticus.hr.Repository.ToDoRepository;
import com.atticus.hr.domain.ToDo;
import com.atticus.hr.domain.ToDoBuilder;

@RestController
@RequestMapping("/api")
public class ToDoController {
	
	@Autowired
	private ToDoRepository toDoRepository;
	
	@GetMapping("/todos")
	public ResponseEntity<Iterable<ToDo>> getAllToDos(){
		return  ResponseEntity.ok(toDoRepository.findAll());
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Optional<ToDo>> getOne(@PathVariable("id") String id){
		return ResponseEntity.ok(toDoRepository.findById(id));
	}
	
	@PatchMapping("/todos")
	public ResponseEntity<ToDo> setCompleted(@RequestBody @Valid ToDo toDo, BindingResult result){
		
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		ToDo updatedToDo=toDoRepository.getOne(toDo.getId());
		updatedToDo.complete();
		updatedToDo.setDescription(toDo.getDescription());
		toDoRepository.save(updatedToDo);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedToDo.getId()).toUri();
		
		return ResponseEntity.ok().header("Location", location.toString()).build();	
	}
	@PatchMapping("/todos/{id}")
	public ResponseEntity<ToDo> setCompleted(@PathVariable("id") String id){
		
		
		ToDo updatedToDo=toDoRepository.getOne(id);
		updatedToDo.complete();
		toDoRepository.save(updatedToDo);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.buildAndExpand(updatedToDo.getId()).toUri();
		
		return ResponseEntity.ok().header("Location", location.toString()).build();	
	}
	
	@RequestMapping(value="/todos", method= {RequestMethod.POST, RequestMethod.PUT})
	public ResponseEntity<ToDo> createToDo(@RequestBody @Valid ToDo toDo, BindingResult result){
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		ToDo createdToDo=toDoRepository.save(toDo);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdToDo.getId()).toUri();
		
		return ResponseEntity.ok().header("Location", location.toString()).build();
	}
	
	@DeleteMapping("/todos")
	public ResponseEntity<ToDo> deleteToDo(@RequestBody ToDo toDo){
		toDoRepository.delete(toDo);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<ToDo> deleteToDoById(@PathVariable("id") String id){
		toDoRepository.delete(ToDoBuilder.create().withId(id).build());
		return ResponseEntity.noContent().build();
	}
	

}
