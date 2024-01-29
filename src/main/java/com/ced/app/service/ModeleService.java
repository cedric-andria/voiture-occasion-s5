package com.ced.app.service;

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

}