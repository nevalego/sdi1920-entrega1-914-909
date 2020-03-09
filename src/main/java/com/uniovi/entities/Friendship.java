package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Friendship {

	@Id
	@GeneratedValue
	private Long id;

	private User user1;
	
	private User user2;

	public Friendship(Long id, User user1, User user2) {
		super();
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public Friendship( User user1, User user2) {
		super();
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public Friendship() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	@Override
	public String toString() {
		return "Friendship [id=" + id + ", user1=" + user1 + ", user2=" + user2 + "]";
	}
	
	
}
