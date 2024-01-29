package com.ced.app.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_voiture")
    private Voiture voiture;
    private double prix;
    private String description;
    private int etat;
    private LocalDateTime date_publication;
    @Transient
    private List<PhotoVoiture> photos_voiture;
    
    public Annonce(Voiture voiture, double prix, String description, int etat, LocalDateTime date_publication) {
        this.voiture = voiture;
        this.prix = prix;
        this.description = description;
        this.etat = etat;
        this.date_publication = date_publication;
    }
    public Annonce(int id, Voiture voiture, double prix, String description, int etat, LocalDateTime date_publication) {
        this.id = id;
        this.voiture = voiture;
        this.prix = prix;
        this.description = description;
        this.etat = etat;
        this.date_publication = date_publication;
    }
    public Annonce() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Voiture getVoiture() {
        return voiture;
    }
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getEtat() {
        return etat;
    }
    public void setEtat(int etat) {
        this.etat = etat;
    }
    public LocalDateTime getDate_publication() {
        return date_publication;
    }
    public void setDate_publication(LocalDateTime date_publication) {
        this.date_publication = date_publication;
    }
    public List<PhotoVoiture> getPhotos_voiture() {
        return photos_voiture;
    }
    public void setPhotos_voiture(List<PhotoVoiture> photos_voiture) {
        this.photos_voiture = photos_voiture;
    }

}
