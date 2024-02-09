package com.ced.app.controller;

import com.ced.app.model.Categorie;
import com.ced.app.model.Marque;
import com.ced.app.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ced.app.service.MarqueService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "Marque")
@CrossOrigin(origins = {"http://localhost:3000"}, methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Content-Type", "Authorization"}, allowCredentials = "true")
public class MarqueController {
    @Autowired
    private MarqueService marqueService;

    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping("")
    public List<Marque> getAllVehicules(){
        return marqueService.getAllMarque();
    }


    @GetMapping("/{id}")
    public Marque getById( @PathVariable("id") int id){
        return marqueService.getMarqueById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable("id") int id)
    {
        marqueService.deleteMarque(id);
        return "Voafafa ny Marque(id="+id+")";
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marque> updateMarque(@PathVariable("id") int id, @RequestBody Marque marque)
    {
        // marqueService.updateMarque(id,nom);
        Optional<Marque> existingMarqueOptional = marqueService.findById(id);
        if (existingMarqueOptional.isPresent()) {

            // Save the updated Annonce
            marque.setId(existingMarqueOptional.get().getId());
            // Categorie savedCategorie = categorieService.updateCategorie(id,nom);
            Marque savedMarque = marqueService.update(marque);
            System.out.println("Status ok");
            
            return new ResponseEntity<>(savedMarque, HttpStatus.OK);
        } else {
            // Annonce with the given id not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Marque> addNewMarque(@RequestBody Marque marque)
    {
        Marque savedMarque = this.marqueService.addMarque(marque);
        return new ResponseEntity<Marque>(savedMarque, HttpStatus.OK);
    }

    @GetMapping("/nbVenteBetween")
    public List<Marque> getAllMarquesWithNbVenteBetween(@RequestParam("dateDebut") String dateDebut, @RequestParam("dateFin") String dateFin)
    {
        LocalDateTime debut = convertStringToLocalDateTime(dateDebut,"yyyy-MM-dd HH:mm:ss");
        LocalDateTime fin = convertStringToLocalDateTime(dateFin,"yyyy-MM-dd HH:mm:ss");
        return marqueService.getAllMarquesWithNbVenteBetween(debut, fin);
    }
    @GetMapping("/nbVente")
    public List<Marque> getAllMarquesWithNbVente()
    {
        return marqueService.getAllMarquesWithNbVente();
    }
    public static LocalDateTime convertStringToLocalDateTime(String dateTimeStr, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date-time format. Please use " + format, e);
        }
    }
}
