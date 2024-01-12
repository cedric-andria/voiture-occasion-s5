package com.ced.app.repository;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    Optional<Marque> findById(int id);
}

