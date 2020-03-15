package com.uniovi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.User;

/**
 * This interface is aimed to define the queries for friendship table in the
 * database
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
public interface FriendshipRepository extends CrudRepository<Friendship, Long> {

    @Query("SELECT f FROM Friendship f WHERE f.user1 = ?1 OR f.user2 = ?1")
    Page<Friendship> findAllOfUser(Pageable pageable, User user1);

    @Query("SELECT f FROM Friendship f WHERE (f.user1 = ?1 AND f.user2 = ?2) "
	    + "  OR  (f.user1 = ?2 AND f.user2 = ?1)")
    Friendship findByUsers(User userRequesting, User userResponding);

}
