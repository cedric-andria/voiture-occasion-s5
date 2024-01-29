package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.Voiture;

public interface VoitureRepository extends JpaRepository<Voiture, Integer>{
    
}
