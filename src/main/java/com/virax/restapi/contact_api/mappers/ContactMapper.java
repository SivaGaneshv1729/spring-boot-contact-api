package com.virax.restapi.contact_api.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.virax.restapi.contact_api.dtos.ContactDto;
import com.virax.restapi.contact_api.model.Contact;

@Mapper(componentModel = "spring")
public interface ContactMapper {
	@Mapping(target = "userName", expression = "java(contact.getEmail() != null ? contact.getEmail().split(\"@\")[0] : null)")
//	@Mapping(target = "userName", expression = "java(contact.getEmail().split(\"@\")[0])")
	public ContactDto toContactDto(Contact contact);
	
	public Contact toContact(ContactDto contactDto);
	
	public List<Contact> toContacts(List<ContactDto> contactDtos);
	
	public List<ContactDto> toContactDtos(List<Contact> contacts);
}
