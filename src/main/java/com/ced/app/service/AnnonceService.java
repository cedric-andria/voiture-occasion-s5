package com.ced.app.service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ced.app.model.Annonce;
import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.model.Photo;
import com.ced.app.model.PhotoVoiture;
import com.ced.app.repository.AnnonceRepository;
import com.ced.app.repository.HistoriqueAnnonceRepository;
import com.ced.app.repository.PhotoRepository;
import com.ced.app.repository.PhotoVoitureRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class AnnonceService {
    @Autowired 
    private AnnonceRepository annonceRepository;

    @Autowired 
    private HistoriqueAnnonceRepository histoannonceRepository;

    @Autowired 
    private PhotoVoitureRepository photoVoitureRepository;

    @Autowired 
    private PhotoRepository photoRepository;

    @PersistenceContext
    private EntityManager entityManager;


    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public List<Annonce> getAnnoncesNonValidees() {
        // return stockMateriauRepository.findTopByMateriauIdOrderByDate_stockDesc(idMateriau);
        String nativeQuery = "SELECT * FROM Annonce where etat < 10";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, Annonce.class);

        @SuppressWarnings("unchecked")
        List<Annonce> annonces = query.getResultList();

        return annonces;
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
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        try {
            //mbola mi upload
//            for (PhotoVoiture photo_annonce : annonce.getPhotos_voiture()) {
//                //mbola ilay chemin cote client izy eto fa miandry anle response zay vao azo ilay chemin alefa any am base
//                formData.add("image", photo_annonce.getPhoto().getChemin());
//                formData.clear();
//            }
//            histoannonceRepository.save(new HistoriqueAnnonce(annonce, LocalDateTime.now(), 0));
//            for (PhotoVoiture photo_annonce : annonce.getPhotos_voiture()) {
//                photoRepository.save(photo_annonce.getPhoto());
//                photoVoitureRepository.save(photo_annonce);
//            }
        } catch (RestClientException restexc) {
            System.out.println(restexc);
        }
        
        return annonceRepository.save(annonce);
    }

    @Transactional
    public Annonce update(Annonce annonce)
    {
        histoannonceRepository.save(new HistoriqueAnnonce(annonce, LocalDateTime.now(), 10));
        return annonceRepository.save(annonce);
    }

    public List<PhotoVoiture> get_photos_of_annonce(int id_annonce, int nb)
    {
        String nativeQuery = "SELECT * FROM PhotoVoiture where id_annonce = :id_annonce order by id desc limit :nb_photos";
        jakarta.persistence.Query query = entityManager.createNativeQuery(nativeQuery, PhotoVoiture.class);
        query.setParameter("id_annonce", id_annonce);
        query.setParameter("nb_photos", nb);


        @SuppressWarnings("unchecked")
        List<PhotoVoiture> photos_voiture = query.getResultList();

        return photos_voiture;
    }

}
