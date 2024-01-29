package com.ced.app.controller;

import com.ced.app.model.Categorie;
import com.ced.app.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

    @GetMapping("/nbVenteBetween")
    public List<Categorie> getAllCategoriesWithNbVenteBetween(@RequestParam("dateDebut") String dateDebut, @RequestParam("dateFin") String dateFin)
    {
        LocalDateTime debut = convertStringToLocalDateTime(dateDebut,"yyyy-MM-dd HH:mm:ss");
        LocalDateTime fin = convertStringToLocalDateTime(dateFin,"yyyy-MM-dd HH:mm:ss");
        return categorieService.getAllCategorieWithNbVenteBetween(debut, fin);
    }
    @GetMapping("/nbVente")
    public List<Categorie> getAllCategoriesWithNbVente()
    {
        return categorieService.getAllCategorieWithNbVente();
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
