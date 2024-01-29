package com.ced.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.Annonce;
import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.model.PhotoVoiture;
import com.ced.app.repository.HistoriqueAnnonceRepository;
import com.ced.app.repository.PhotoVoitureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class PhotoVoitureService {
    @Autowired
    private PhotoVoitureRepository photoVoitureRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<PhotoVoiture> getAllPhotoVoitures() {
        return photoVoitureRepository.findAll();
    }

    public PhotoVoiture getById(int id)
    {
        return photoVoitureRepository.getById(id);
    }

    public Optional<PhotoVoiture> findById(int id)
    {
        return photoVoitureRepository.findById(id);
    }

    public PhotoVoiture save(PhotoVoiture photovoiture)
    {
        return photoVoitureRepository.save(photovoiture);
    }

    public PhotoVoiture update(PhotoVoiture photovoiture)
    {
        return photoVoitureRepository.save(photovoiture);
    }
}
