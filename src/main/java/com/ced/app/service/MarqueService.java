package com.ced.app.service;

import com.ced.app.model.Marque;
import com.ced.app.repository.MarqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarqueService {
    @Autowired
    private MarqueRepository repo;

    public Marque getbyid(int id){
        return repo.findById(id).get();
    }
}
