package com.ced.app.service;

import com.ced.app.model.*;
import com.ced.app.repository.SoldeRepository;
import com.ced.app.repository.SoldeUtilisateurRepository;
import com.ced.app.repository.VenteRepository;
import jakarta.transaction.Transactional;
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


}
