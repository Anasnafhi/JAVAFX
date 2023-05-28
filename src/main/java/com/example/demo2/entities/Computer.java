package com.example.demo2.entities;

import java.util.Date;
import java.util.Objects;

public class Computer {
    private int id ;
    private String marque;
    private String name;
    private int prix;
    private generations generation;
    private typePros typeProcesor;

    private boolean ssd ;
    private Date anneFabri;

    public Computer() {

    }

    public enum typePros {
        COREI5, COREI6, COREI7, COREI8;


    };
    public enum generations {
        g5th, g6th, g7th, g8th, g9th, g10th, g11th;
    }

    public generations getGeneration() {
        return generation;
    }

    public void setGeneration(generations generation) {
        this.generation = generation;
    }

    public typePros getTypeProcesor() {
        return typeProcesor;
    }

    public void setTypeProcesor(typePros typeProcesor) {
        this.typeProcesor = typeProcesor;
    }

    public Computer(int id, String marque, String name, int prix, boolean ssd, Date anneFabri, generations generation , typePros typeProcesor) {
        this.id = id;
        this.marque = marque;
        this.name = name;
        this.prix = prix;
        this.ssd = ssd;
        this.anneFabri = anneFabri;
        this.typeProcesor = typeProcesor;
        this.generation=generation;
    }

    public Computer( String marque, String name, int prix, boolean ssd, Date anneFabri, generations generation , typePros typeProcesor) {

        this.marque = marque;
        this.name = name;
        this.prix = prix;
        this.ssd = ssd;
        this.anneFabri = anneFabri;
        this.typeProcesor = typeProcesor;
        this.generation=generation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isSsd() {
        return ssd;
    }

    public void setSsd(boolean ssd) {
        this.ssd = ssd;
    }

    public Date getAnneFabri() {
        return anneFabri;
    }

    public void setAnneFabri(Date anneFabri) {
        this.anneFabri = anneFabri;
    }


    @Override
    public String toString() {
        return "Computer{" +
                "id=" + id +
                ", marque='" + marque + '\'' +
                ", name='" + name + '\'' +
                ", prix=" + prix +
                ", generation=" + generation +
                ", typeProcesor=" + typeProcesor +
                ", ssd=" + ssd +
                ", anneFabri=" + anneFabri +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Computer computer = (Computer) o;
        return prix == computer.prix && ssd == computer.ssd && Objects.equals(id, computer.id) && Objects.equals(marque, computer.marque) && Objects.equals(name, computer.name) && generation == computer.generation && typeProcesor == computer.typeProcesor && Objects.equals(anneFabri, computer.anneFabri);
    }

}



