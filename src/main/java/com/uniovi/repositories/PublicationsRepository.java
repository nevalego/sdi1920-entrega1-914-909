package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

/**
 * This interface is aimed to define the queries for publications
 *  table in the database
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
public interface PublicationsRepository
	extends CrudRepository<Publication, Long> {

    @Query("SELECT p FROM Publication p WHERE p.user = ?1")
    List<Publication> findAllByUser(User activeUser);

}
