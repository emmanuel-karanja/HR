package com.atticus.hr.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atticus.hr.domain.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {
	
	public Post findPostById(Integer id);

}
