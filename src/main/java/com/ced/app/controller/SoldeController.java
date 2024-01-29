package com.ced.app.controller;


import com.ced.app.model.Solde;
import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.service.SoldeService;
import com.ced.app.service.SoldeUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/solde")
public class SoldeController {
    @Autowired
    private SoldeService soldeService;

    @GetMapping
    public ResponseEntity<Object> getSoldeBackOffice(@RequestBody Map<String, String> data){
        try {
            Solde solde = soldeService.getSolde();
            return ResponseEntity.ok().body(solde);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Cannot find balance : "+e);
        }
    }
}
