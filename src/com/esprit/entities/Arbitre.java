/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class Arbitre {

    private int id;
    private String nomA;
    private int nbe;
    private String descrp;
    private String image;

    public Arbitre() {
    }
    
    public Arbitre(int id) {
        this.id = id;
    }

    public Arbitre(int id, String nomA, int nbe, String descrp, String image) {
        this.id = id;
        this.nomA = nomA;
        this.nbe = nbe;
        this.descrp = descrp;
        this.image = image;
    }

    public Arbitre(int id, String nomA) {
        this.id = id;
        this.nomA = nomA;
    }

    public Arbitre(String nomA, int nbe, String descrp, String image) {
        this.nomA = nomA;
        this.nbe = nbe;
        this.descrp = descrp;
        this.image = image;
    }

    public Arbitre(Arbitre a) {
        this.id = a.id;
        this.nomA = a.nomA;
        this.nbe = a.nbe;
        this.descrp = a.descrp;
        this.image = a.image;
    }

 
    public int getId() {
        return id;
    }

    public String getNomA() {
        return nomA;
    }

    public int getNbe() {
        return nbe;
    }

    public String getDescrp() {
        return descrp;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public void setNbe(int nbe) {
        this.nbe = nbe;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

}
