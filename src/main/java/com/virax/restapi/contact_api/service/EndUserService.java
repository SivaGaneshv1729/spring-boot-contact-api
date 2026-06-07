package com.virax.restapi.contact_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.virax.restapi.contact_api.model.EndUser;
import com.virax.restapi.contact_api.repository.EndUserRepository;

import org.springframework.http.HttpStatusCode;
import io.swagger.v3.oas.annotations.servers.Server;

@Service // FIX: Added missing @Service annotation so Spring can inject this class
public class EndUserService {
	
	@Autowired
	EndUserRepository endUserRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	public EndUser addEndUser(EndUser endUser) {
		if (endUserRepository.findById(endUser.getId()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"End USer already exist");
		}
		else {
			// FIX: Changed setPassward/getPassward to setPassword/getPassword
			endUser.setPassword(passwordEncoder.encode(endUser.getPassword()));
			return endUserRepository.save(endUser);
		}
	}
	public List<EndUser> getAllDevelopers() {
        return endUserRepository.findAll();
    }

    public EndUser getEndUSerByUserName(String userName) {
        return endUserRepository.findByUserName(userName);
    }
    
    public void deleteEndUser(int id) {
        endUserRepository.deleteById(id);
    }
}