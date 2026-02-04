package com.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	
	private final UserService userService;
	
	public StudentController(UserService userService) {
		this.userService=userService;
		
	}
	
	@GetMapping("/")
	public String getMessage() {
		return "Welcome to Spring Security";
	}
	
	@GetMapping("/user")
	public String userAccess() {
		return userService.getUserData();
	}
	
	@GetMapping("/admin")
	public String adminAccess() {
		return userService.getAdminData();
	}
	
}
