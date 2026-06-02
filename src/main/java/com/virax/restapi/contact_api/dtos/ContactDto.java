package com.virax.restapi.contact_api.dtos;

import lombok.Data;

@Data
public class ContactDto {
	private String mobileNumber;
	private String name;
	private String email;
	private String type;
	private String userName;
}
