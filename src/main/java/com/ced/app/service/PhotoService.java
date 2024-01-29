package com.ced.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ced.app.model.Photo;
import com.ced.app.repository.PhotoRepository;
import com.ced.app.repository.PhotoVoitureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    public Photo getById(int id)
    {
        return photoRepository.getById(id);
    }

    public Optional<Photo> findById(int id)
    {
        return photoRepository.findById(id);
    }

    public Photo save(Photo photo)
    {
        return photoRepository.save(photo);
    }

    public Photo update(Photo photo)
    {
        return photoRepository.save(photo);
    }

}
