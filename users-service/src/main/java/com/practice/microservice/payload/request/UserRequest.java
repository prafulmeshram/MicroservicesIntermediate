/**
 * 
 */
package com.practice.microservice.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jack
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private Long userId;

	@NotEmpty
	@Size(min = 2, message = "Must not be less than 2 charecters")
	private String firstName;
	@NotEmpty
	@Size(min = 2, message = "Must not be less than 2 charecters")
	private String lastName;
	@NotEmpty
	@Email
	private String emailId;

	@NotEmpty
	@Size(min = 8, max = 16)
	private String password;
	@NotEmpty
	@Size(min = 10, max = 10)
	private String mobileNumber;

	private String uuid;
	
}
