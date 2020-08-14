package com.atticus.hr.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atticus.hr.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
   public Contact findContactById(Integer id);
   public Contact findByEmail(String email);
   public Contact findByPhoneNumber(String phoneNumber);
}
