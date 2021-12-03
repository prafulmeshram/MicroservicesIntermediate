/**
 * 
 */
package com.practice.microservice.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.microservice.data.dto.UserDTO;
import com.practice.microservice.data.model.User;
import com.practice.microservice.payload.request.UserRequest;
import com.practice.microservice.repository.UserRepository;

/**
 * @author jack
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

	UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder;
	Environment environment;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
			Environment environment) {
		super();
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.environment = environment;
	}

	@Override
	public UserDTO saveUser(UserRequest userRequest) {
		userRequest.setUuid(UUID.randomUUID().toString());
		userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
		User user = new User();
		BeanUtils.copyProperties(userRequest, user);
		this.userRepository.save(user);

		UserDTO userDTO = new UserDTO();
		BeanUtils.copyProperties(userRequest, userDTO);

		return userDTO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmailId(username);
		if (user == null)
			throw new UsernameNotFoundException(username);

		return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), true,
				false, false, false, new ArrayList<>());
	}

	@Override
	public User loadUserByEmailId(String emailId) {
		User user = this.userRepository.findByEmailId(emailId);
		if (user == null)
			throw new UsernameNotFoundException(emailId);
		return user;
	}

}
