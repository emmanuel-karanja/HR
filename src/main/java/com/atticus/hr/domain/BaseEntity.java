package com.atticus.hr.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(updatable = false)
	 protected Integer id;

	 
     @Version
     private Integer version;
	 public Integer getId() {
	   return this.id;
	 }
	 
	 public void setId(Integer id) {
	  this.id = id;
	  }


}
