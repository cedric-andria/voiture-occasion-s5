package com.ced.app.repository;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.Integer;
import java.util.Optional;


public interface ModeleRepository extends JpaRepository<Modele, Integer> {
    Optional<Modele> findById(int id);
}
