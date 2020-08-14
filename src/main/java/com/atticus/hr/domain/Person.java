package com.atticus.hr.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Person {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id;
	
	@Version
	private Integer version;
	
	@Column(unique=true)
	private String email;
	
	private String name;
	private String password;
	private String role="USER";
	
	private boolean enabled=true;
	
	private LocalDate birthDay;
	
	@Column(insertable=true,updatable=false)
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	
	public Person() {
		
	}
	
	public Person(String email, String name, String password, String birthDay) {
		this.email=email;
		this.name=name;
		this.password=password;
		this.birthDay=LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
	}
	
	public Person(String email, String name, String password, LocalDate birthDay) {
		this.email=email;
		this.name=name;
		this.password=password;
		this.birthDay=birthDay;
	}
	
	public Person(String email, String name, String password, String birthDay, String role,
			boolean enabled) {
		this(email,name, password,birthDay);
		this.role=role;
		this.enabled=enabled;
	}
	
	@PrePersist
	void onCreate() {
		this.setCreatedDate(LocalDateTime.now());
		this.setModifiedDate(LocalDateTime.now());
	}
	
	@PreUpdate
	void onUpdate() {
		this.setModifiedDate(LocalDateTime.now());
	}
	
	public String getId() {
	   return this.id;
	}
	
	public void setId(final String id) {
		this.id=id;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(final String email) {
		this.email=email;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name=name;
	}
	
	public String getPassword() {
	   return this.password;
	}
	
	public void setPassword(final String password) {
		this.password=password;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void disable() {
		this.enabled=false;
	}
	
	public void enable() {
		this.enabled=true;
	}
	
	public LocalDate getBirthDay() {
		return this.birthDay;
	}
	
	public void setBirthDay(final String birthDay) {
		this.birthDay=LocalDate.parse(birthDay, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
	public void setBirthDay(final LocalDate birthDay) {
		this.birthDay=birthDay;
	}
	
	public LocalDateTime getCreatedDate() {
		return this.createdDate;
	}
	
	public void setCreatedDate(final LocalDateTime createdDate) {
		this.createdDate=createdDate;
	}
	
	public LocalDateTime getModifiedDate() {
		return this.modifiedDate;
	}
	
	public void setModifiedDate(final LocalDateTime modifiedDate) {
		this.modifiedDate=modifiedDate;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(final String role) {
		this.role=role;
	}

}
