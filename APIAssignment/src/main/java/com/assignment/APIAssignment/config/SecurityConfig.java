package com.assignment.APIAssignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.assignment.APIAssignment.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		System.out.println("At Security configure");
		http
			.csrf().disable()
			.authorizeHttpRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll()
				.antMatchers(HttpMethod.GET, "/cars").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST,"/register").permitAll()
				.antMatchers(HttpMethod.GET, "/cars/*").permitAll()
				.antMatchers(HttpMethod.POST, "/cars/new").permitAll()
				.antMatchers(HttpMethod.PUT, "/cars/*/update").permitAll()
				.antMatchers(HttpMethod.DELETE, "/cars/*").permitAll()
				.anyRequest().authenticated();
//				.and()
//				.formLogin()
//					.loginPage("/login")
//					.loginProcessingUrl("/login")
//					.defaultSuccessUrl("/posts")
//					.failureUrl("/signin-error")
//					.permitAll()
//					.and()
//					.csrf().disable()
//				.logout()
//					 .invalidateHttpSession(true)
//					 .permitAll();	
		
		return http.build();
	}

}

