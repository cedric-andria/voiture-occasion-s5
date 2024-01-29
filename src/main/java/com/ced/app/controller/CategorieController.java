package com.ced.app.controller;

import com.ced.app.model.Categorie;
import com.ced.app.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Categorie")
@CrossOrigin(origins = "*", methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("")
    public List<Categorie> getAllVehicules(){
        return categorieService.getAllCategorie();
    }

    @GetMapping("/{id}")
    public Categorie getById( @PathVariable("id") int id){
        return categorieService.getCategorieById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable("id") int id)
    {
        categorieService.deleteCategorie(id);
        return "Voafafa ny Categorie(id="+id+")";
    }

    @PutMapping("/{id}")
    public void updateCategorire(@PathVariable("id") int id, @RequestParam(required = false) String nom)
    {
        categorieService.updateCategorie(id,nom);
    }

    @PostMapping("")
    public void addNewCategorie( @RequestBody Categorie categorie)
    {
        this.categorieService.addCategorie(categorie);
    }

}
