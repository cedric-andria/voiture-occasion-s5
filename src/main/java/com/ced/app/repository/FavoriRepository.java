package com.ced.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.Favori;

public interface FavoriRepository extends JpaRepository<Favori, Integer>{
    
}
