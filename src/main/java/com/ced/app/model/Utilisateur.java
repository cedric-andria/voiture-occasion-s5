package com.ced.app.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

import com.ced.app.model.Profil;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;

@Entity
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    @Column(name="identifiant", unique = true)
    private String identifiant;
    private String mdp;
    @ManyToOne
    @JoinColumn(name="id_profil")
    private Profil profil;

    public Utilisateur(String nom, String identifiant, String mdp, Profil profil) {
        this.nom = nom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.profil = profil;
    }

    public Utilisateur(int id, String nom, String identifiant, String mdp, Profil profil) {
        this.id = id;
        this.nom = nom;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.profil = profil;
    }

    public Utilisateur(String nom, String identifiant, String mdp) {
        this.setNom(nom);
        this.setIdentifiant(identifiant);
        this.setMdp(mdp);
    }

    public Utilisateur() {
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

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    

}
