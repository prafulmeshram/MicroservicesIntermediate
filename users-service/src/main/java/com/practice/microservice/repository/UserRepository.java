/**
 * 
 */
package com.practice.microservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.practice.microservice.data.model.User;

/**
 * @author jack
 *
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUuid(String uuid);

	public User findByEmailId(String emailId);

	public User findByMobileNumber(String mobileNumber);

}
