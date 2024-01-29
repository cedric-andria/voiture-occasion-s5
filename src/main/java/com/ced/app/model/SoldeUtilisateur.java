package com.ced.app.model;

import jakarta.persistence.*;

@Entity
public class SoldeUtilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn(name = "idutilisateur")
    Utilisateur utilisateur;
    double solde;

    public SoldeUtilisateur() {
    }

    public SoldeUtilisateur(int id, Utilisateur utilisateur, double solde) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.solde = solde;
    }

    public SoldeUtilisateur(Utilisateur utilisateur, double solde) {
        this.utilisateur = utilisateur;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
