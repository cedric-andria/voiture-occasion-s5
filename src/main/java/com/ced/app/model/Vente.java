package com.ced.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_annonce")
    private Annonce annonce;
    @ManyToOne
    @JoinColumn(name="id_utilisateur")
    private Utilisateur client;
    private double prix;
    private LocalDateTime date_vente;
    public Vente(Annonce annonce, Utilisateur client, double prix, LocalDateTime date_vente) {
        this.annonce = annonce;
        this.client = client;
        this.prix = prix;
        this.date_vente = date_vente;
    }
    public Vente(int id, Annonce annonce, Utilisateur client, double prix, LocalDateTime date_vente) {
        this.id = id;
        this.annonce = annonce;
        this.client = client;
        this.prix = prix;
        this.date_vente = date_vente;
    }
    public Vente() {
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
    public Utilisateur getClient() {
        return client;
    }
    public void setClient(Utilisateur client) {
        this.client = client;
    }
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public LocalDateTime getDate_vente() {
        return date_vente;
    }
    public void setDate_vente(LocalDateTime date_vente) {
        this.date_vente = date_vente;
    }
    
}
