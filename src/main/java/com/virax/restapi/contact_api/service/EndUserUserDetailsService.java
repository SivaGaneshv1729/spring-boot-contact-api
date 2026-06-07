package com.virax.restapi.contact_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.virax.restapi.contact_api.model.EndUser;
import com.virax.restapi.contact_api.model.EndUserUserDetails;
import com.virax.restapi.contact_api.repository.EndUserRepository;

@Service 
public class EndUserUserDetailsService implements UserDetailsService {

    @Autowired
    private EndUserRepository endUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        EndUser endUser = endUserRepository.findByUserName(username);
        
        if (endUser == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new EndUserUserDetails(endUser); 
    }
}