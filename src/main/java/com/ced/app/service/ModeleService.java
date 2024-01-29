package com.ced.app.service;

import com.ced.app.model.Marque;
import com.ced.app.model.Modele;
import com.ced.app.repository.ModeleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModeleService {
    @Autowired
    private ModeleRepository repo;

    public void save(Modele model){
        repo.save(model);
    }

    public Modele getById(int id)
    {
        return repo.findById(id).orElseThrow(() -> new IllegalStateException("Modele ID: "+id+" n'existe pas"));
    }
}
