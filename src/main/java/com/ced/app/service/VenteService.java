package com.ced.app.service;

import com.ced.app.model.*;
import com.ced.app.repository.SoldeRepository;
import com.ced.app.repository.SoldeUtilisateurRepository;
import com.ced.app.repository.VenteRepository;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VenteService {
    @Autowired
    private SoldeService soldeService;
    @Autowired
    private CommissionService commission;
    @Autowired
    private SoldeUtilisateurService soldeUtilisateurService;
    @Autowired
    private SoldeRepository soldeRepository;
    @Autowired
    private VenteRepository venteRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Utilisateur vendeur, Utilisateur client, Vente vente, double solde, double commission) throws Exception {
        try{
            double solde_vendeur = solde-commission;
            soldeUtilisateurService.addSolde(vendeur, solde_vendeur);
            soldeUtilisateurService.addSolde(client, solde);
            venteRepository.save(vente);
            soldeService.addSolde(commission);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public long countAllVentes()
    {
        return venteRepository.count();
    }

    public List<Vente> getAllStatFromOneMarque(int idMarque)
    {
        String nativeQuery = "SELECT * FROM Vente";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);

        @SuppressWarnings("unchecked")
        List<Vente> ventes = query.getResultList();
        List<Vente> statvente = new ArrayList<>();
        
        for (Vente vente : ventes) {
            if (vente.getAnnonce().getVoiture().getModele().getMarque().getId() == idMarque) {
                statvente.add(vente);
            }
        }

        return statvente;
    }

    public List<Vente> getAllStatFromOneCategorie(int idCategorie)
    {
        String nativeQuery = "SELECT * FROM Vente";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Vente.class);

        @SuppressWarnings("unchecked")
        List<Vente> ventes = query.getResultList();
        List<Vente> statvente = new ArrayList<>();

        for (Vente vente : ventes) {
            if (vente.getAnnonce().getVoiture().getCategorie().getId() == idCategorie) {
                statvente.add(vente);
            }
        }

        return statvente;
    }

}
