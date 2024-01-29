
package com.ced.app.controller;

import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.service.SoldeUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/soldeutilisateur")
public class SoldeUtilisateurController {
    @Autowired
    private SoldeUtilisateurService soldeService;

    @GetMapping
    public ResponseEntity<Object> getSoldeUser(@RequestBody Map<String, String> data){
        try {
            SoldeUtilisateur solde = soldeService.getSoldeUser(data.get("email"));
            return ResponseEntity.ok().body(solde);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Cannot find balance : "+e);
        }
    }

    @PutMapping("/recharge")
    public ResponseEntity<Object> rechargeBalance(@RequestBody Map<String, String> data){
        try{
            soldeService.setSolde(data.get("email"), Double.valueOf(data.get("solde")));
            return ResponseEntity.ok().body("Balance recharging success");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Cannot recharge the balance : "+e);
        }
    }
}
