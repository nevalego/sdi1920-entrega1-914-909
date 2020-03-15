package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;

/**
 * This interface is aimed to define the queries for invitations
 *  table in the database
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
public interface InvitationsRepository
	extends CrudRepository<Invitation, Long> {

    @Query("SELECT i FROM Invitation i WHERE i.userResponding = ?1")
    Page<Invitation> findAllForUser(Pageable pageable, User user);

    @Query("SELECT i FROM Invitation i WHERE i.userRequesting = ?1 AND i.userResponding = ?2")
    Invitation findByUsers(User userRequesting, User userResponding);

}
