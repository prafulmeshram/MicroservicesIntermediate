/**
 * 
 */
package com.practice.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.microservice.data.dto.UserDTO;
import com.practice.microservice.payload.request.UserRequest;
import com.practice.microservice.service.UserService;

/**
 * @author jack
 *
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UsersController {

	@Autowired
	private Environment env;

	@Autowired
	private UserService userService;

	@GetMapping
	public String getUser() {
		return "User" + env.getProperty("local.server.port");
	}

	@PostMapping(produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	private ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserRequest userRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.saveUser(userRequest));
	}

}
