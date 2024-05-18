package com.API.kanitas.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.kanitas.Entities.NewsletterUser;
import com.API.kanitas.Repositories.EmailRepository;

import com.API.kanitas.Entities.ProductCategory;
import com.API.kanitas.Repositories.CategoryRepository;

@RestController
public class HelperController {

    @Autowired
    private EmailRepository emailRepository; 

    @Autowired
    private CategoryRepository categoryRepo;

    ///Newsletter methods
    @CrossOrigin
    @PostMapping(path="/newsletter/add")
    public String addNewEmail(@RequestBody String email) {
        NewsletterUser newu = new NewsletterUser();
        newu.setRegistered_email(email);
        emailRepository.save(newu);
        return "Saved";
    }


    @CrossOrigin
    @DeleteMapping(path="/newsletter/delete/{id}")
    public void deleteEmail(@PathVariable int id) {
        emailRepository.deleteById(id);
    }


    @CrossOrigin
    @GetMapping(path="/newsletter/showAll")
    public @ResponseBody Iterable<NewsletterUser> getAllNewsletterUser() {
        return emailRepository.findAll();
    }

    ///Category methods
    @CrossOrigin
    @PostMapping(path="/categories/add")
    public String addNewCategory(@RequestBody String name) {
        ProductCategory newc = new ProductCategory();
        newc.setCategory_name(name);
        categoryRepo.save(newc);
        return "Saved";
    }


    @CrossOrigin
    @GetMapping(path="/categories/showAll")
    public @ResponseBody Iterable<ProductCategory> getAllProductCategories() {
        return categoryRepo.findAll();
    }


    @CrossOrigin
    @PutMapping(path="/categories/edit/{id}")
    public ResponseEntity<ProductCategory> changeName(@PathVariable int id, @RequestBody ProductCategory categoryDetails) {
        ProductCategory updatedCategory = updateCategory(id, categoryDetails);
        if (updatedCategory != null) {
            return ResponseEntity.ok(updatedCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin
    @DeleteMapping(path="/categories/delete/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryRepo.deleteById(id);
    }


    public ProductCategory updateCategory(int id, ProductCategory newDetails) {
        Optional<ProductCategory> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isPresent()) {
            ProductCategory currentCategory = optionalCategory.get();
            currentCategory.setCategory_name(newDetails.getCategory_name());
            return categoryRepo.save(currentCategory);
        } else {
            return null;
        }
    }
}
