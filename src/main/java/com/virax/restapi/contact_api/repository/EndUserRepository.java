package com.virax.restapi.contact_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.virax.restapi.contact_api.model.EndUser;

public interface EndUserRepository extends JpaRepository<EndUser, Integer>{

	EndUser findByUserName(String userName);


}
