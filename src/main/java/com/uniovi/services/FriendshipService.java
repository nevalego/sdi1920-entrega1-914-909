package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.User;
import com.uniovi.repositories.FriendshipRepository;

/**
 * This class is aimed to define the services for the friendship functionalities
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    /**
     * Method to add a friendship formed by two users
     * 
     * @param user1
     * @param user2
     */
    public void addFriendship(User user1, User user2) {
	Friendship friendship = new Friendship(user1, user2);
	friendshipRepository.save(friendship);
    }

    /**
     * Method to oobtain a Page of Users that are friends of a user passed by
     * parameter.
     * 
     * @param pageable
     * @param user1
     * @return
     */
    public Page<User> getFriendsOfUser(Pageable pageable, User user1) {
	Page<Friendship> friends = friendshipRepository.findAllOfUser(pageable,
		user1);

	return getFriendsOf(user1, friends);
    }

    /**
     * Method to obtain the friendship of two users
     * @param user1
     * @param user2
     * @return
     */
    public Friendship getFriendsOfUser(User user1, User user2) {
	Friendship friends = friendshipRepository.findByUsers(user1, user2);

	return friends;
    }

    /**
     * Method to obtain a Page of Users that are friends of a user
     * It checks each pair not to return repeated users.
     * @param user1
     * @param friendships
     * @return
     */
    private Page<User> getFriendsOf(User user1, Page<Friendship> friendships) {
	List<User> friends = new LinkedList<User>();

	for (Friendship friendship : friendships.getContent()) {
	    // Si el usuario 1 es el logeado, guardar su amigo el usuario2
	    if (friendship.getUser1().getId() == user1.getId()) {
		friends.add(friendship.getUser2());
		// Si el usuario 2 es el logeado, guardar su amigo el usuario1
	    } else if (friendship.getUser2().getId() == user1.getId()) {
		friends.add(friendship.getUser1());
	    }
	}

	return new PageImpl<User>(friends);
    }

}
