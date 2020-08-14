package com.atticus.hr.Service;

import java.util.List;
import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.domain.Contact;


public interface ContactService {
    public Contact add(ContactDTO added);
    public Contact deleteById(Integer id) throws ResourceNotFoundException;
    public List<Contact> findAll() throws ResourceNotFoundException;
    public Contact findById(Integer id) throws ResourceNotFoundException;

    public Contact findByEmail(String email) throws ResourceNotFoundException;
    public Contact update(ContactDTO updated) throws ResourceNotFoundException;
    public Contact findByPhoneNumber(String phoneNumber) throws ResourceNotFoundException;
}
