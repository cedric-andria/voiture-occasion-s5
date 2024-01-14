package com.ced.app.controller.vaovao;

import com.ced.app.model.Vaovao.Marque;
import com.ced.app.service.MarqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Marque")
public class MarqueController {
    @Autowired
    private MarqueService marqueService;

    public MarqueController(MarqueService marqueService) {
        this.marqueService = marqueService;
    }

    @GetMapping("all")
    public List<Marque> getAllVehicules(){
        return marqueService.getAllMarque();
    }


    @GetMapping("find/{id}")
    public Marque getById( @PathVariable("id") int id){
        return marqueService.getMarqueById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteOne(@PathVariable("id") int id)
    {
        marqueService.deleteMarque(id);
        return "Voafafa ny Marque(id="+id+")";
    }

    @PutMapping("update/{id}")
    public void updateCategorire(@PathVariable("id") int id, @RequestParam(required = false) String nom)
    {
        marqueService.updateMarque(id,nom);
    }

    @PostMapping("insert")
    public void addNewMarque( @RequestBody Marque marque)
    {
        this.marqueService.addMarque(marque);
    }

}
