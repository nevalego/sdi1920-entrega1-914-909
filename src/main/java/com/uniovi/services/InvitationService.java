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

@Service
public class InvitationService {

	@Autowired
	private InvitationsRepository invitationsRepository;
	
	public Page<Invitation> getInvitationsForUser(Pageable pageable, User user) {
		Page<Invitation> invitations = new PageImpl<Invitation>(new LinkedList<Invitation>());
		invitations = invitationsRepository.findAllForUser(pageable, user);
		return invitations;
	}
	
	public Invitation getById(Long id) {
		return invitationsRepository.findById(id).get();
	}
	
	public void deleteInvitation(Long id) {
		invitationsRepository.deleteById(id);
	}
	
	public Invitation getInvitationFromTo(User userRequesting, User userResponding) {
		return invitationsRepository.findByUsers(userRequesting,userResponding);
	}
	
	public void addInvitationFromTo(User userRequesting, User userResponding) {
		
		Invitation invitation = new Invitation(userRequesting,userResponding);
		Invitation invitationSaved =  invitationsRepository.save(invitation);
	}
}
