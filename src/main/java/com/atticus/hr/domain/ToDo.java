package com.atticus.hr.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name="todos")
public class ToDo {
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String id;
	
	
	@NotNull
	@NotBlank
	@Column(name="description")
	private String description;
	
	@Column(name="created",insertable=true,updatable=false)
	@JsonSerialize(using= LocalDateTimeSerializer.class)
	@JsonFormat(pattern="dd:MM:yyy HH:mm:ss")
	private LocalDateTime createdDate;
	
	@Column(name="modified")
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonFormat(pattern="dd:MM:yyyy HH:mm:ss")
	private LocalDateTime modifiedDate;
	
	@Column(name="complete")
	private boolean completed;
	
	public ToDo(String description) {
		this.description=description;
	}
	
	//empty constructor for Json
	public ToDo() {
		
	}
	
	@PrePersist
	public void onCreate() {
		this.id = UUID.randomUUID().toString();
		this.setCreatedDate(LocalDateTime.now());
		this.setModifiedDate(LocalDateTime.now());
	}
	
	@PreUpdate
	public void onUpdate() {		
	 this.setModifiedDate(LocalDateTime.now());
	}
	
	public String getId() {
		return this.id;
	}
	
	public void setId(String uuid) {
		this.id=uuid;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}
	
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate=createdDate;
	}
	public LocalDateTime getModifiedDate() {
		return this.modifiedDate;
	}
    
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate=modifiedDate;
	}
	
	public boolean isCompleted() {
		return this.completed;
	}
	
	public void complete() {
		this.completed=true;
	}
	
	public void notComplete() {
		this.completed=false;
	}
}
