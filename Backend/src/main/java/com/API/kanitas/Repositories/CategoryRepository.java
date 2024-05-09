package com.API.kanitas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.kanitas.Entities.ProductCategory;

public interface CategoryRepository extends JpaRepository<ProductCategory, Integer>{

}
