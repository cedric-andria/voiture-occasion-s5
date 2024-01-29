package com.ced.app.repository;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ced.app.model.Marque;
import org.springframework.data.jpa.repository.Query;
import java.lang.Integer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MarqueRepository extends JpaRepository<Marque, Integer> {
    Optional<Marque> findById(int id);
    @Query("SELECT c FROM Marque c WHERE c.nom=?1 ")
    Optional<Marque> findByNom(String prenom);
    @Query("SELECT m.id AS id, m.nom AS nom, COUNT(v.id) AS nb_vente " +
            "FROM Marque m " +
            "JOIN Modele mo ON m.id = mo.marque.id " +
            "JOIN Voiture vo ON mo.id = vo.modele.id " +
            "JOIN Annonce a ON vo.id = a.voiture.id " +
            "JOIN Vente v ON a.id = v.annonce.id " +
            "GROUP BY m.id, m.nom")
    List<Object[]> findMarquesWithNbVente();
    /*Ito izy ilay entre deux date de io ambony io ilay izy rehetra*/
    @Query("SELECT m.id AS id, m.nom AS nom, COUNT(v.id) AS nb_vente " +
            "FROM Marque m " +
            "JOIN Modele mo ON m.id = mo.marque.id " +
            "JOIN Voiture vo ON mo.id = vo.modele.id " +
            "JOIN Annonce a ON vo.id = a.voiture.id " +
            "JOIN Vente v ON a.id = v.annonce.id " +
            "WHERE v.date_vente BETWEEN :dateDebut AND :dateFin " +
            "GROUP BY m.id, m.nom")
    List<Object[]> findMarquesWithNbVenteBetween(@Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);

}
