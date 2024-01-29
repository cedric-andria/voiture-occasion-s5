package com.ced.app.service;

import com.ced.app.model.Marque;
import com.ced.app.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
// import com.ced.app.crud.service.GenericService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
    @Autowired
    private MarqueRepository marqueRepository;

    public MarqueService(MarqueRepository marqueRepository) {
        this.marqueRepository = marqueRepository;
    }

    public List<Marque> getAllMarque()
    {
        return marqueRepository.findAll();
    }

    public void deleteMarque(int id)
    {
        if(!marqueRepository.existsById(id))
        {
            throw new IllegalStateException("La Marque id :"+id+" n'existe pas");
        }
        System.out.println(marqueRepository.findById(id).toString()+" will be DELETED");
        marqueRepository.deleteById(id);
    }

    @Transactional
    public void updateMarque(int id, String nom)
    {
        Marque emp = marqueRepository.findById(id).orElseThrow(() -> new IllegalStateException("--UPDATE IMPOSSIBLE-- La Marque id :\"+id+\" n'existe pas "));
        if(nom!=null)
        {
            emp.setNom(nom);
        }
        System.out.println("UPDATE REUSSI");
    }

    public void addMarque(Marque marque) {
        Optional<Marque> marqueExist = marqueRepository.findByNom(marque.getNom());
        if(marqueExist.isPresent())
        {
            throw new IllegalStateException("Marque : "+marque+" deja dans la base");
        }
        marqueRepository.save(marque);
        System.out.println(marque.toString() +" TONGA soamantsara");
    }

    public Marque getMarqueById(int id)
    {
        return marqueRepository.findById(id).orElseThrow(() -> new IllegalStateException("Marque ID: "+id+" n'existe pas"));
    }
    public List<Marque> getAllMarquesWithNbVenteBetween(LocalDateTime dateDebut, LocalDateTime dateFin) {
        List<Object[]> results = marqueRepository.findMarquesWithNbVenteBetween(dateDebut, dateFin);
        List<Marque> marques = new ArrayList<>();
        for (Object[] result : results)
        {
            Marque marque = new Marque();
            marque.setId((int) result[0]);
            marque.setNom((String) result[1]);
            marque.setNb_vente(((Long) result[2]).intValue());
            marques.add(marque);
        }
        return marques;
    }
    public List<Marque> getAllMarquesWithNbVente() {
        List<Object[]> results = marqueRepository.findMarquesWithNbVente();
        List<Marque> marques = new ArrayList<>();
        for (Object[] result : results)
        {
            Marque marque = new Marque();
            marque.setId((int) result[0]);
            marque.setNom((String) result[1]);
            marque.setNb_vente(((Long) result[2]).intValue());
            marques.add(marque);
        }
        return marques;
    }
}
