package com.ced.app.controller;

import com.ced.app.model.Annonce;
import com.ced.app.model.Utilisateur;
import com.ced.app.model.Vente;
import com.ced.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/vente")
public class VenteController {
    @Autowired
    private UtilisateurService utilisateur;
    @Autowired
    private CommissionService commissionService;
    @Autowired
    private AnnonceService annonceService;
    @Autowired
    private VenteService venteService;
    @Autowired
    private SoldeService soldeCommissionService;
    @Autowired
    private SoldeUtilisateurService soldeService;

    public ResponseEntity<Object> venteconclus(int idMpivarotra, int idMpividy, int idAnnonce, double vola) throws Exception{
        Utilisateur mpivarotra = utilisateur.getUserById(idMpivarotra);
        Utilisateur mpividy = utilisateur.getUserById(idMpividy);
        /*resaka vola amzay*/
        double volaCommision=0;
        double c=0;
        double volaClient=0;
        c = commissionService.getCommission().getCommission();
        volaCommision = c*vola;
        volaClient = vola-volaCommision;
         //soldeCommissionService.addSolde(volaCommision);
         //soldeService.addSolde(mpivarotra,volaClient);

         //soldeService.addSolde(mpividy,-vola);

        Annonce annonce = annonceService.getById(idAnnonce);
        annonce.setEtat(20);
        Vente vente= new Vente(annonce,mpividy,vola, LocalDateTime.now());
        try{
            venteService.save(mpivarotra, mpividy, vente, vola, volaCommision);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("La transaction n'a pas été effectué : "+e);
        }
        return ResponseEntity.ok().body("transaction effectuer");
    }
}
