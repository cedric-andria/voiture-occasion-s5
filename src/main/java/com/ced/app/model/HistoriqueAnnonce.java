package com.ced.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HistoriqueAnnonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="id_annonce")
    private Annonce annonce;
    private LocalDateTime date_operation;
    private int nouvel_etat;
    
    public HistoriqueAnnonce(Annonce annonce, LocalDateTime date_operation, int nouvel_etat) {
        this.annonce = annonce;
        this.date_operation = date_operation;
        this.nouvel_etat = nouvel_etat;
    }
    public HistoriqueAnnonce(int id, Annonce annonce, LocalDateTime date_operation, int nouvel_etat) {
        this.id = id;
        this.annonce = annonce;
        this.date_operation = date_operation;
        this.nouvel_etat = nouvel_etat;
    }
    public HistoriqueAnnonce() {
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
    public LocalDateTime getDate_operation() {
        return date_operation;
    }
    public void setDate_operation(LocalDateTime date_operation) {
        this.date_operation = date_operation;
    }
    public int getNouvel_etat() {
        return nouvel_etat;
    }
    public void setNouvel_etat(int nouvel_etat) {
        this.nouvel_etat = nouvel_etat;
    }

}
