package com.assignment.APIAssignment.apiController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.APIAssignment.entity.User;
import com.assignment.APIAssignment.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

//
//	// Registration API
//	@PostMapping("/register")
//	String saveUser(@RequestBody User u) {
//		userService.saveUser(u);
//		return "Register successfully";
//	}
//
//	@PostMapping("/login")
//	public String postLogin() {
//		return "login";
//	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		// Additional validation and error handling as needed
		User registeredUser = userService.saveUser(user);
		return ResponseEntity.ok(registeredUser);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		// Additional validation and error handling as needed
		User existingUser = userService.getUserByUsername(user.getUsername());
		if (existingUser == null || !passwordEncoder().matches(user.getPassword(), existingUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
		return ResponseEntity.ok("Login successful");
	}

	private PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
