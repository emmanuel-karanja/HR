package com.atticus.hr.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="role")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1216649563914411074L;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, unique=true)
	private RoleName name=RoleName.ROLE_USER;
	
	@JsonBackReference
	@ManyToMany(mappedBy="roles")
	private List<AuthUser> users;

	public RoleName getName() {
		return this.name;
	}
	public void setName(final RoleName name) {
		this.name=name;
	}
	

	public List<AuthUser> getUsers(){
		return this.users;
	}
	
	public void setUsers(List<AuthUser> users) {
		this.users=users;
	}
}
