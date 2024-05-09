package com.API.kanitas.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.API.kanitas.Repositories.CategoryRepository;
import com.API.kanitas.Repositories.ProductRepository;
import com.API.kanitas.Entities.Products;
import com.API.kanitas.Entities.ProductCategory;


@RestController
public class ProductController {
    

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;


    @CrossOrigin
    @GetMapping(path="/products/showAll")
    public @ResponseBody Iterable<Products> getAllProducts() {
        return productRepo.findAll();
    }


    @CrossOrigin
    @GetMapping(path="/products/showFeatured")
    public @ResponseBody Iterable<Products> getAllFeaturedProducts() {
        return productRepo.findByFeatured(1);
    }


    @CrossOrigin
    @GetMapping(path="/products/showCopas")
    public @ResponseBody Iterable<Products> getAllCopas() {
        Optional<ProductCategory> search = categoryRepo.findById(1);
        return productRepo.findByCategory(search);
    }

    @CrossOrigin
    @GetMapping(path="/products/showConos")
    public @ResponseBody Iterable<Products> getAllConos() {
        Optional<ProductCategory> search = categoryRepo.findById(2);
        return productRepo.findByCategory(search);
    }

    @CrossOrigin
    @GetMapping(path="/products/showSpecials")
    public @ResponseBody Iterable<Products> getAllSpecials() {
        Optional<ProductCategory> search = categoryRepo.findById(3);
        return productRepo.findByCategory(search);
    }
}

