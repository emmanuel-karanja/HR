package com.atticus.hr.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name="POSTS")
public class Post  extends BaseEntity{

	private static final long serialVersionUID = 6840160666477935984L;

   
	@NotBlank
	@NotNull
	@Column(name="title",nullable=false,length=150)
	private String title;
	
   
	@NotBlank
	@NotNull
	@Lob
    @Column(name="content",nullable=false,columnDefinition="TEXT")
	private String content;
	
    
    
    @JsonSerialize(using=LocalDateTimeSerializer.class)
   	@JsonFormat(pattern="dd:MM:yyyy HH:mm:ss")
	@Column(name="created_on", insertable=true, updatable=false)
	private LocalDateTime createdOn;
	
	//@Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonFormat(pattern="dd:MM:yyyy HH:mm:ss")
	@Column(name="updated_on")
	private LocalDateTime updatedOn;
	
    @JsonManagedReference
	@OneToMany(mappedBy="post", fetch= FetchType.EAGER, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<Comment> comments;
	
	public Post() {
		this.title="";
		this.content="";
	}

	public Post(String title, String content) {
		this.title=title;
		this.content=content;
	}
	@PrePersist
	public void onCreate() {
		this.setCreatedOn(LocalDateTime.now());
		this.setUpdatedOn(LocalDateTime.now());
	}
	
	@PreUpdate
	public void onUpdate() {
		this.setUpdatedOn(LocalDateTime.now());
	}

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content=content;
	}
	
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(LocalDateTime localDateTime) {
		this.createdOn=localDateTime;
	}
	
	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}
	
	public void setUpdatedOn(LocalDateTime localDateTime) {
		this.updatedOn=localDateTime;
	}
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments=comments;
	}
	
	public void updateContent(String content) {
		this.content=content;
	}
	
	public void updateTitle(String title) {
		this.title=title;
	}
	
	public void update(String title, String content) {
		this.title=title;
		this.content=content;
	}
	public static class Builder{
		private Post built;
		
		public Builder(String title, String content) {
			built=new Post();
			built.setTitle(title);
			built.setContent(content);
			
		}
		
		public Post build() {
			return built;
		}
	
	}
	
	public static Builder getBuilder(String title, String content) {
		return new Builder(title,content);
		
	}
	
	
	

}
