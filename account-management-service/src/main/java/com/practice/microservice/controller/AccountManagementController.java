/**
 * 
 */
package com.practice.microservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jack
 */
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class AccountManagementController {

	@GetMapping
	public String getaccountManager() {
		return "Account Manager";
	}
	
	
}
