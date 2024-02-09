package com.ced.app.service;

import com.ced.app.model.Favori;
import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import com.ced.app.repository.ModeleRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeleService {
    @Autowired
    private ModeleRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    public Modele save(Modele model){
        return repo.save(model);
    }

    public Modele getById(int id)
    {
        return repo.findById(id).orElseThrow(() -> new IllegalStateException("Modele ID: "+id+" n'existe pas"));
    }

    public List<Modele> getAllByMarque(int id_marque)
    {
        String nativeQuery = "SELECT * FROM Modele where id_marque = :id_marque";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Favori.class);
        query.setParameter("id_marque", id_marque);

        @SuppressWarnings("unchecked")

        List<Modele> modeles = query.getResultList();

        return modeles;
    }
}
