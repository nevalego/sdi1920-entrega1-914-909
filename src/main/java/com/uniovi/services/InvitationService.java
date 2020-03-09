package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Invitation;
import com.uniovi.entities.User;
import com.uniovi.repositories.InvitationsRepository;

@Service
public class InvitationService {

	@Autowired
	private InvitationsRepository invitationsRepository;
	
	public List<Invitation> getInvitationsForUser(User user) {
		List<Invitation> invitations = new ArrayList<Invitation>();
		invitations = invitationsRepository.findAllForUser(user);
		return invitations;
	}
	
	public Invitation getById(Long id) {
		return invitationsRepository.findById(id).get();
	}
	
	public void deleteInvitation(Long id) {
		invitationsRepository.deleteById(id);
	}
	
	public void addInvitationFromTo(User userRequesting, User userResponding) {
		Invitation invitation = new Invitation(userRequesting,userResponding);
		invitationsRepository.save(invitation);
	}
}
