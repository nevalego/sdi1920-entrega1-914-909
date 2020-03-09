package com.uniovi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Invitation {

	@Id
	@GeneratedValue
	private Long id;

	private User userRequesting;
	
	private User userResponding;

	public Invitation(Long id, User userRequesting, User userResponding) {
		super();
		this.id = id;
		this.userResponding = userRequesting;
		this.userResponding = userResponding;
	}
	
	public Invitation( User userRequesting, User userResponding) {
		super();
		this.userRequesting = userRequesting;
		this.userResponding = userResponding;
	}
	
	public Invitation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserRequesting() {
		return userRequesting;
	}

	public void setUserRequesting(User userRequesting) {
		this.userRequesting = userRequesting;
	}

	public User getUserResponding() {
		return userResponding;
	}

	public void setUserResponding(User userResponding) {
		this.userResponding = userResponding;
	}

	@Override
	public String toString() {
		return "Invitation [id=" + id + ", userRequesting=" + userRequesting + ", userResponding=" + userResponding
				+ "]";
	}
	
	
}
