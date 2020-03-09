package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u <> ?1 AND u.email<>'admin@email.com' ")
	List<User> findAllForUser(User user);
}