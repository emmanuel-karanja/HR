package com.atticus.hr.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;

@Entity
@Table(name="auth_user")
public class AuthUser extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5725338438013336718L;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@Column
	private String name;
	
	@JsonManagedReference
	@ManyToMany(cascade= CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
	           joinColumns= { @JoinColumn(name="user_id",
	                          referencedColumnName="id")},
	           inverseJoinColumns={@JoinColumn(name="role_id",
	                                referencedColumnName="id")})
	 private List<Role> roles;   
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(final String email) {
		this.email=email;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(final String password) {
		this.password=password;
	}
	
	public List<Role> getRoles(){
		return this.roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles=roles;
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(final String name) {
		this.name=name;
	}
}
