package com.ced.app.repository;

import com.ced.app.model.Modele;
import com.ced.app.model.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    Optional<Profil> findById(int id);
}
