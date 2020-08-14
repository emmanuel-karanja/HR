package com.atticus.hr.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atticus.hr.Exceptions.ResourceNotFoundException;
import com.atticus.hr.Repository.ContactRepository;
import com.atticus.hr.domain.Contact;

@Service
public class ContactServiceImpl implements ContactService{

	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional
	@Override
	public Contact add(ContactDTO added) {
		// TODO Auto-generated method stub
		
		Contact contact= Contact.getBuilder(added.getFirstName(),added.getLastName())
				                .address(added.getStreetAddress(), added.getPostCode(), 
				                		added.getPostOffice(), added.getState(), added.getCountry())
				                .withEmailAddress(added.getEmail())
				                .withPhoneNumber(added.getPhoneNumber())
				                .build();
		return contactRepository.save(contact);
		
	}

	@Transactional(rollbackFor=ResourceNotFoundException.class)
	@Override
	public Contact deleteById(Integer id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Contact deleted= contactRepository.findContactById(id);
		contactRepository.delete(deleted);
		return deleted;
	}

	@Transactional(readOnly=true)
	@Override
	public List<Contact> findAll() throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return contactRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Contact findById(Integer id) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Contact found=contactRepository.findContactById(id);
		
		if(found == null)
			throw new ResourceNotFoundException("Contact with Id ="+id+" could not be found");
		return found;
	}

	@Transactional(rollbackFor = ResourceNotFoundException.class)
	@Override
	public Contact update(ContactDTO updated) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		Contact found = contactRepository.findContactById(updated.getId());
		found.update(updated.getFirstName(),updated.getLastName(),updated.getEmail(), updated.getPhoneNumber());
		found.updateAddress(updated.getStreetAddress(),updated.getPostCode(),updated.getPostOffice(),
				updated.getState(),updated.getCountry());
		
		return found;
	}

	@Override
	public Contact findByEmail(String email) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return contactRepository.findByEmail(email);
	}

	@Override
	public Contact findByPhoneNumber(String phoneNumber) throws ResourceNotFoundException {
		// TODO Auto-generated method stub
		return contactRepository.findByPhoneNumber(phoneNumber);
	}
	
}