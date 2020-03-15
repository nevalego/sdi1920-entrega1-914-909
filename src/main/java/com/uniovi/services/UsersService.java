package com.uniovi.services;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * This class is aimed to define the services for the user functionalities
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Method to obtain a list of all users of the system
     * 
     * @return
     */
    public List<User> getUsers() {
	List<User> users = new ArrayList<User>();
	usersRepository.findAll().forEach(users::add);
	return users;
    }

    /**
     * Method to obtain a user provided its id
     * 
     * @param id
     * @return
     */
    public User getUser(Long id) {
	return usersRepository.findById(id).get();
    }

    /**
     * Method to add a new user to the system, encoding its password to store
     * it.
     * 
     * @param user
     */
    public void addUser(User user) {

	user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	usersRepository.save(user);
    }

    /**
     * Method to search Users by its name , lastname or email
     * 
     * @param pageable
     * @param searchText
     * @return Page of Users
     */
    public Page<User> searchUserByNameLastNameAndEmail(Pageable pageable,
	    String searchText) {
	Page<User> users = new PageImpl<User>(new LinkedList<User>());
	searchText = "%" + searchText + "%";
	users = usersRepository.searchByNameLastnameAndEmailP(pageable,
		searchText);

	return users;
    }

    /**
     * Method to search Users by its name, lastname, or email
     * 
     * @param searchText
     * @return List of Users
     */
    public List<User> searchUserByNameLastNameAndEmail(String searchText) {
	searchText = "%" + searchText + "%";
	List<User> users = usersRepository
		.searchByNameLastnameAndEmail(searchText);

	return users;
    }

    /**
     * Method to delete a user provided its id
     * 
     * @param id
     */
    public void deleteUser(Long id) {
	usersRepository.deleteById(id);
    }

    /**
     * Method to obtain a user by its email
     * 
     * @param email
     * @return
     */
    public User getUserByEmail(String email) {
	return usersRepository.findByEmail(email);
    }

    /**
     * Method to obtain a Page of Users for a user in the system
     * 
     * @param pageable
     * @param activeUser
     * @return Page of Users
     */
    public Page<User> getUsersForUser(Pageable pageable, User activeUser) {
	return usersRepository.findAllForUser(pageable, activeUser);
    }
}