package com.ced.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.repository.HistoriqueAnnonceRepository;

@Service
public class HistoriqueAnnonceService {
    @Autowired
    private HistoriqueAnnonceRepository historiqueAnnonceRepository;

    public List<HistoriqueAnnonce> getAll()
    {
        return historiqueAnnonceRepository.findAll();
    }

    public HistoriqueAnnonce getById(int id)
    {
        return historiqueAnnonceRepository.getById(id);
    }

    public HistoriqueAnnonce save(HistoriqueAnnonce histoannonce)
    {
        return historiqueAnnonceRepository.save(histoannonce);
    }
}
