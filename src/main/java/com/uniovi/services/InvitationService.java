package com.uniovi.services;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationsRepository;

/**
 * This class is aimed to define the services for the invitation functionalities
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class InvitationService {

    @Autowired
    private InvitationsRepository invitationsRepository;

    /**
     * Method to obtain a Page of Invitations received by a user.
     * 
     * @param pageable
     * @param user
     * @return
     */
    public Page<Invitation> getInvitationsForUser(Pageable pageable,
	    User user) {
	Page<Invitation> invitations = new PageImpl<Invitation>(
		new LinkedList<Invitation>());
	invitations = invitationsRepository.findAllForUser(pageable, user);
	return invitations;
    }

    /**
     * Method to obtain an invitation provided its id
     * 
     * @param id
     * @return
     */
    public Invitation getById(Long id) {
	return invitationsRepository.findById(id).get();
    }

    /**
     * Method to delete an invitation provided the id
     * 
     * @param id
     */
    public void deleteInvitation(Long id) {
	invitationsRepository.deleteById(id);
    }

    /**
     * Method to obtain an invitation from a user to another user
     * 
     * @param userRequesting
     * @param userResponding
     * @return
     */
    public Invitation getInvitationFromTo(User userRequesting,
	    User userResponding) {
	return invitationsRepository.findByUsers(userRequesting,
		userResponding);
    }

    /**
     * Method to save a new invitation from a user to another user
     * 
     * @param userRequesting
     * @param userResponding
     */
    public void addInvitationFromTo(User userRequesting, User userResponding) {

	Invitation invitation = new Invitation(userRequesting, userResponding);
	invitationsRepository.save(invitation);
    }
}
