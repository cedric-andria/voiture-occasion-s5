package com.ced.app.controller;

import com.ced.app.model.Categorie;
import com.ced.app.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "Categorie")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("all")
    public List<Categorie> getAllVehicules(){
        return categorieService.getAllCategorie();
    }


    @GetMapping("find/{id}")
    public Categorie getById( @PathVariable("id") int id){
        return categorieService.getCategorieById(id);
    }

    @DeleteMapping("delete/{id}")
    public String deleteOne(@PathVariable("id") int id)
    {
        categorieService.deleteCategorie(id);
        return "Voafafa ny Categorie(id="+id+")";
    }

    @PutMapping("update/{id}")
    public void updateCategorire(@PathVariable("id") int id, @RequestParam(required = false) String nom)
    {
        categorieService.updateCategorie(id,nom);
    }

    @PostMapping("insert")
    public void addNewCategorie( @RequestBody Categorie categorie)
    {
        this.categorieService.addCategorie(categorie);
    }

}
