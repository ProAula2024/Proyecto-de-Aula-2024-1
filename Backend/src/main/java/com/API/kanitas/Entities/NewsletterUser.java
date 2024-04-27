package com.API.kanitas.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
public class NewsletterUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Getter
    @Setter
    private Integer email_id;
    
    @Column
    @Getter
    @Setter
    private String registered_email;

}