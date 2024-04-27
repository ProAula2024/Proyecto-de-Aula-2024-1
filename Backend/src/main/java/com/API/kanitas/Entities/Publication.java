package com.API.kanitas.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Publication {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int publication_id;

    @Getter 
    @Setter
    private String publication_name;
}
