package com.virax.restapi.contact_api.service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.virax.restapi.contact_api.dtos.ContactDto;
import com.virax.restapi.contact_api.mappers.ContactMapper;
import com.virax.restapi.contact_api.model.Contact;
import com.virax.restapi.contact_api.repository.ContactRepository;

@Service

public class ContactOperations {
	
	@Autowired
	ContactRepository contactRepository;
	@Autowired
	ContactMapper contactMapper;
	
	Logger logger = LogManager.getLogger(ContactOperations.class);
	
	public boolean addContact(ContactDto c) {
		
		if (contactRepository.findById(c.getMobileNumber()).isPresent()) {
			logger.info("Contact already exist with number" + c.getMobileNumber());
			throw new ResponseStatusException(HttpStatus.CONFLICT, "MObile Number Already Exists");
		}
		else {
			contactRepository.save(contactMapper.toContact(c));
			return true;
		}
	}
	
	
	public List<ContactDto> addMultipleContacts(List<ContactDto> contactDtos) {
		List<Contact> savedContacts = contactRepository.saveAll(contactMapper.toContacts(contactDtos));
		return contactMapper.toContactDtos(savedContacts);
	}
	
	public ContactDto getContactByMobileNumber(String mobileNumber) {
		Contact contact = contactRepository.findById(mobileNumber)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MObile Number Not Found"));
		return contactMapper.toContactDto(contact);
	}
	
	public List<ContactDto> getAllContacts() {
		return contactMapper.toContactDtos(contactRepository.findAll());
	}
	
	public List<ContactDto> getContactsByType(String type){
		return contactMapper.toContactDtos(contactRepository.getContactsByType(type));
	}
	
	public void updateContactName(String mobileNumber, String newName) {
		if(!contactRepository.existsById(mobileNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile Number Not Found");
		}
		contactRepository.updateContactName(mobileNumber, newName);
		logger.info("name updated for contact with number " + mobileNumber);
	}
	
	public void updateContact(String mobileNumber, ContactDto contactDto) {
		if(!contactRepository.existsById(mobileNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Mobile Number Not Found");
		}
		contactRepository.updateContact(mobileNumber,contactMapper.toContact(contactDto));
		logger.info("Contact Updated with " + mobileNumber);
	}
	public void deleteContactByMobileNumber(String mobileNumber) {
		if(!contactRepository.existsById(mobileNumber)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Contact Exists to Delete");
		}
		contactRepository.deleteById(mobileNumber);
		logger.info("contact deleted with number " + mobileNumber);
	}
	
	public List<ContactDto> searchContacts(String key){
	    return contactMapper.toContactDtos(contactRepository.searchContactsByKeyword(key));
	}
}
