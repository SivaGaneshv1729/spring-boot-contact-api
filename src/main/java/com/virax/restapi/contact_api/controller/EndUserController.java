package com.virax.restapi.contact_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virax.restapi.contact_api.model.EndUser;
import com.virax.restapi.contact_api.service.EndUserService;

@RestController
@RequestMapping("/auth")
public class EndUserController {

    @Autowired
    private EndUserService endUserService;

    @PostMapping("/register")
    public ResponseEntity<EndUser> registerUser(@RequestBody EndUser endUser) {
        return new ResponseEntity<>(endUserService.addEndUser(endUser), HttpStatus.CREATED);
    }
}