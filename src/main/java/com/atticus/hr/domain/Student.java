package com.atticus.hr.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@Size(min=2, max=40)
	private String name;
	
	@NotNull
	@Email
	private String email;
	
	private boolean openHouse;
	private boolean subscribe;
	
	@Size(min=0, max=300)
	private String comments;
	
	public Integer getId() {
		return id;
		}
    public void setId(Integer id) {
		this.id = id;
		}
	public String getName() {
		return name;
		}
	
	public void setName(String name) {
		this.name = name;
		}
		public String getEmail() {
		return email;
		}
		public void setEmail(String email) {
		this.email = email;
		}
		public Boolean getOpenHouse() {
		return openHouse;
		}
		public void setOpenHouse(Boolean openHouse) {
		this.openHouse = openHouse;
		}
		public Boolean getSubscribe() {
		return subscribe;
		}
		public void setSubscribe(Boolean subscribe) {
		this.subscribe = subscribe;
		}
		public String getComments() {
		return comments;
		}
		public void setComments(String comments) {
		this.comments = comments;
		}

}
