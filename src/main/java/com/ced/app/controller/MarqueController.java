package com.ced.app.controller;

import com.ced.app.model.Marque;
import com.ced.app.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ced.app.service.MarqueService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping(path = "Marque")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
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
    public void updateMarque(@PathVariable("id") int id, @RequestParam(required = false) String nom)
    {
        marqueService.updateMarque(id,nom);
    }

    @PostMapping("")
    public void addNewMarque( @RequestBody Marque marque)
    {
        this.marqueService.addMarque(marque);
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
