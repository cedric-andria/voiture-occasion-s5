package com.ced.app.model;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity
public class Voiture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_vendeur")
    private Utilisateur vendeur;
    @ManyToOne
    @JoinColumn(name="id_modele")
    private Modele modele;
    private int annee_sortie;
    @ManyToOne
    @JoinColumn(name="id_categorie")
    private Categorie categorie;

    
    public Voiture(Utilisateur vendeur, Modele modele, int annee_sortie, Categorie categorie) {
        this.vendeur = vendeur;
        this.modele = modele;
        this.annee_sortie = annee_sortie;
        this.categorie = categorie;
    }
    public Voiture(int id, Utilisateur vendeur, Modele modele, int annee_sortie, Categorie categorie) {
        this.id = id;
        this.vendeur = vendeur;
        this.modele = modele;
        this.annee_sortie = annee_sortie;
        this.categorie = categorie;
    }
    public Voiture() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Utilisateur getVendeur() {
        return vendeur;
    }
    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }
    public Modele getModele() {
        return modele;
    }
    public void setModele(Modele modele) {
        this.modele = modele;
    }
    public int getAnnee_sortie() {
        return annee_sortie;
    }
    public void setAnnee_sortie(int annee_sortie) {
        this.annee_sortie = annee_sortie;
    }
    public Categorie getCategorie() {
        return categorie;
    }
    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
