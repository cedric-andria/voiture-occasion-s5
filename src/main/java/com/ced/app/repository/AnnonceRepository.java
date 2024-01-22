package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.Annonce;

public interface AnnonceRepository extends JpaRepository<Annonce, Integer> {
    
}
