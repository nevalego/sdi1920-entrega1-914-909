package com.uniovi.services;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;

/**
 * This class is aimed to insert data in the
 * database when application.properties is setted:
 * spring.jpa.hibernate.ddl-auto=create 
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class InsertDataService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private InvitationService invitationsService;

    @PostConstruct
    public void init() {
	User user1 = new User("pedrod@gmail.com", "Pedro", "Díaz");
	user1.setPassword("123456");
	user1.setRole(rolesService.getRoles()[0]);
	User user2 = new User("lucasnu@gmail.com", "Lucas", "Núñez");
	user2.setPassword("123456");
	user2.setRole(rolesService.getRoles()[0]);
	User user3 = new User("mariar@gmail.com", "María", "Rodríguez");
	user3.setPassword("123456");
	user3.setRole(rolesService.getRoles()[0]);
	User user4 = new User("maral@gmail.com", "Marta", "Almonte");
	user4.setPassword("123456");
	user4.setRole(rolesService.getRoles()[0]);
	User user5 = new User("pelaval@gmail.com", "Pelayo", "Valdes");
	user5.setPassword("123456");
	user5.setRole(rolesService.getRoles()[0]);
	User user6 = new User("admin@email.com", "Edward", "Núñez"); // ADMIN
	user6.setPassword("admin");
	user6.setRole(rolesService.getRoles()[1]);
	User user7 = new User("elendi@gmail.com", "Elena", "Ruiz");
	user7.setPassword("123456");
	user7.setRole(rolesService.getRoles()[0]);

	User user8 = new User("llera@gmail.com", "Adrian", "Llera Lona");
	user8.setPassword("123456");
	user8.setRole(rolesService.getRoles()[0]);

	Set<Publication> user1Publications = new HashSet<Publication>();

	user1Publications.add(
		new Publication("Buenos dias", "Saludo", new Date(), user1));
	user1Publications.add(new Publication("Me ha pasado algo genial",
		"Comentario", new Date(), user1));

	user1.setPublications(user1Publications);
	Set<Publication> user2Publications = new HashSet<Publication>();

	user2Publications
		.add(new Publication("ajaj", "Risa", new Date(), user2));
	user2Publications.add(new Publication("Que tal os va", "Pregunta",
		new Date(), user2));

	user2.setPublications(user2Publications);
	Set<Publication> user3Publications = new HashSet<Publication>();
	user3Publications.add(
		new Publication("Genial!", "Respuesta", new Date(), user3));
	user3Publications.add(new Publication("Que bonito dia", "Comentario",
		new Date(), user3));

	user3.setPublications(user3Publications);

	usersService.addUser(user1);
	usersService.addUser(user2);
	usersService.addUser(user3);
	usersService.addUser(user4);
	usersService.addUser(user5);
	usersService.addUser(user6);
	usersService.addUser(user7);
	usersService.addUser(user8);

	invitationsService.addInvitationFromTo(user1, user2);
	invitationsService.addInvitationFromTo(user6, user1);
	invitationsService.addInvitationFromTo(user3, user1);
	invitationsService.addInvitationFromTo(user4, user6);
	invitationsService.addInvitationFromTo(user1, user7);

	friendshipService.addFriendship(user1, user4);
	friendshipService.addFriendship(user1, user5);
	friendshipService.addFriendship(user7, user2);

    }
}
