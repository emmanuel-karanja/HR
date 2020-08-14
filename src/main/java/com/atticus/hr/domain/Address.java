package com.atticus.hr.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Embeddable
public class Address {
	
	@NotBlank
	@NotNull
	@Size(min=3, max=30)
	@Column(name="country", length=30)
	private String country;
	
	@Column(name="street_address", length=150)
	private String streetAddress;
	
	@Column(name="post_code",length=10)
	private String postCode;
	
	
	@Column(name="post_office", length=40)
	private String postOffice;
	
	@NotBlank
	@NotNull
	@Column(name="state", length=20)
	private String state;
	
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
    
    public static class Builder{
    	private Address built;
    	
    	public Builder(String streetAddress, String postCode, String postOffice) {
    		built=new Address();
    		built.setStreetAddress(streetAddress);
    		built.setPostCode(postCode);
    		built.setPostOffice(postOffice);
    		
    	}
    	
    	public Builder withCountry(String country) {
    		built.setCountry(country);
    		return this;
    	}
    	
    	public Builder withState(String state) {
    		built.setState(state);
    		return this;
    	}
    	
    	public Address build() {
    		return built;
    	}
    	
    	
    }
    
    public static Builder getBuilder(final String streetAddress, final String postCode,
    		 final String postOffice) {
    	return new Builder(streetAddress,postCode,postOffice);
    }
    
    public void update(final String streetAddress, final String postCode, final String postOffice,
    		final String state, final String country) {
       this.streetAddress=streetAddress;
       this.postCode=postCode;
       this.postOffice=postOffice;
       this.state=state;
       this.country=country;
    }
}

