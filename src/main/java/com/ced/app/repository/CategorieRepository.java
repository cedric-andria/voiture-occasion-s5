package com.ced.app.repository;

import com.ced.app.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

        List<Categorie> findAllByOrderById();

    @Query("SELECT c FROM Categorie c WHERE c.nom=?1 ")
    Optional<Categorie> findByNom(String prenom);

    @Query("SELECT c.id AS id, c.nom AS nom, COUNT(v.id) AS nb_vente " +
            "FROM Categorie c " +
            "JOIN Voiture vo ON c.id = vo.categorie.id " +
            "JOIN Annonce a ON vo.id = a.voiture.id " +
            "JOIN Vente v ON a.id = v.annonce.id " +
            "GROUP BY c.id, c.nom")
    List<Object[]> findCategoriesWithNbVente();
    @Query("SELECT c.id AS id, c.nom AS nom, COUNT(v.id) AS nb_vente " +
            "FROM Categorie c " +
            "JOIN Voiture vo ON c.id = vo.categorie.id " +
            "JOIN Annonce a ON vo.id = a.voiture.id " +
            "JOIN Vente v ON a.id = v.annonce.id " +
            "WHERE v.date_vente BETWEEN :dateDebut AND :dateFin " +
            "GROUP BY c.id, c.nom")
    List<Object[]> findCategoriesWithNbVenteBetween(@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);

}
