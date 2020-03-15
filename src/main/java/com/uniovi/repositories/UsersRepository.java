package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u <> ?1 AND u.email<>'admin@email.com' ")
    Page<User> findAllForUser(Pageable pageable, User user);

    @Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR "
	    + "LOWER(u.lastName) LIKE LOWER(?1) OR "
	    + "LOWER(u.email) LIKE LOWER(?1))")
    Page<User> searchByNameLastnameAndEmailP(Pageable pageable,
	    String searchText);

    @Query("SELECT u FROM User u WHERE (LOWER(u.name) LIKE LOWER(?1) OR "
	    + "LOWER(u.lastName) LIKE LOWER(?1) OR "
	    + "LOWER(u.email) LIKE LOWER(?1))")
    List<User> searchByNameLastnameAndEmail(String searchText);

}