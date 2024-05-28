package com.datvu.all.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
	
    @PostConstruct
    public void init() {
        System.out.println("SecurityConfig initialized");
    }

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    return http
	        .csrf().disable()
	        .authorizeRequests(authorize -> authorize
	            .requestMatchers("/message", "/hello").permitAll()
	            .requestMatchers("/secure-message","/protected-resource").authenticated()
	        )
	        .oauth2Login() // remember sign out of github.com when testing this function
	        .and()
	        .build();
	}

	@Bean
	// authentication
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
		UserDetails admin = User.withUsername("admin_user").password(encoder.encode("123456")).roles("ADMIN").build();
		UserDetails user = User.withUsername("user").password(encoder.encode("123456")).roles("USER").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}



}