package com.ced.app.repository;

import com.ced.app.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByIdentifiant(String identifiant);
    Optional<Utilisateur> findByIdentifiantAndMdp(String identifiant, String mdp);

    Optional<Utilisateur> findById(int id);

}
