package com.atticus.hr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="Contacts")
public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@NotNull
	@Size(min=2,max=50)
	@Column(name="first_name", nullable=false,length=50)
	private String firstName;
	
	@NotBlank
	@NotNull
	@Size(min=2,max=50)
	@Column(name="last_name", nullable=false, length=50)
	private String lastName;
	
	@NotBlank
	@NotNull
	//@PhoneNumberUnique
	@Column(name="phone_number", nullable=false, length=30)
	private String phoneNumber;
	
	@NotNull
	@NotBlank
	@Email
	//@EmailUnique
	@Column(name="email_address", length=100)
	private String email;
	
	@Valid
	private Address address;
	
	@Version
	private Long version;
	
	public Address getAddress() {
		return this.address;
	}
	
	public void setAddress(Address address) {
		this.address=address;
	}
	
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

	public static class Builder{
		private Contact built;
		
		public Builder(String firstName, String lastName) {
			built= new Contact();
			built.setFirstName(firstName);
			built.setLastName(lastName);
		}
		
		public Builder address(String streetAddress, String postCode, String postOffice,
				String state, String country) {
			Address address=Address.getBuilder(streetAddress, postCode, postOffice)
					               .withState(state)
					               .withCountry(country)
					               .build();
			                       
			built.setAddress(address);
			
			return this;
		}
		
		public Builder withEmailAddress(String email) {
			built.setEmail(email);
			
			return this;
		}
		
		public Builder withPhoneNumber(String phoneNumber) {
			built.setPhoneNumber(phoneNumber);
			
			return this;
		}
		
		public Contact build() {
			return built;
		}
	}
	
	public static Builder getBuilder(String firstName, String lastName) {
		return new Builder(firstName, lastName);
	}
	
	public void update(final String firstName, final String lastName,final String emailAddress,
			final String phoneNumber) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=emailAddress;
		this.phoneNumber=phoneNumber;
	}
	
	public void updateAddress(final String streetAddress,final String postCode, final String postOffice,
			final String state,final String country) {
		if(address==null) {
			address= new Address();
		}
		
		address.update(streetAddress,postCode,postOffice,state,country);
	}
}
