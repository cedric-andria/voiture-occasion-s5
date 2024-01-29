package com.ced.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_voiture")
    private Voiture voiture;
    private String chemin;
    
    public Photo(String chemin) {
        this.chemin = chemin;
    }
    public Photo(int id, Voiture voiture, String chemin) {
        this.id = id;
        this.voiture = voiture;
        this.chemin = chemin;
    }
    
    public Photo(Voiture voiture, String chemin) {
        this.voiture = voiture;
        this.chemin = chemin;
    }
    public Photo() {
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
    public String getChemin() {
        return chemin;
    }
    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

}
