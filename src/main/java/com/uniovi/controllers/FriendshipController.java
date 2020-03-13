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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uniovi.entities.User;
import com.uniovi.services.FriendshipService;
import com.uniovi.services.PublicationsService;
import com.uniovi.services.UsersService;

@Controller
public class FriendshipController {

	

	@Autowired
	private UsersService usersService;
	
	@Autowired 
	private FriendshipService friendshipService;
	
	@Autowired
	private PublicationsService publicationsService;
	
	@RequestMapping(value="/friendship/list")
	public String getListado(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User user1 = usersService.getUserByEmail(email);
		Page<User> friends = new PageImpl<User>(new LinkedList<User>());
		friends = friendshipService.getFriendsOfUser(pageable,user1);
		model.addAttribute("userLogged", user1);
		model.addAttribute("friendsList", friends.getContent());
		model.addAttribute("page", friends);
		return "friendship/list";
	}
	
	
	@RequestMapping("/friend/details/{id}")
	public String getFriendDetails(Model model, @PathVariable Long id) {
		User user = usersService.getUser(id);
		model.addAttribute("user", user);
		model.addAttribute("publicationList", publicationsService.getPublicationsForUser(user));
		return "friendship/details";
	}
	
}
