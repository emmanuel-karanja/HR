package com.atticus.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.atticus.hr.Validators.CheckUserIsUnique;

@Entity
@Table(name="USERS")
//@CheckUserIsUnique(message=" User with that email already exists")
public class User extends BaseEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5778276171268662426L;
    
	
	@NotBlank
	@Size(min=3,max=50)
	@Column(name="name",nullable=false,length=150)
	private String name;
	
	@Email
	@NotEmpty
	@Length(max = 80)
	@Column(name="email",nullable=false,length=150)
	private String email;
	
	@NotBlank
	@Length(max = 150)
	@Column(name = "ADDRESS")
	private String address;
	
	@NotBlank(message="error.password.empty")
	@Size(min=6,max=40)
	@Column(name="password",nullable=false,length=150)
	private String password;
	
	
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
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}

}
