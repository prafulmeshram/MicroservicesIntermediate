/**
 * 
 */
package com.practice.microservice.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.practice.microservice.data.dto.UserDTO;
import com.practice.microservice.data.model.User;
import com.practice.microservice.payload.request.UserRequest;

/**
 * @author jack
 *
 */
public interface UserService extends UserDetailsService {

	public UserDTO saveUser(UserRequest userRequest);

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

	public User loadUserByEmailId(String emailId);

}
