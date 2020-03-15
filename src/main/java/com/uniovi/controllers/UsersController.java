package com.uniovi.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.uniovi.entities.User;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;
import com.uniovi.validators.SignUpFormValidator;

/**
 * This class is aimed to receive the requests for users
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SignUpFormValidator signUpFormValidator;

    @Autowired
    private RolesService rolesService;

    /**
     * Method to receive request to obtaine the form to sign up an anonymous
     * user
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup(Model model) {
	model.addAttribute("user", new User());
	return "signup";
    }

    /**
     * Method to receive the request of signing up a user
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Validated User user, BindingResult result) {
	signUpFormValidator.validate(user, result);
	if (result.hasErrors()) {
	    return "signup";
	}
	user.setRole(rolesService.getRoles()[0]);
	usersService.addUser(user);
	securityService.autoLogin(user.getEmail(), user.getPasswordConfirm());
	return "redirect:home";
    }

    /**
     * Method to receive the request of the form to log in a user
     * 
     * @param error
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(
	    @RequestParam(value = "error", required = false) String error,
	    Model model) {
	if (error != null) {
	    model.addAttribute("error", error);
	}
	return "login";
    }

    /**
     * Method to receive the request of obtaining the home page
     * 
     * @param model
     * @param pageable
     * @param searchText
     * @return
     */
    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public String home(Model model, Pageable pageable, String searchText) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	List<User> users = new ArrayList<User>();

	if (activeUser.getRole().equals(rolesService.getRoles()[1])) {
	    // Si es Admin devuelve todos los usuarios del sistema
	    if (searchText != null && !searchText.isEmpty()) {
		users = usersService
			.searchUserByNameLastNameAndEmail(searchText);
	    } else {
		users = usersService.getUsers();
	    }
	    model.addAttribute("usersList", users);

	} else {
	    Page<User> usersPageable = new PageImpl<User>(
		    new LinkedList<User>());

	    if (searchText != null && !searchText.isEmpty()) {
		usersPageable = usersService
			.searchUserByNameLastNameAndEmail(pageable, searchText);
	    } else {
		usersPageable = usersService.getUsersForUser(pageable,
			activeUser);
	    }
	    users = usersPageable.getContent();
	    model.addAttribute("usersList", users);
	    model.addAttribute("page", usersPageable);
	}
	return "home";
    }

    /**
     * Method to receive the request of listing the users when a user is logged
     * in
     * 
     * @param model
     * @param pageable
     * @param searchText
     * @return
     */
    @RequestMapping("/user/list")
    public String getListado(Model model, Pageable pageable,
	    String searchText) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	List<User> users = new ArrayList<User>();

	if (activeUser.getRole().equals(rolesService.getRoles()[1])) {
	    // Si es Admin devuelve todos los usuarios del sistema
	    if (searchText != null && !searchText.isEmpty()) {
		users = usersService
			.searchUserByNameLastNameAndEmail(searchText);
	    } else {
		users = usersService.getUsers();
	    }
	    model.addAttribute("usersList", users);

	} else {
	    Page<User> usersPageable = new PageImpl<User>(
		    new LinkedList<User>());

	    if (searchText != null && !searchText.isEmpty()) {
		usersPageable = usersService
			.searchUserByNameLastNameAndEmail(pageable, searchText);
	    } else {
		usersPageable = usersService.getUsersForUser(pageable,
			activeUser);
	    }
	    users = usersPageable.getContent();
	    model.addAttribute("usersList", users);
	    model.addAttribute("page", usersPageable);
	}
	return "user/list";
    }

    /**
     * Method to receive the request of the form to add users
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/user/add")
    public String getUser(Model model) {
	model.addAttribute("rolesList", rolesService.getRoles());
	return "user/add";
    }

    /**
     * Method to receive the request to add a new user
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String setUser(@ModelAttribute User user) {
	usersService.addUser(user);
	return "redirect:/user/list";
    }

    /**
     * Method to receive the request of deleting a user by its id
     * 
     * @param id
     * @return
     */
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
	usersService.deleteUser(id);
	return "redirect:/user/list";
    }
}