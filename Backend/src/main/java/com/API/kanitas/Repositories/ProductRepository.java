package com.API.kanitas.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.API.kanitas.Entities.ProductCategory;
import com.API.kanitas.Entities.Products;

public interface ProductRepository extends JpaRepository<Products, Integer>{
    

    @Query("FROM Products WHERE is_featured = ?1")
    List<Products> findByFeatured(int featured);

    @Query("FROM Products WHERE product_category = ?1")
    List<Products> findByCategory(Optional<ProductCategory> search);

    @Query("FROM Products WHERE product_name = ?1")
    List<Products> findByName(String name);
}
