package com.ced.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ced.app.model.Marque;
import java.lang.Integer;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    
}
