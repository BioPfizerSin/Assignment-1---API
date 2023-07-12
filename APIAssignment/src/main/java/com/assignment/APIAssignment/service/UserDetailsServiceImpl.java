package com.assignment.APIAssignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.assignment.APIAssignment.entity.User;
import com.assignment.APIAssignment.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getUserByEmail(username);
		
		if(user == null) { 
			throw new UsernameNotFoundException("user not found."); 
		}
		
		return new CustomUserDetail(user);
	}

}
