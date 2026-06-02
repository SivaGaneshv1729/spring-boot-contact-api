package com.virax.restapi.contact_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.virax.restapi.contact_api.model.Contact;

import jakarta.transaction.Transactional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>{
	
	@Query("select c from Contact c where c.type = :type")
	public List<Contact> getContactsByType(@Param("type") String type);
	
	@Modifying
	@Transactional
	@Query("update Contact set name = :newName where mobileNumber = :mobileNumber")
	public void updateContactName(@Param("mobileNumber") String mobileNumber, @Param("newName") String newName);
	
	@Modifying
    @Transactional
    @Query("update Contact c set c.name = :#{#contact.name}, c.email = :#{#contact.email}, c.type = :#{#contact.type} where c.mobileNumber = :mobileNumber")
	public void updateContact(@Param("mobileNumber") String mobileNumber, @Param("contact") Contact c);

	@Query("SELECT c FROM Contact c WHERE c.name LIKE %:key% OR c.email LIKE %:key% OR c.mobileNumber LIKE %:key% OR c.type LIKE %:key%")
	public List<Contact> searchContactsByKeyword(@Param("key") String key);

}
