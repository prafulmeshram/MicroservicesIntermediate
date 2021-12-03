/**
 * 
 */
package com.practice.microservice.payload.request;

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
public class LoginRequest implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 2306788328236525027L;

	private String username;
	private String password;

}
