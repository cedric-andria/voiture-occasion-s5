package com.ced.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    double commission;

    public Commission(int id, double commission) {
        this.id = id;
        this.commission = commission;
    }

    public Commission(double commission) {
        this.commission = commission;
    }

    public Commission() {
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public double getPourcentage() {
        return commission;
    }

    public double getCommission(){
        return commission / 100;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
