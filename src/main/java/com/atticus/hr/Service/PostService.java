package com.atticus.hr.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.domain.Comment;
import com.atticus.hr.domain.Post;

@Service
public interface PostService {
	public Post createPost(Post post);
	public Comment createPostComment(Integer postId, Comment comment) throws ResourceNotFoundException;
	public Optional<Post> findPostById(Integer id) throws ResourceNotFoundException;
	public Comment findPostCommentById(Integer commentId) throws ResourceNotFoundException;
	public void deletePost(Post post) throws ResourceNotFoundException;
	public Post updatePost(Integer id,Post post) throws ResourceNotFoundException;
	public Iterable<Post> findAll();
	public void deletePostComment(Integer commentId) throws ResourceNotFoundException;
	public void updatePostComment(Integer id, Comment comment);
	

}
