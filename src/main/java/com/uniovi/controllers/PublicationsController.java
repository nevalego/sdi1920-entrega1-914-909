package com.uniovi.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email = auth.getName();
		User activeUser = usersService.getUserByEmail(email);
		model.addAttribute("publicationList", publicationsService.getPublicationsForUser(activeUser));
		return "publication/list";
	}

	@RequestMapping("/publication/list/update")
	public String updateList(Model model) {
		model.addAttribute("publicationList", publicationsService.getPublications());
		return "publication/list :: tablePublications";
	}

	@RequestMapping(value = "/publication/add")
	public String getPublication(Model model) {
		model.addAttribute("usersList", usersService.getUsers());
		return "publication/add";
	}

	@RequestMapping(value = "/publication/add", method = RequestMethod.POST)
	public String setPublication(@ModelAttribute Publication publication) {
		publication.setDate(new Date());
		publicationsService.addPublication(publication);
		return "redirect:/publication/list";
	}

	@RequestMapping("/publication/details/{id}")
	public String getDetail(Model model, @PathVariable Long id) {
		model.addAttribute("publication", publicationsService.getPublication(id));
		return "publication/details";
	}

	@RequestMapping("/publication/delete/{id}")
	public String deletePublication(@PathVariable Long id) {
		publicationsService.deletePublication(id);
		return "redirect:/publication/list";
	}
}
