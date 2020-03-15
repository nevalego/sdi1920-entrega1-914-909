package com.uniovi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.services.PublicationsService;
import com.uniovi.services.UsersService;

@Controller
public class PublicationsController {

    @Autowired
    private PublicationsService publicationsService;

    @Autowired
    private UsersService usersService;

    @RequestMapping("/publication/list")
    public String getList(Model model) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	model.addAttribute("publicationList",
		publicationsService.getPublicationsForUser(activeUser));
	return "publication/list";
    }

    @RequestMapping("/publication/list/update")
    public String updateList(Model model) {
	model.addAttribute("publicationList",
		publicationsService.getPublications());
	return "publication/list :: tablePublications";
    }

    @RequestMapping(value = "/publication/add")
    public String getPublication(Model model) {
	return "publication/add";
    }

    @RequestMapping(value = "/publication/add", method = RequestMethod.POST)
    public String setPublication(@ModelAttribute Publication publication) {
	Authentication auth = SecurityContextHolder.getContext()
		.getAuthentication();
	String email = auth.getName();
	User activeUser = usersService.getUserByEmail(email);
	publication.setDate(new Date());
	publication.setUser(activeUser);
	publicationsService.addPublication(publication);
	return "redirect:/publication/list";
    }
}
