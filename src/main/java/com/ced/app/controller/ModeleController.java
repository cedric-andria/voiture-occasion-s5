package com.ced.app.controller;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import com.ced.app.service.MarqueService;
import com.ced.app.service.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RequestMapping("/modele")
@RestController
public class ModeleController {
    @Autowired
    private ModeleService modeleService;

    @Autowired
    private MarqueService service;
    @PostMapping
    public void save(@RequestBody Map<String, String>data ){

        String nom = data.get("nom");

        Marque marque = service.getbyid(Integer.valueOf(data.get("id_marque")));
       Modele model = new Modele(nom,marque);
        modeleService.save(model);

    }

    /** REST
    * Emp
    * /emp : GET
     * /emp/id : GET
     * /emp : POST
     * /emp/id : PUT
     * /emp/id : DELETE
     *
    * */
}
