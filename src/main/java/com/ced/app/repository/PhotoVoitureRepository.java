package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.PhotoVoiture;

public interface PhotoVoitureRepository extends JpaRepository<PhotoVoiture, Integer> {
    
}
