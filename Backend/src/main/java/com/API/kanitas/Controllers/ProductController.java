package com.API.kanitas.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    @PostMapping(path="/products/add")
    public String createNewProduct(@ModelAttribute Products newP) {
        try {
            List<Products> empty = productRepo.findByName(newP.getProduct_name());
            if (empty.isEmpty()) {
                productRepo.save(newP);
                int product = 0;
                List<Products> temp = productRepo.findByName(newP.getProduct_name());
                for (int i = 0; i < temp.size(); i++) {
                    product = temp.get(i).getProduct_id();
                }
                ImageController.uploadImage(product, newP.getProduct_image());
                return "Saved";
            } else {
                return "Este nombre ya esta en uso";
            }
            
        } catch (Exception  e) {
            e.printStackTrace();
            return "No se pudo crear el producto.";
        }
        
    }


    @CrossOrigin
    @PutMapping(path="/products/edit/{id}")
    public ResponseEntity<Products> changeName(@PathVariable int id, @RequestBody Products product) {
        Products updatedProduct = updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin
    @DeleteMapping(path="/products/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepo.deleteById(id);
    }


    @CrossOrigin
    @GetMapping(path="/products/showAll")
    public @ResponseBody List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    ///Retrieves products based in if is marked featured
    @CrossOrigin
    @GetMapping(path="/products/showFeatured")
    public @ResponseBody Iterable<Products> getAllFeaturedProducts() {
        return productRepo.findByFeatured(1);
    }

    ///Retrieves products by category
    @CrossOrigin
    @GetMapping(path="/products/showCopas")
    public @ResponseBody Iterable<Products> getAllCopas() {
        Optional<ProductCategory> search = categoryRepo.findById(1);
        return productRepo.findByCategory(search);
    }

    @CrossOrigin
    @GetMapping(path="/products/showEnsaladas")
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


    public Products updateProduct(int id, Products newDetails) {
        Optional<Products> optionalProduct = productRepo.findById(id);
        if (optionalProduct.isPresent()) {
            Products currentProduct = optionalProduct.get();
            currentProduct.setProduct_name(newDetails.getProduct_name());
            currentProduct.setProduct_price(newDetails.getProduct_price());
            currentProduct.setIs_featured(newDetails.getIs_featured());
            currentProduct.setProduct_category(newDetails.getProduct_category());
            currentProduct.setProduct_ingredients(newDetails.getProduct_ingredients());
            currentProduct.setProduct_image(newDetails.getProduct_image());
            ImageController.uploadImage(currentProduct.getProduct_id(), newDetails.getProduct_image());
            return productRepo.save(currentProduct);
        } else {
            return null;
        }
    }
}

