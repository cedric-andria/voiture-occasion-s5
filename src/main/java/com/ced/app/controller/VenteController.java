package com.ced.app.controller;

import com.ced.app.model.Annonce;
import com.ced.app.model.Utilisateur;
import com.ced.app.model.Vente;
import com.ced.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

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
    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping
    public ResponseEntity<Object> venteconclus(@RequestHeader("Authorization") String bearerToken, @RequestBody Map<String, String> data) throws Exception{

        int id_user_actuel = 0;
        
        try {
            id_user_actuel = Integer.parseInt(Utilisateur.extractId(bearerToken.substring(7)));
            System.out.println("id user actuel " + id_user_actuel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double prix = Double.parseDouble(data.get("prix"));

        //miova ny statut anle annonce
        Utilisateur mpivarotra = utilisateur.getUserById(Integer.parseInt(data.get("id_vendeur")));
        Utilisateur mpividy = utilisateur.getUserById(id_user_actuel);
        /*resaka vola amzay*/
        double volaCommision=0;
        double c=0;
        double volaClient=0;
        c = commissionService.getCommission().getCommission();
        volaCommision = c*prix;
        volaClient = prix-volaCommision;
         //soldeCommissionService.addSolde(volaCommision);
         //soldeService.addSolde(mpivarotra,volaClient);

         //soldeService.addSolde(mpividy,-vola);

        Annonce annonce = annonceService.getById(Integer.parseInt(data.get("idAnnonce")));
        // Vente vente= new Vente(annonce,mpividy,prix-volaCommision, LocalDateTime.now());
        annonce.setEtat(20);
        Vente vente = new Vente(annonce, mpividy, Double.parseDouble("prix") - volaCommision, LocalDateTime.now());

        try{
            venteService.save(mpivarotra, mpividy, vente, prix, volaCommision);
            annonceService.update(annonce);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("La transaction n'a pas été effectué : "+e);
        }
        return ResponseEntity.ok().body("transaction effectuer");
    }
}
