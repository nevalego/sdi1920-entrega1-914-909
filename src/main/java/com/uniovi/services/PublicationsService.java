package com.uniovi.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Publication;
import com.uniovi.entities.User;
import com.uniovi.repositories.PublicationsRepository;

/**
 * This class is aimed to define the services for the publication
 * functionalities
 * 
 * @author Nerea Valdés Egocheaga
 *
 */
@Service
public class PublicationsService {

    @Autowired
    private PublicationsRepository publicationsRepository;

    /**
     * Method to obtain a List of all publications
     * 
     * @return
     */
    public List<Publication> getPublications() {
	List<Publication> publications = new ArrayList<Publication>();
	publicationsRepository.findAll().forEach(publications::add);
	return publications;
    }

    /**
     * Method to obtain a publication provided its id
     * 
     * @param id
     * @return
     */
    public Publication getPublication(Long id) {
	return publicationsRepository.findById(id).get();
    }

    /**
     * Method to add a new publication, with its date of publication (now) and
     * the user logged in
     * 
     * @param publication
     */
    public void addPublication(Publication publication) {
	publication.setDate(new Date()); // Fecha de publicación
	publicationsRepository.save(publication);
    }

    /**
     * Method to delete a publication provided its id
     * 
     * @param id
     */
    public void deletePublication(Long id) {
	publicationsRepository.deleteById(id);
    }

    /**
     * Method to obtain a List of publications made by a user
     * 
     * @param activeUser
     * @return
     */
    public List<Publication> getPublicationsForUser(User activeUser) {
	return publicationsRepository.findAllByUser(activeUser);
    }
}