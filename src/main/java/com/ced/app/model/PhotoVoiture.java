package com.ced.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PhotoVoiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_photo")
    private Photo photo;
    @ManyToOne
    @JoinColumn(name="id_annonce")
    private Annonce annonce;

    public PhotoVoiture(Photo photo, Annonce annonce) {
        this.photo = photo;
        this.annonce = annonce;
    }
    public PhotoVoiture(int id, Photo photo, Annonce annonce) {
        this.id = id;
        this.photo = photo;
        this.annonce = annonce;
    }
    public PhotoVoiture() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Photo getPhoto() {
        return photo;
    }
    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
    public Annonce getAnnonce() {
        return annonce;
    }
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }

}
