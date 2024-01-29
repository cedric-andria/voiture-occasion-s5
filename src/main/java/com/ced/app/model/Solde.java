package com.ced.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Solde {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    double solde;

    public Solde(int id, double solde) {
        this.id = id;
        this.solde = solde;
    }

    public Solde(double solde) {
        this.solde = solde;
    }

    public Solde() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
}
