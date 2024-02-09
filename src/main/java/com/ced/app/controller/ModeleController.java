package com.ced.app.controller;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import com.ced.app.service.MarqueService;
import com.ced.app.service.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequestMapping("/modele")
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ModeleController {
    @Autowired
    private ModeleService modeleService;

    @Autowired
    private MarqueService service;
    @PostMapping
    public ResponseEntity<Modele> save(@RequestBody Map<String, String>data ){

        String nom = data.get("nom");

        Marque marque = service.getMarqueById(Integer.valueOf(data.get("id_marque")));
        Modele model = new Modele(nom,marque);
        return new ResponseEntity<Modele>(modeleService.save(model), HttpStatus.OK);

    }

    @GetMapping("/{id}/marques/")
    public List<Modele> getAllModeleByMarque(@PathVariable int id)
    {
        return modeleService.getAllByMarque(id);
    }

    @GetMapping("/{id}")
    public Modele getById( @PathVariable("id") int id){
        return modeleService.getById(id);
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
