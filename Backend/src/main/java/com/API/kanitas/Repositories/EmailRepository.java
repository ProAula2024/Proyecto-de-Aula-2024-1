package com.API.kanitas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.kanitas.Entities.NewsletterUser;


public interface EmailRepository extends JpaRepository<NewsletterUser, Integer>{
    
}
