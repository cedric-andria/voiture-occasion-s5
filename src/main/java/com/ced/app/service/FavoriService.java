package com.ced.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.Annonce;
import com.ced.app.model.Favori;
import com.ced.app.repository.FavoriRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class FavoriService {
    @Autowired
    private FavoriRepository favoriRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // public List<Favori> getAllFavoris() {
    //     return favoriRepository.findAll();
    // }

    public List<Favori> getAnnoncesFavorites(int id_user_actuel) {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM Favori where id_utilisateur = :id_utilisateur";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Favori.class);
        query.setParameter("id_utilisateur", id_user_actuel);

        @SuppressWarnings("unchecked")

        List<Favori> favoris = query.getResultList();

        return favoris;
    }

    public Favori getById(int id)
    {
        return favoriRepository.getById(id);
    }

    public Optional<Favori> findById(int id)
    {
        return favoriRepository.findById(id);
    }

    public Favori save(Favori favori)
    {
        return favoriRepository.save(favori);
    }
}
