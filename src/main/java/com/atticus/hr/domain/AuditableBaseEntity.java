package com.atticus.hr.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public abstract class AuditableBaseEntity implements Serializable{
	 
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(updatable = false)
	 protected Long id;

	 @Version
	 @Column(name = "VERSION")
	 private int version;

	 public Long getId() {
	   return id;
	 }
	 
	 public void setId(Long id) {
	  this.id = id;
	  }
	

	 @CreatedDate
	 @Column(name = "CREATED_DATE")
	 @Temporal(TemporalType.TIMESTAMP)
	 protected Date createdDate;

	 @CreatedBy
	 @Column(name = "CREATED_BY")
	 protected String createdBy;

	 @LastModifiedBy
	 @Column(name = "LAST_MODIFIED_BY")
	 protected String lastModifiedBy;

	 @LastModifiedDate
	 @Column(name = "LAST_MODIFIED_DATE")
	 @Temporal(TemporalType.TIMESTAMP)
	 protected Date lastModifiedDate;
	 
	 public Optional<String> getCreatedBy() {
	    return Optional.of(createdBy);
	  }

	 public void setCreatedBy(String createdBy) {
	    this.createdBy = createdBy;
	 }

	 public Optional<Date> getCreatedDate() {
	    return Optional.of(createdDate);
	 }


	 public void setCreatedDate(Date createdDate) {
	   this.createdDate = createdDate;
	 }
	 
	 public Optional<String> getLastModifiedBy() {
	    return Optional.of(lastModifiedBy);
	 }

	 public void setLastModifiedBy(String lastModifiedBy) {
	    this.lastModifiedBy = lastModifiedBy;
	 }

	 public Optional<Date> getLastModifiedDate() {
	    return Optional.of(lastModifiedDate);
	}

	 public void setLastModifiedDate(Date lastModifiedDate) {
	   this.lastModifiedDate = lastModifiedDate;
	 }
	

}
