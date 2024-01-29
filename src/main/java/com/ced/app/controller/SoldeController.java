
package com.ced.app.controller;

import com.ced.app.model.SoldeUtilisateur;
import com.ced.app.service.SoldeUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/solde")
public class SoldeController {
    @Autowired
    private SoldeUtilisateurService soldeService;

    public ResponseEntity<Object> getSoldeUser(@RequestBody Map<String, String> data){
        try {
            SoldeUtilisateur solde = soldeService.getSoldeUser(data.get("email"));
            return ResponseEntity.ok().body(solde);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Cannot find balance : "+e);
        }
    }

    @GetMapping("/recharge")
    public ResponseEntity<Object> rechargeBalance(@RequestBody Map<String, String> data){
        try{
            soldeService.setSolde(data.get("email"), Double.valueOf(data.get("solde")));
            return ResponseEntity.ok().body("Balance recharging success");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Cannot recharge the balance : "+e);
        }
    }
}
