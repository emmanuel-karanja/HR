package com.atticus.hr.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Repository.CommentRepository;
import com.atticus.hr.Repository.PostRepository;
import com.atticus.hr.domain.Comment;
import com.atticus.hr.domain.Post;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Transactional
	@Override
	public Post createPost(Post post) {
		// TODO Auto-generated method stub
		return postRepository.save(post);
	}

	@Transactional(rollbackFor= ResourceNotFoundException.class)
	@Override
	public Comment createPostComment(Integer postId, Comment comment) {
		// TODO Auto-generated method stub
		Post post=postRepository.findPostById(postId);
			    
		comment.setPost(post);
	post.getComments().add(comment);
	
	postRepository.save(post);
	return commentRepository.save(comment);

	}

	@Transactional(readOnly=true)
	@Override
	public Optional<Post> findPostById(Integer id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Optional<Post> post= postRepository.findById(id);
		if(post == null)
			throw new ResourceNotFoundException("No post with with id = "+id+"could be found");
		return post;
	}

	@Transactional(readOnly=true)
	@Override
	public Comment findPostCommentById(Integer commentId) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return commentRepository.findCommentById(commentId);
	}

	@Transactional(rollbackFor=ResourceNotFoundException.class)
	@Override
	public void deletePost(Post post) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		postRepository.delete(post);
	}
	
    @Transactional(rollbackFor=ResourceNotFoundException.class)
	@Override
	public Post updatePost(Integer id,Post post) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Post updatedPost=postRepository.findPostById(id);
		updatedPost.updateTitle(post.getTitle());
		updatedPost.updateContent(post.getContent());
		return postRepository.save(updatedPost);
		
	}

    @Transactional(readOnly=true)
	@Override
	public Iterable<Post> findAll() {
		// TODO Auto-generated method stub
		return postRepository.findAll();
	}
	
    @Transactional
    @Override
	public void deletePostComment(Integer commentId) {
		commentRepository.deleteById(commentId);
		
	}

    @Transactional
	@Override
	public void updatePostComment(Integer id, Comment comment) {
		// TODO Auto-generated method stub
		commentRepository.save(comment);
		
	}

}
