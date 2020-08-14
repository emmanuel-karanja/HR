package com.atticus.hr.controllers;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atticus.hr.Exceptions.ErrorDetails;
import com.atticus.hr.Exceptions.PostDeletionException;
import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Service.PostService;
import com.atticus.hr.domain.Comment;
import com.atticus.hr.domain.Post;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {
	@Autowired
	private PostService postService;
	
	//create a single post//
	
	@PostMapping("")
	public ResponseEntity<Post> createPost(@RequestBody @Valid Post post, BindingResult result) {	
		if(result.hasErrors()) {
			throw new ValidationException(result);
		}		
		Post savedPost=postService.createPost(post);				
		return new ResponseEntity<>(savedPost, HttpStatus.CREATED);	
	}
	
	@GetMapping("")
	public ResponseEntity<Iterable<Post>> listPosts(){
		return ResponseEntity.ok(postService.findAll());
	}
	
	@GetMapping("/{postid}/comments")
	public ResponseEntity<List<Comment>> getPostCommentsById(@PathVariable("postid") Integer postId){
		Post post=postService.findPostById(postId)
			    .orElseThrow(()-> new ResourceNotFoundException
			    		("No posts found with id ="+postId));
		return ResponseEntity.ok(post.getComments());
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Optional<Post>> getPost(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(postService.findPostById(id));				
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") Integer id, @RequestBody @Valid Post post, 
			BindingResult result) {		
		if(result.hasErrors())
			throw new ValidationException(result);
		return ResponseEntity.ok(postService.updatePost(id,post));	
	}
	
	@DeleteMapping(value="/{id}")
	public void deletePost(@PathVariable Integer id) {
		Post post=postService.findPostById(id)
				    .orElseThrow(()->new ResourceNotFoundException
				    		("No posts found with Id= "+id));		
		try{
			postService.deletePost(post);
		}
		catch(Exception e) {
			throw new PostDeletionException("Post with id ="+id+"could not be deleted");
		}
	}
	

	@PostMapping("/{id}/comments")
	public ResponseEntity<Comment> createPostComment(@PathVariable Integer id, @RequestBody @Valid Comment comment,
			BindingResult result) {
		if(result.hasErrors())
			throw new ValidationException(result);
		postService.createPostComment(id, comment);
		return ResponseEntity.ok(comment);
	}
	
	@PutMapping("/{id}/comments")
	public ResponseEntity<Comment> updatePostComment(@PathVariable Integer id, @RequestBody @Valid Comment comment,
			BindingResult result) {
		if(result.hasErrors())
			throw new ValidationException(result);
		postService.updatePostComment(id, comment);
		return ResponseEntity.ok(comment);
		
	}
	@DeleteMapping("/{postId}/comments/{commentId}")
	public void deletePostComment(@PathVariable("postId") Integer postId,
			@PathVariable("commentId") Integer commentId) {
		postService.deletePostComment(commentId);
	}
	
	@ExceptionHandler(PostDeletionException.class)
	public ResponseEntity<?> servletRequestBindingException(PostDeletionException e){
		ErrorDetails errorDetails=new ErrorDetails();
		errorDetails.setErrorMessage(e.getMessage());
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		e.printStackTrace(pw);
		errorDetails.setDevErrorMessage(sw.toString());
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	

}
