package com.ced.app.service.vaovao;

import com.ced.app.model.Vaovao.Categorie;
import com.ced.app.repository.vaovao.CategorieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return categorieRepository.findAll();
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
    public void updateCategorie(int id, String nom)
    {
        Categorie emp = categorieRepository.findById(id).orElseThrow(() -> new IllegalStateException("--UPDATE IMPOSSIBLE-- La Categorie id :\"+id+\" n'existe pas "));
        if(nom!=null)
        {
            emp.setNom(nom);
        }
        System.out.println("UPDATE REUSSI");
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
}
