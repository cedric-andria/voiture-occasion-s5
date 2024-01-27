package com.ced.app.repository;

import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SoldeRepository extends JpaRepository<SoldeUtilisateur, Integer> {
    Optional<SoldeUtilisateur> findByUtilisateur(Utilisateur utilisateur);
}
