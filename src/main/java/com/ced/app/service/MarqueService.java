package com.ced.app.service;

import java.util.List;

// import com.ced.app.crud.service.GenericService;
import com.ced.app.model.Marque;
import com.ced.app.repository.MarqueRepository;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

@Service
public class MarqueService {
    @Autowired
    private MarqueRepository marqueRepository;

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Marque getById(int id)
    {
        return marqueRepository.getById(id);
    }
}
