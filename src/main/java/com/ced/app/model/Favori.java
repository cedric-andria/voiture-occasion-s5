package com.ced.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Favori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_annonce")
    private Annonce annonce;
    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur utilisateur;
    public Favori(Annonce annonce, Utilisateur utilisateur) {
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }
    public Favori(int id, Annonce annonce, Utilisateur utilisateur) {
        this.id = id;
        this.annonce = annonce;
        this.utilisateur = utilisateur;
    }
    public Favori() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Annonce getAnnonce() {
        return annonce;
    }
    public void setAnnonce(Annonce annonce) {
        this.annonce = annonce;
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
