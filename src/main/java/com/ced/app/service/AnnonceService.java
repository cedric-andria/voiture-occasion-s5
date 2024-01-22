package com.ced.app.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.Annonce;
import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.repository.AnnonceRepository;
import com.ced.app.repository.HistoriqueAnnonceRepository;

import jakarta.transaction.Transactional;

@Service
public class AnnonceService {
    @Autowired 
    private AnnonceRepository annonceRepository;

    private HistoriqueAnnonceRepository histoannonceRepository;

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public Annonce getById(int id)
    {
        return annonceRepository.getById(id);
    }

    public Optional<Annonce> findById(int id)
    {
        return annonceRepository.findById(id);
    }

    @Transactional
    public Annonce save(Annonce annonce)
    {
        histoannonceRepository.save(new HistoriqueAnnonce(annonce, LocalDateTime.now(), 0));
        return annonceRepository.save(annonce);
    }

    @Transactional
    public Annonce update(Annonce annonce)
    {
        histoannonceRepository.save(new HistoriqueAnnonce(annonce, LocalDateTime.now(), annonce.getEtat()));
        return annonceRepository.save(annonce);
    }

}
