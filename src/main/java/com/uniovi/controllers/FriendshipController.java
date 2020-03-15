package com.uniovi.controllers;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.FriendshipService;
import com.uniovi.services.UsersService;

/**
 * This class is aimed to receive the requests for friendship
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Controller
public class FriendshipController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FriendshipService friendshipService;

    /**
     * Method to receive request for listing friends of 
     * logged user.
     * @param model
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/friendship/list")
    public String getListado(Model model, Pageable pageable) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User user1 = usersService.getUserByEmail(email);
	Page<User> friends = new PageImpl<User>(new LinkedList<User>());
	friends = friendshipService.getFriendsOfUser(pageable, user1);
	model.addAttribute("userLogged", user1);
	model.addAttribute("friendsList", friends.getContent());
	model.addAttribute("page", friends);
	return "friendship/list";
    }

}
