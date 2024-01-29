package com.ced.app.service;

import com.ced.app.model.Profil;
import com.ced.app.repository.PhotoRepository;
import com.ced.app.repository.ProfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfilService {
    @Autowired
    private ProfilRepository profilRepository;

    public Profil getById(int id){
        return profilRepository.findById(id).orElseThrow(() -> new IllegalStateException("Profil ID: "+id+" n'existe pas"));
    }
}
