package com.atticus.hr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.atticus.hr.domain.Post;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment =SpringBootTest.WebEnvironment.DEFINED_PORT)
class RestSocialTest {

	private static final String ROOT_URL="http://localhost:8080/api";
	RestTemplate restTemplate= new RestTemplate();
	
	@Test
	public void testGetAllPosts() {
		ResponseEntity<Post[]> responseEntity= restTemplate.getForEntity(ROOT_URL+"/posts",Post[].class);
		List<Object> posts= Arrays.asList(responseEntity.getBody());
		assertNotNull(posts);
	}
	
	@Test
	public void testGetPostById() {
		Post post=restTemplate.getForObject(ROOT_URL+"/posts/1", Post.class);
		assertNotNull(post);
	}
	
	
	@Test
	public void testCreatePost() {
		Post post= new Post();
		post.setTitle("Solving some of the problems I had created");
		post.setContent("You cannot solve a problem at the same level of stupid in which you created it");
		
		
		ResponseEntity<Post> postResponse=restTemplate.postForEntity(ROOT_URL+"/posts",post, Post.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());	
	}
	
	
	@Test
	public void testUpdatePost() {
		int id =2;
		Post post=restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
		
		post.setContent("This is my updated post1");
		
		
		restTemplate.put(ROOT_URL+"/posts/"+id, post);
		assertNotNull(post);
		
		Post updatedPost=restTemplate.getForObject(ROOT_URL+"/posts/"+id,Post.class);
		assertNotNull(updatedPost);
	}
	
	@Test
	public void testDeletePost() {
		int id=2;
		Post post=restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
		assertNotNull(post);
		
		restTemplate.delete(ROOT_URL+"/posts/"+id);
		
		try {
			post=restTemplate.getForObject(ROOT_URL+"/posts/"+id, Post.class);
		}
		catch(final HttpClientErrorException e) {
			assertEquals(e.getStatusCode(),HttpStatus.NOT_FOUND);
		}
	}
	

}
