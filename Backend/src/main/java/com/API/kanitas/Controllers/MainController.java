package com.API.kanitas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.API.kanitas.Entities.NewsletterUser;
import com.API.kanitas.Entities.Publication;
import com.API.kanitas.Repositories.EmailRepository;
import com.API.kanitas.Repositories.PublicationRepository;


@RestController
public class MainController {

    @Autowired
    private EmailRepository emailRepository; 
    @Autowired
    private PublicationRepository publicationRepository;

    @CrossOrigin
    @PostMapping(path="/add")
    public String addNewEmail(@RequestBody NewsletterUser newu) {
        emailRepository.save(newu);
        return "Saved";
    }

    @CrossOrigin
    @GetMapping(path="/all")
    public @ResponseBody Iterable<NewsletterUser> getAllNewsletterUser() {
        return emailRepository.findAll();
    }

    @CrossOrigin
    @GetMapping()
    public String index() {
        return "initialized!";
    }

    ///Publication test
    @CrossOrigin
    @GetMapping(path="all/publi")
    public @ResponseBody Iterable<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }
}
