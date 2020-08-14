package com.atticus.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	private String address;
	
	@Column(name="invoice")
	private Integer currentInvoice;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address=address;
	}
	
	public Integer getCurrentInvoice() {
		return this.currentInvoice;
	}
	
	public void setCurrentInvoice(Integer currentInvoice) {
		this.currentInvoice=currentInvoice;
	}

}
