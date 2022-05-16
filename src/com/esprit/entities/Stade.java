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
public class Stade {
    
    
    private int id;
    private String noms;
    private int nbrP;
    private String lieu;
    private String etat;
    private String photo;

    public Stade() {
    }

    public Stade(int id) {
        this.id = id;
    }

    public Stade(int id, String noms) {
        this.id = id;
        this.noms = noms;
    }

    

    public Stade(String noms, int nbrP, String lieu, String etat, String photo) {
        this.noms = noms;
        this.nbrP = nbrP;
        this.lieu = lieu;
        this.etat = etat;
        this.photo = photo;
    }
public Stade(Stade s) {
        this.id = s.id;
        this.noms = s.noms;
        this.nbrP = s.nbrP;
        this.lieu = s.lieu;
        this.etat = s.etat;
        this.photo = s.photo;
    }

    public Stade(int id, String noms, int nbrP, String lieu, String etat, String photo) {
        this.id = id;
        this.noms = noms;
        this.nbrP = nbrP;
        this.lieu = lieu;
        this.etat = etat;
        this.photo = photo;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public int getNbrP() {
        return nbrP;
    }

    public void setNbrP(int nbrP) {
        this.nbrP = nbrP;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
    
    
}
