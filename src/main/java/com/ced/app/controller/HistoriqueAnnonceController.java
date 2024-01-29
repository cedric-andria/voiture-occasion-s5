package com.ced.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ced.app.model.Annonce;
import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.model.Utilisateur;
import com.ced.app.service.AnnonceService;
import com.ced.app.service.HistoriqueAnnonceService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class HistoriqueAnnonceController {
    @Autowired
    private HistoriqueAnnonceService historiqueAnnonceService;

    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/historique_annonce")
	public List<HistoriqueAnnonce> getAllHistorique(){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
		return historiqueAnnonceService.getAll();
	}

    @GetMapping("/historique_annonce/current_user")
	public List<HistoriqueAnnonce> getHistoriqueCurrentUser(@RequestHeader("Authorization") String bearerToken){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }

        int id_user_actuel = 0;
        
        try {
            id_user_actuel = Integer.parseInt(Utilisateur.extractId(bearerToken.substring(7)));
            System.out.println("id user actuel " + id_user_actuel);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalStateException("non autorisé");
        }
		return historiqueAnnonceService.getHistoriqueOfUser(id_user_actuel);
	}
}
