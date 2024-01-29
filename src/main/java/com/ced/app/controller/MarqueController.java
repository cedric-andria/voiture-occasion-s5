package com.ced.app.controller;

import com.ced.app.model.Marque;
import com.ced.app.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ced.app.service.MarqueService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void updateCategorire(@PathVariable("id") int id, @RequestParam(required = false) String nom)
    {
        marqueService.updateMarque(id,nom);
    }

    @PostMapping("")
    public void addNewMarque( @RequestBody Marque marque)
    {
        this.marqueService.addMarque(marque);
    }
}
