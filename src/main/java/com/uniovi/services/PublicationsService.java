package com.uniovi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.repositories.PublicationsRepository;

@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;

    public List<Publication> getPublications() {
	List<Publication> publications = new ArrayList<Publication>();
	publicationsRepository.findAll().forEach(publications::add);
	return publications;
    }

    public Publication getPublication(Long id) {
	return publicationsRepository.findById(id).get();
    }

    public void addPublication(Publication publication) {
	publication.setDate(new Date()); // Fecha de publicación
	publicationsRepository.save(publication);
    }

    public void deletePublication(Long id) {
	publicationsRepository.deleteById(id);
    }

    public List<Publication> getPublicationsForUser(User activeUser) {
	return publicationsRepository.findAllByUser(activeUser);
    }
}