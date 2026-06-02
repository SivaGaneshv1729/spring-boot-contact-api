package com.virax.restapi.contact_api.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults());
            
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. The Users List
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        
        List<UserDetails> users = new ArrayList<>();
        
        users.add(User.withUsername("virax")
                .password(passwordEncoder.encode("password123"))
                .roles("USER")
                .build());
                
        users.add(User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build());
                
        users.add(User.withUsername("guest")
                .password(passwordEncoder.encode("guest123"))
                .roles("GUEST")
                .build());

        users.add(User.withUsername("testuser")
                .password(passwordEncoder.encode("test1234"))
                .roles("USER")
                .build());

        return new InMemoryUserDetailsManager(users);
    }
}