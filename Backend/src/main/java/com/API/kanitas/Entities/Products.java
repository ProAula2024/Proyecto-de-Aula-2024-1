package com.API.kanitas.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Products {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int product_id;

    @Getter 
    @Setter
    private String product_name;

    @Getter
    @Setter
    private String product_image_url;

    @Getter
    @Setter
    private String product_price;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "product_category")
    private ProductCategory product_category;

    @Getter
    @Setter
    private int is_featured;

    @Getter
    @Setter
    private String product_ingredients;
}
