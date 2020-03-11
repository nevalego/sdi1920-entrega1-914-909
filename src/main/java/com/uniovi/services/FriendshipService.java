package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

	public Page<Friendship> getFriendsOfUser(Pageable pageable, User user1) {
		return friendshipRepository.findAllOfUser(pageable, user1);
	}

}
