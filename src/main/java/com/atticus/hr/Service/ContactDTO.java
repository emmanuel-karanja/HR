package com.atticus.hr.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.atticus.hr.Validators.EmailUnique;
import com.atticus.hr.Validators.PhoneNumberUnique;


public class ContactDTO {

	private Integer id;
	
	@NotBlank
	@NotNull
	@Size(min=2,max=50)
	private String firstName;
	
	@NotBlank
	@NotNull
	@Size(min=2,max=50)
	private String lastName;
	
	private String streetAddress;
	
	@NotBlank
	@NotNull
	@Email
	//@EmailUnique
	private String email;
	
	@NotBlank
	@NotNull
	//@PhoneNumberUnique
	private String phoneNumber;
	
	@NotBlank
	@NotNull
	@Size(min=2,max=30)
	private String country;
	
	@NotBlank
	@NotNull
	private String state;
	
	private String postOffice;
	private String postCode;
	
	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id=id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName=firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String lastName) {
		this.lastName=lastName;
	}
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber=phoneNumber;
	}
	
	public String getCountry() {
		return this.country;
	}
	
	public void setCountry(String country) {
		this.country=country;
	}
	
	public String getStreetAddress() {
		return this.streetAddress;
	}
    public void setStreetAddress(String streetAddress) {
    	this.streetAddress=streetAddress;
    }
    
    public String getPostCode() {
    	return this.postCode;
    }
    public void setPostCode(String postCode) {
    	this.postCode=postCode;
    }
    
    public String getPostOffice() {
    	return this.postOffice;
    }
    
    public void setPostOffice(String postOffice) {
    	this.postOffice=postOffice;
    }
    
    public String getState() {
    	return this.state;
    }
    
    public void setState(String state) {
    	this.state=state;
    }

}
