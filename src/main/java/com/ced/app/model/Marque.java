package com.ced.app.model;

import jakarta.persistence.*;

@Entity
public class Marque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Transient
    private int nb_vente;
    public Marque(String nom) {
        this.nom = nom;
    }
    public Marque(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public Marque() {
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
        return "Marque{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
