package com.ced.app.service;

import java.util.List;
import java.util.Optional;
// import com.ced.app.crud.service.GenericService;
import com.ced.app.model.Marque;
import com.ced.app.repository.MarqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MarqueService {
    @Autowired
    private final MarqueRepository marqueRepository;

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
    }}
