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

@Service
public class FriendshipService {

	@Autowired
	private FriendshipRepository friendshipRepository;
	
	public void addFriendship(User user1, User user2) {
		Friendship friendship = new Friendship(user1,user2);
		friendshipRepository.save(friendship);
	}

	public Page<User> getFriendsOfUser(Pageable pageable, User user1) {
		Page<Friendship> friends = friendshipRepository.findAllOfUser(pageable, user1);
		
		return getFriendsOf(user1, friends); 
	}
	
	public Friendship getFriendsOfUser( User user1, User user2) {
		Friendship friends = friendshipRepository.findByUsers(user1, user2);
		
		return friends; 
	}

	private Page<User> getFriendsOf(User user1, Page<Friendship> friendships) {
		List<User> friends = new LinkedList<User>();
		
		for (Friendship friendship : friendships.getContent()) {
			// Si el usuario 1 es el logeado, guardar su amigo el usuario2
			if( friendship.getUser1().getId() == user1.getId() ) {
				friends.add(friendship.getUser2());
			// Si el usuario 2 es el logeado, guardar su amigo el usuario1
			} else if (friendship.getUser2().getId() == user1.getId()) {
				friends.add(friendship.getUser1());
			}
		}
		
		return new PageImpl<User>(friends);
	}

	public Friendship getFriendship(User userRequesting, User userResponding) {
		return friendshipRepository.findByUsers(userRequesting, userResponding);
	}

}
