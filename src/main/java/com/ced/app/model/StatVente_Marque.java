package com.ced.app.model;

public class StatVente_Marque {
    private Marque marque;
    private double stat;
    private int annee;
    
    public StatVente_Marque(Marque marque, double stat) {
        this.marque = marque;
        this.stat = stat;
    }
    public StatVente_Marque(Marque marque, double stat, int annee) {
        this.marque = marque;
        this.stat = stat;
        this.annee = annee;
    }
    public StatVente_Marque() {
    }
    public Marque getMarque() {
        return marque;
    }
    public void setMarque(Marque marque) {
        this.marque = marque;
    }
    public double getStat() {
        return stat;
    }
    public void setStat(double stat) {
        this.stat = stat;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    
}
