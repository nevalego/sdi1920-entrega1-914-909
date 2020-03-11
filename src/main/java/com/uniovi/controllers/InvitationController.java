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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.services.FriendshipService;
import com.uniovi.services.InvitationService;
import com.uniovi.services.RolesService;
import com.uniovi.services.SecurityService;
import com.uniovi.services.UsersService;

@Controller
public class InvitationController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private RolesService rolesService;
	
	@Autowired
	private InvitationService invitationsService;
	
	@Autowired 
	private FriendshipService friendshipService;
	
	@RequestMapping(value ="/invitation/add/{id}", method = RequestMethod.POST)
	public String inviteUserToFriendship(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User userRequesting = usersService.getUserByEmail(email);
		User userResponding = usersService.getUser(id);
		
		Invitation invitation = invitationsService.getInvitationFromTo(userRequesting, userResponding);
		if( invitation != null )
			invitationsService.addInvitationFromTo(userRequesting, userResponding);
		
		return "user/list";
	}

	@RequestMapping(value="/invitation/list") 
	public String getListado(Model model, Pageable pageable) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User userResponding = usersService.getUserByEmail(email);
		Page<Invitation> invitationsRecepted = new PageImpl<Invitation>(new LinkedList<Invitation>());
		invitationsRecepted = invitationsService.getInvitationsForUser(pageable,userResponding);
		model.addAttribute("invitationsList", invitationsRecepted.getContent());
		model.addAttribute("page", invitationsRecepted);
		return "invitation/list";
	}
	
	@RequestMapping(value="/invitation/accept/{id}", method = RequestMethod.POST)
	public String acceptInvitation(@PathVariable Long invitationId) {
		
		Invitation invitation = invitationsService.getById(invitationId);
		friendshipService.addFriendship(invitation.getUserRequesting(), invitation.getUserResponding());
		invitationsService.deleteInvitation(invitationId);
		return "invitation/list";
	}
}
