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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uniovi.entities.Friendship;
import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.services.FriendshipService;
import com.uniovi.services.InvitationService;
import com.uniovi.services.UsersService;

/**
 * This class is aimed to receive the requests for invitations
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Controller
public class InvitationController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private InvitationService invitationsService;

    @Autowired
    private FriendshipService friendshipService;

    /**
     * Method to receive the request for adding a new invitation from the logged
     * in user to another user
     * 
     * @param id
     * @param pageable
     * @param model
     * @param redirectAttrs
     * @return
     */
    @RequestMapping(value = "/invitation/add/{id}")
    public String inviteUserToFriendship(@PathVariable Long id,
	    Pageable pageable, Model model, RedirectAttributes redirectAttrs) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User userRequesting = usersService.getUserByEmail(email);
	User userResponding = usersService.getUser(id);

	Invitation invitation = invitationsService
		.getInvitationFromTo(userRequesting, userResponding);
	Friendship friendship = friendshipService
		.getFriendshipOfUsers(userRequesting, userResponding);
	if (invitation == null && friendship == null)
	    invitationsService.addInvitationFromTo(userRequesting,
		    userResponding);
	else if (invitation != null || friendship != null) {
	    // "Error.friendship.repeated"
	    redirectAttrs
		    .addFlashAttribute("mensaje",
			    "Ya existe una invitacion para este usuario")
		    .addFlashAttribute("clase", "warning");
	}
	Page<User> users = new PageImpl<User>(new LinkedList<User>());
	users = usersService.getUsersForUser(pageable, userResponding);
	model.addAttribute("usersList", users.getContent());
	model.addAttribute("page", users);
	return "redirect:/user/list";
    }

    /**
     * Method to receive the request for listing the invitations received by a
     * user
     * 
     * @param model
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invitation/list")
    public String getListado(Model model, Pageable pageable) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User userResponding = usersService.getUserByEmail(email);
	Page<Invitation> invitationsRecepted = new PageImpl<Invitation>(
		new LinkedList<Invitation>());
	invitationsRecepted = invitationsService.getInvitationsForUser(pageable,
		userResponding);
	model.addAttribute("invitationsList", invitationsRecepted.getContent());
	model.addAttribute("page", invitationsRecepted);
	return "invitation/list";
    }

    /**
     * Method to receive the request for the logged in user to accept an
     * invitation of another user
     * 
     * @param model
     * @param invitationId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invitation/accept/{invitationId}")
    public String acceptInvitation(Model model, @PathVariable Long invitationId,
	    Pageable pageable) {

	Invitation invitation = invitationsService.getById(invitationId);
	friendshipService.addFriendship(invitation.getUserRequesting(),
		invitation.getUserResponding());
	invitationsService.deleteInvitation(invitationId);

	Page<Invitation> invitationsRecepted = new PageImpl<Invitation>(
		new LinkedList<Invitation>());
	invitationsRecepted = invitationsService.getInvitationsForUser(pageable,
		invitation.getUserResponding());
	model.addAttribute("invitationsList", invitationsRecepted.getContent());
	model.addAttribute("page", invitationsRecepted);
	return "invitation/list";
    }
}
