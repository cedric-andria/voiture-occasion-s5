package com.ced.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.model.PhotoVoiture;
import com.ced.app.repository.HistoriqueAnnonceRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class HistoriqueAnnonceService {
    @Autowired
    private HistoriqueAnnonceRepository historiqueAnnonceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<HistoriqueAnnonce> getAll()
    {
        return historiqueAnnonceRepository.findAll();
    }

    public HistoriqueAnnonce getById(int id)
    {
        return historiqueAnnonceRepository.getById(id);
    }

    public HistoriqueAnnonce save(HistoriqueAnnonce histoannonce)
    {
        return historiqueAnnonceRepository.save(histoannonce);
    }

    public List<HistoriqueAnnonce> getHistoriqueOfUser(int id_user)
    {
        String nativeQuery = "select ha.id, ha.id_annonce, ha.date_operation, ha.nouvel_etat from historiqueAnnonce ha join annonce on annonce.id = ha.id_annonce join voiture on voiture.id = annonce.id_voiture where id_vendeur = :id_user order by ha.id";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, HistoriqueAnnonce.class);
        query.setParameter("id_user", id_user);

        @SuppressWarnings("unchecked")
        List<HistoriqueAnnonce> historiqueAnnonces = query.getResultList();

        return historiqueAnnonces;
    }
}
