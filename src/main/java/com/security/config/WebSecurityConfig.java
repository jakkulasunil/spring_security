package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests(auth -> auth.anyRequest().authenticated()).formLogin();

		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		var userDetailsManager = new InMemoryUserDetailsManager();
		var user = User.withUsername("user").password(passwordEncoder().encode("admin")).roles("USER").build();

		var admin = User.withUsername("admin").password(passwordEncoder().encode("admin123")).roles("ADMIN").build();

		userDetailsManager.createUser(user);
		userDetailsManager.createUser(admin);
		return userDetailsManager;
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
