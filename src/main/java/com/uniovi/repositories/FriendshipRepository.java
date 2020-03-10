package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Long>{

	@Query("SELECT f FROM Friendship f WHERE f.user1 = ?1")
	List<Friendship> findAllOfUser(User user1);

}
