package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.HistoriqueAnnonce;

public interface HistoriqueAnnonceRepository extends JpaRepository<HistoriqueAnnonce, Integer> {
    
}
