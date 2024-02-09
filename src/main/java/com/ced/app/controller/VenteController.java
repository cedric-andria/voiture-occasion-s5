package com.ced.app.controller;

import com.ced.app.model.Annonce;
import com.ced.app.model.Categorie;
import com.ced.app.model.Marque;
import com.ced.app.model.StatVente_Marque;
import com.ced.app.model.Utilisateur;
import com.ced.app.model.Vente;
import com.ced.app.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(path = "/vente")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Content-Type", "Authorization"}, allowCredentials = "true")
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
    private MarqueService marqueService;
    @Autowired
    private CategorieService categorieService;
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

    @GetMapping("/statmarque")
    public ResponseEntity<HashMap<String, Object>> getStatVente_marque() {
        HashMap<String, Object> hashMap_marque_stat = new HashMap<>();

        double totalVentes = (double)venteService.countAllVentes();
        System.out.println("total ventes : " + totalVentes);
        for(Marque marque : marqueService.getAllMarque())
        {
            System.out.println("id marque : " + marque.getId());
            List<Vente> ventes_one_marque = venteService.getAllStatFromOneMarque(marque.getId());
            System.out.println("vente one marque.size : " + ventes_one_marque.size());
            hashMap_marque_stat.put(marque.getNom(), (ventes_one_marque.size()/totalVentes)*100);
        }

        return new ResponseEntity<HashMap<String, Object>>(hashMap_marque_stat, HttpStatus.OK);
    }

    @GetMapping("/statcategorie")
    public ResponseEntity<HashMap<String, Object>> getStatVente_categorie() {
        HashMap<String, Object> hashMap_categorie_stat = new HashMap<>();

        double totalVentes = (double)venteService.countAllVentes();
        System.out.println("total ventes : " + totalVentes);
        for(Categorie categorie : categorieService.getAllCategorie())
        {
            System.out.println("id categorie : " + categorie.getId());
            List<Vente> ventes_one_categorie = venteService.getAllStatFromOneCategorie(categorie.getId());
            // System.out.println("vente one marque.size : " + ventes_one_categorie.size());
            hashMap_categorie_stat.put(categorie.getNom(), (ventes_one_categorie.size()/totalVentes)*100);
        }

        return new ResponseEntity<HashMap<String, Object>>(hashMap_categorie_stat, HttpStatus.OK);
    }
    
    
}
