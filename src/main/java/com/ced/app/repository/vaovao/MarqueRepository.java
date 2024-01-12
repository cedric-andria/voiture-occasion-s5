package com.ced.app.repository.vaovao;

import com.ced.app.model.Vaovao.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MarqueRepository extends JpaRepository<Marque,Integer> {

    @Query("SELECT c FROM Marque c WHERE c.nom=?1 ")
    Optional<Marque> findByNom(String prenom);
}
