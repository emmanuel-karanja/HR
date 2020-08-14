package com.atticus.hr.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atticus.hr.domain.Comment;
import com.atticus.hr.domain.Post;

public interface CommentRepository extends JpaRepository<Comment,Integer>{
	public Comment findCommentById(Integer id);

}
