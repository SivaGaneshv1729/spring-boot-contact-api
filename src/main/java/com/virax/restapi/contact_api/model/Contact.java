package com.virax.restapi.contact_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
	
	@Id
	private String mobileNumber;
	private String name;
	private String email;
	private String type;
	
}
