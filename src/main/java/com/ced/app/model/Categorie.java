package com.ced.app.model;

import jakarta.persistence.*;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Transient
    private int nb_vente;
    public Categorie(String nom) {
        this.nom = nom;
    }

    public Categorie() {
    }

    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNb_vente() {
        return nb_vente;
    }

    public void setNb_vente(int nb_vente) {
        this.nb_vente = nb_vente;
    }

    @Override
    public String toString() {
        return "Categorie : {" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
