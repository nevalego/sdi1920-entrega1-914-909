package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;

public interface InvitationsRepository extends CrudRepository<Invitation, Long>{

	@Query("SELECT i FROM Invitation i WHERE i.userResponding = ?1")
	Page<Invitation> findAllForUser(Pageable pageable, User user);

	@Query("SELECT i FROM Invitation i WHERE i.userRequesting = ?1 AND i.userResponding = ?2")
	Invitation findByUsers(User userRequesting, User userResponding);

}

