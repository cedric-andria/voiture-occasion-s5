package com.ced.app.service;

import com.ced.app.model.Categorie;
import com.ced.app.repository.CategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {
    @Autowired
    private final CategorieRepository categorieRepository;

    public CategorieService(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    public List<Categorie> getAllCategorie()
    {
        return categorieRepository.findAllByOrderById();
    }

    public Optional<Categorie> findById(int id){
        return categorieRepository.findById(id);
    }

    public void deleteCategorie(int id)
    {
        if(!categorieRepository.existsById(id))
        {
            throw new IllegalStateException("La Categorie id :"+id+" n'existe pas");
        }
        System.out.println(categorieRepository.findById(id).toString()+" will be DELETED");
        categorieRepository.deleteById(id);
    }

    @Transactional
    public Categorie updateCategorie(int id, String nom)
    {
        Categorie emp = categorieRepository.findById(id).orElseThrow(() -> new IllegalStateException("--UPDATE IMPOSSIBLE-- La Categorie id :\"+id+\" n'existe pas "));
        if(nom!=null)
        {
            emp.setNom(nom);
        }
        System.out.println("UPDATE REUSSI");
        return emp;
    }

    public void addCategorie(Categorie categorie) {
        Optional<Categorie> categorieExist = categorieRepository.findByNom(categorie.getNom());
        if(categorieExist.isPresent())
        {
            throw new IllegalStateException("Categorie : "+categorie+" deja dans la base");
        }
        categorieRepository.save(categorie);
        System.out.println(categorie.toString() +" TONGA soamantsara");
    }

    public Categorie getCategorieById(int id)
    {
        return categorieRepository.findById(id).orElseThrow(() -> new IllegalStateException("Categorie ID: "+id+" n'existe pas"));
    }
    public List<Categorie> getAllCategorieWithNbVenteBetween(LocalDateTime dateDebut, LocalDateTime dateFin) {
        List<Object[]> results = categorieRepository.findCategoriesWithNbVenteBetween(dateDebut,dateFin);
        List<Categorie> categories = new ArrayList<Categorie>();
        for (Object[] result : results)
        {
            Categorie categorie = new Categorie();
            categorie.setId((int) result[0]);
            categorie.setNom((String) result[1]);
            categorie.setNb_vente(((Long) result[2]).intValue());
            categories.add(categorie);
        }
        return categories;
    }
    public List<Categorie> getAllCategorieWithNbVente() {
        List<Object[]> results = categorieRepository.findCategoriesWithNbVente();
        List<Categorie> categories = new ArrayList<>();
        for (Object[] result : results)
        {
            Categorie categorie = new Categorie();
            categorie.setId((int) result[0]);
            categorie.setNom((String) result[1]);
            categorie.setNb_vente(((Long) result[2]).intValue());
            categories.add(categorie);
        }
        return categories;
    }
}
