package com.example.stud.measureapp;

import java.io.Serializable;

/**
 * Created by stud on 2/28/2018.
 */

public class Punct implements Serializable{
    private double latitudine;
    private double longitudine;

    public Punct(double latitudine, double longitudine) {
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    @Override
    public String toString() {
        return "Punct{" +
                "latitudine=" + latitudine +
                ", longitudine=" + longitudine +
                '}';
    }
}
