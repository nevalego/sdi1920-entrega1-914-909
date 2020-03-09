package com.uniovi.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long>{
	
	User findByEmail(String email);
	@Query("SELECT u FROM User u WHERE u <> ?1 AND u.email<>'admin@email.com' ")
	Page<User> findAllForUser(Pageable pageable, User user);
}