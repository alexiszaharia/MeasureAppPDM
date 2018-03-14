package com.example.stud.measureapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by stud on 2/28/2018.
 */

public class Masuratoare implements Serializable{
    private String denumire = "";
    private ArrayList<Punct> listaPuncte = new ArrayList<>();
    private Date data = null;
    private int id;

    public Masuratoare(String denumire, ArrayList<Punct> puncte, Date data, int id){
        this.denumire = denumire;
        this.listaPuncte = puncte;
        this.data = data;
        this.id = id;
    }

    public Masuratoare() {

    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public ArrayList<Punct> getListaPuncte() {
        return listaPuncte;
    }

    public void setListaPuncte(ArrayList<Punct> listaPuncte) {
        this.listaPuncte = listaPuncte;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSuprafata() {

        if(listaPuncte.size() <= 2) {
            return 0.0f;
        } else {
            float area = 0.0f;
            for(int i = 0; i < listaPuncte.size() - 1; i++) {
                area += listaPuncte.get(i).getLatitudine()*listaPuncte.get(i+1).getLongitudine() - listaPuncte.get(i).getLongitudine()*listaPuncte.get(i+1).getLatitudine();
            }
            area /= 2;
            return area;
        }

    }
}
