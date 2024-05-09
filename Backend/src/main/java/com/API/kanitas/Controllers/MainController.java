package com.API.kanitas.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.kanitas.Entities.NewsletterUser;
import com.API.kanitas.Repositories.EmailRepository;

import com.API.kanitas.Entities.ProductCategory;
import com.API.kanitas.Repositories.CategoryRepository;

@RestController
public class MainController {

    @Autowired
    private EmailRepository emailRepository; 

    @Autowired
    private CategoryRepository categoryRepo;

    @CrossOrigin
    @PostMapping(path="/newsletter/add")
    public String addNewEmail(@RequestBody NewsletterUser newu) {
        emailRepository.save(newu);
        return "Saved";
    }

    @CrossOrigin
    @GetMapping(path="/newsletter/showAll")
    public @ResponseBody Iterable<NewsletterUser> getAllNewsletterUser() {
        return emailRepository.findAll();
    }

    @CrossOrigin
    @GetMapping()
    public String index() {
        return "initialized!";
    }

    @CrossOrigin
    @GetMapping(path="/categories/showAll")
    public @ResponseBody Iterable<ProductCategory> getAllProductCategories() {
        return categoryRepo.findAll();
    }
}
