package com.assignment.APIAssignment.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.assignment.APIAssignment.entity.User;
import com.assignment.APIAssignment.repository.RoleRepository;
import com.assignment.APIAssignment.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	// save new user
	public User saveUser(User user) {

		// encrypt the password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// assign default role: VIEW_STORE
		user.addRoles(roleRepository.findById(1).get());

		return userRepository.save(user);
	}

	public long countRegisteredUser() {
		return userRepository.count();
	}

	public User getUserById(Long user_id) {
		return userRepository.findById(user_id).get();
	}

	public User getUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	// return all users
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	public void updateUser(Long id) {
		
	}


}
