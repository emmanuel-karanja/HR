package com.atticus.hr.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;

@Entity
@Table(name="COMMENTS")
@Data
public class Comment extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2142504180211410584L;

	
	@NotBlank
	@NotNull
	@Column(name="name",nullable=false,length=150)
	private String name;
	
	@NotBlank
	@NotNull
    @Email
	@Column(name="email",nullable=false,length=150)
	private String email;
	
    
    @NotBlank
    @NotNull
	@Lob
	@Column(name="content",nullable=false, columnDefinition="TEXT")
	private String  content;
	
    
    @JsonSerialize(using=LocalDateTimeSerializer.class)
   	@JsonFormat(pattern="dd:MM:yyyy HH:mm:ss")
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
    @JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonFormat(pattern="dd:MM:yyyy HH:mm:ss")
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
    
    @JsonBackReference
    @ManyToOne(fetch= FetchType.EAGER, optional=false)
    @JoinColumn(name="post_id")
    private Post post;
	
	@PrePersist
	public void onCreate() {
		this.setCreatedOn(LocalDateTime.now());
		this.setUpdatedOn(LocalDateTime.now());
	}
	
	@PreUpdate
	public void onUpdate() {
		this.setUpdatedOn(LocalDateTime.now());
	}
	
	
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content=content;
	}
	
	public LocalDateTime getCreatedOn() {
		return this.createdOn;
	}
	
	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn=createdOn;
	}
	
	public LocalDateTime getUpdatedOn() {
		return this.updatedOn;
	}
	
	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn=updatedOn;
	}
	
	public Post getPost() {
		return this.post;
	}
	public void setPost(Post post) {
		this.post=post;
	}
	
	public void updateName(String name) {
		this.name=name;
	}
	
	public void updateContent(String content) {
		this.content=content;
	}

}
