package com.virax.restapi.contact_api.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class EndUserUserDetails implements UserDetails{

	private EndUser endUser;
	
	public EndUserUserDetails(EndUser endUser) {
		this.endUser = endUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER")); 
		return authorities;
	}

	@Override
	public String getPassword() {
		return endUser.getPassword(); 
	}

	@Override
	public String getUsername() {
		return endUser.getUserName();
	}

	// --- THE 4 MISSING SPRING SECURITY METHODS ---
	
	@Override
	public boolean isAccountNonExpired() {
		return true; // Account never expires
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; // Account is never locked out
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true; // Password never expires
	}

	@Override
	public boolean isEnabled() {
		return true; // Account is active by default
	}
}