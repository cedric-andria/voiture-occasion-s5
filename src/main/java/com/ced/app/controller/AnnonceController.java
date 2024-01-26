package com.ced.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ced.app.model.Annonce;
import com.ced.app.service.AnnonceService;

import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class AnnonceController {
    @Autowired
    private AnnonceService annonceService;

    @GetMapping("/annonces")
	public List<Annonce> getAllAnnonces(){
        // for (Annonce annonce : annonceService.getAllAnnonces()) {
        //     System.out.println("annonce id : " + annonce.getId());
        // }
		return annonceService.getAllAnnonces();
	}

    @GetMapping("/annonces/{id}")
	public ResponseEntity<Annonce> getAnnonceById(@PathVariable int id){
		return annonceService.findById(id).map(annonce -> new ResponseEntity<>(annonce, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

    @PostMapping("/annonces")
    public ResponseEntity<Annonce> saveAnnonce(@RequestBody Annonce annonce) {
        return new ResponseEntity<>(annonceService.save(annonce), HttpStatus.CREATED);
    }

    @PutMapping("/annonces/{id}")
    public ResponseEntity<Annonce> updateAnnonce(@PathVariable int id, @RequestBody Annonce updatedAnnonce) {
        // Check if the Annonce with the given id exists
        Optional<Annonce> existingAnnonceOptional = annonceService.findById(id);

        if (existingAnnonceOptional.isPresent()) {
            Annonce existingAnnonce = existingAnnonceOptional.get();

            updatedAnnonce.setId(existingAnnonce.getId());

            // Save the updated Annonce
            Annonce savedAnnonce = annonceService.update(updatedAnnonce);

            return new ResponseEntity<>(savedAnnonce, HttpStatus.OK);
        } else {
            // Annonce with the given id not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
