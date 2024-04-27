package com.API.kanitas.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.API.kanitas.Entities.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer>{
    
}
