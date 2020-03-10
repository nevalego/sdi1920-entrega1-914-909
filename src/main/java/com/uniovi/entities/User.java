package com.uniovi.entities;

import java.util.Set; //A collection that contains no duplicate elements 

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	private String role;
	private String password;
	@Transient // propiedad que no se almacena en la tabla
	private String passwordConfirm;

	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Publication> publications;
	
	@OneToMany(mappedBy = "user1", cascade = CascadeType.ALL)
	private Set<Friendship> friends;
	
	@OneToMany(mappedBy = "userResponding",  cascade = CascadeType.ALL)
	private Set<Invitation> invitations;
	
	public User(String email, String name, String lastName) {
		super();
		this.email = email;
		this.name = name;
		this.lastName = lastName;
	}

	public User() {
	}

	public boolean isFriendOf(User user2) {
		boolean isFriend = false;
		for(Friendship friendship : friends) {
			if(friendship.getUser2().getId() == user2.getId()) {
				isFriend = true;
			}
		}
		return isFriend;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Set<Publication> getPublications() {
		return publications;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
	}

	public void setPublications(Set<Publication> publications) {
		this.publications=publications;
	}

	
	
	public Set<Invitation> getInvitations() {
		return invitations;
	}

	public void setInvitations(Set<Invitation> invitations) {
		this.invitations = invitations;
	}

	public Set<Friendship> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friendship> friends) {
		this.friends = friends;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", lastName=" + lastName + ", password="
				+ password + ", passwordConfirm=" + passwordConfirm + ", publications=" + publications + "]";
	}

	public void setRole(String role) {
		this.role=role;
	}

	
	
}