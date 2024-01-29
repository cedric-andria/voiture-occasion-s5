package com.ced.app.repository;

import com.ced.app.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

    @Query("SELECT c FROM Categorie c WHERE c.nom=?1 ")
    Optional<Categorie> findByNom(String prenom);
}
