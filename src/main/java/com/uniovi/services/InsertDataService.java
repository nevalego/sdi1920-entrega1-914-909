//package com.uniovi.services;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.uniovi.entities.Publication;
//import com.uniovi.entities.User;
//
//@Service
//public class InsertDataService {
//	@Autowired
//	private UsersService usersService;
//
//	@Autowired
//	private RolesService rolesService;
//	
//	@Autowired
//	private FriendshipService friendshipService;
//	
//	@Autowired
//	private InvitationService invitationsService;
//
//	@PostConstruct
//	public void init() {
//		User user1 = new User("pedrod@gmail.com", "Pedro", "Díaz");
//		user1.setPassword("123456");
//		user1.setRole(rolesService.getRoles()[0]);
//		User user2 = new User("lucasnu@gmail.com", "Lucas", "Núñez");
//		user2.setPassword("123456");
//		user2.setRole(rolesService.getRoles()[0]);
//		User user3 = new User("mariar@gmail.com", "María", "Rodríguez");
//		user3.setPassword("123456");
//		user3.setRole(rolesService.getRoles()[0]);
//		User user4 = new User("maral@gmail.com", "Marta", "Almonte");
//		user4.setPassword("123456");
//		user4.setRole(rolesService.getRoles()[0]);
//		User user5 = new User("pelaval@gmail.com", "Pelayo", "Valdes");
//		user5.setPassword("123456");
//		user5.setRole(rolesService.getRoles()[0]);
//		User user6 = new User("admin@email.com", "Edward", "Núñez"); // ADMIN
//		user6.setPassword("admin");
//		user6.setRole(rolesService.getRoles()[1]);
//
//		Set<Publication> user1Publications = new HashSet<Publication>() {
//			{
//				add(new Publication("Buenos dias", "Saludo", new Date(), user1));
//				add(new Publication("Me ha pasado algo genial", "Comentario", new Date(), user1));
//			}
//		};
//		user1.setPublications(user1Publications);
//		Set<Publication> user2Publications = new HashSet<Publication>() {
//			{
//				add(new Publication("ajaj", "Risa", new Date(), user2));
//				add(new Publication("Que tal os va", "Pregunta", new Date(), user2));
//			}
//		};
//		user2.setPublications(user2Publications);
//		Set<Publication> user3Publications = new HashSet<Publication>() {
//			{
//				add(new Publication("Genial!", "Respuesta", new Date(), user3));
//				add(new Publication("Que bonito dia", "Comentario", new Date(), user3));
//			}
//		};
//		user3.setPublications(user3Publications);
//
//		usersService.addUser(user1);
//		usersService.addUser(user2);
//		usersService.addUser(user3);
//		usersService.addUser(user4);
//		usersService.addUser(user5);
//		usersService.addUser(user6);
//		
//		invitationsService.addInvitationFromTo(user1, user2);
//		invitationsService.addInvitationFromTo(user6, user1);
//		invitationsService.addInvitationFromTo(user4, user6);
//		
//		friendshipService.addFriendship(user1, user4);
//		friendshipService.addFriendship(user1, user5);
//	}
//}
