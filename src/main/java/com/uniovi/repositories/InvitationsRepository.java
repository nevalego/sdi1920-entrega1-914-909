package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;

public interface InvitationsRepository extends CrudRepository<Invitation, Long>{

	@Query("SELECT i FROM Invitation i WHERE i.userResponding = ?1")
	List<Invitation> findAllForUser(User user);

}

