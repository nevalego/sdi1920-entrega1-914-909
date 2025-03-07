package com.uniovi.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.uniovi.entities.User;
import com.uniovi.repositories.UsersRepository;

/**
 * This class is aimed to define the user details method to load a user by its
 * username (email)
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * Method to obtain de user details of a user by its username (email)
     * 
     * @param email
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
	User user = usersRepository.findByEmail(email);
	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

	return new org.springframework.security.core.userdetails.User(
		user.getEmail(), user.getPassword(), grantedAuthorities);
    }

}
