/**
 * 
 */
package com.practice.microservice.data.dto;

import java.io.Serializable;

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
public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1528448107513432697L;

	private String firstName;
	private String lastName;
	private String emailId;
	private String password;
	private String mobileNumber;
	private String uuid;
}
