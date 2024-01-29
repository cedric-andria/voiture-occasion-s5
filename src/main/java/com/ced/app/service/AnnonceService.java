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
import com.ced.app.model.Categorie;
import com.ced.app.model.HistoriqueAnnonce;
import com.ced.app.model.Modele;
import com.ced.app.model.Photo;
import com.ced.app.model.PhotoVoiture;
import com.ced.app.model.Utilisateur;
import com.ced.app.model.Voiture;

import com.ced.app.repository.AnnonceRepository;
import com.ced.app.repository.HistoriqueAnnonceRepository;
import com.ced.app.repository.PhotoRepository;
import com.ced.app.repository.PhotoVoitureRepository;
import com.ced.app.repository.VoitureRepository;

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

    @Autowired 
    private VoitureRepository voitureRepository;

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
    public Annonce save(Annonce annonce, Voiture voiture)
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        String imgbb_unique_link_image = null;
        String part_ilaina = null;

        Voiture voiture_temporaire = null;
        Photo photo_temporaire = null;
        PhotoVoiture photoVoiture_temporaire = null;
        try {
            //rehefa eo am react dia ny object annonce asiana array de photos_voiture
            voiture_temporaire = voitureRepository.save(voiture);
            for (PhotoVoiture photo_annonce : annonce.getPhotos_voiture()) {
                //mbola ilay chemin cote client izy eto fa miandry anle response zay vao azo ilay chemin alefa any am base
                formData.add("image", photo_annonce.getPhoto().getChemin());
                try {
                    String ws_response = restTemplate.postForObject("https://api.imgbb.com/1/upload?key=ca01a4a5f52ac1e27bb4d636d622ba24", formData, String.class);
                    System.out.println("ws_response :" + ws_response);
                    part_ilaina = ws_response.split("url")[2];
                    imgbb_unique_link_image = part_ilaina.substring(part_ilaina.lastIndexOf("i.ibb.co\\/")+10, part_ilaina.lastIndexOf("\\/"));

                    //efa ilay lien any am imgbb no alefa any am base
                    photo_annonce.getPhoto().setChemin(imgbb_unique_link_image + "/" + photo_annonce.getPhoto().getChemin());

                    photo_temporaire = photoRepository.save(new Photo(voiture_temporaire, photo_annonce.getPhoto().getChemin()));
                    photoVoiture_temporaire = photoVoitureRepository.save(new PhotoVoiture(photo_temporaire, annonce));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //clear the image pseudo form
                formData.clear();
            }
            histoannonceRepository.save(new HistoriqueAnnonce(annonce, LocalDateTime.now(), 0));
            annonce.setVoiture(voiture_temporaire);
            //tsy mila manao setPhotosVoiture tsony satria efa pointeur no tadiavin'ilay setChemin

            // for (PhotoVoiture photo_annonce : annonce.getPhotos_voiture()) {
            //     photoRepository.save(photo_annonce.getPhoto());
            //     photoVoitureRepository.save(photo_annonce);
            // }
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
