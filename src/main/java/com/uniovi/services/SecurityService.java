package com.uniovi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;;

/**
 * This class is aimed to define the security
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class SecurityService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory
	    .getLogger(SecurityService.class);

    /**
     * Method to obtain the username of the user logged in
     * 
     * @return
     */
    public String findLoggedInEmail() {
	Object userDetails = SecurityContextHolder.getContext()
		.getAuthentication().getDetails();
	if (userDetails instanceof UserDetails) {
	    return ((UserDetails) userDetails).getUsername();
	}

	return null;
    }

    /**
     * Method to authenticate a user with a username and a password
     * 
     * @param email
     * @param password
     */
    public void autoLogin(String email, String password) {
	UserDetails userDetails = userDetailsService.loadUserByUsername(email);
	UsernamePasswordAuthenticationToken aToken = new UsernamePasswordAuthenticationToken(
		userDetails, password, userDetails.getAuthorities());

	authenticationManager.authenticate(aToken);

	if (aToken.isAuthenticated()) {
	    SecurityContextHolder.getContext().setAuthentication(aToken);
	    logger.debug(String.format("Auto login %s successfully!", email));
	}
    }
}