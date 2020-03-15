package com.uniovi.services;

import org.springframework.stereotype.Service;

/**
 * This class is aimed to define the roles
 * for the different users in the application
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class RolesService {

    String[] roles = { "ROLE_STANDARD", "ROLE_ADMIN" };

    public String[] getRoles() {
	return roles;
    }
}
