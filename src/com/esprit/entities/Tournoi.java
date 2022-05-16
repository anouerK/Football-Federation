/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;



/**
 *
 * @author oumayma
 */
public class Tournoi {
    private int id;
    private String nomt;
    private String dated ;
    private String datef ;
     private String typet;
      private int nbrc;
      private String logo;
private Rewards r;

    public Tournoi(Tournoi t) {
        this.id = t.id;
        this.nomt = t.nomt;
        this.dated = t.dated;
        this.datef = t.datef;
        this.typet = t.typet;
        this.nbrc = t.nbrc;
        this.logo = t.logo;
        this.r = t.r;
    }

    public Tournoi(int id,Rewards r) {
          this.id = id;
        this.r = r;
    }

    public Rewards getR() {
        return r;
    }

    public void setR(Rewards r) {
        this.r = r;
    }
    public Tournoi() {
    }

    public Tournoi(int id) {
        this.id = id;
    }

    public Tournoi(int id, String nomt) {
        this.id = id;
        this.nomt = nomt;
    }

    public Tournoi(int id, String nomt, String dated, String datef, String typet, int nbrc, String logo) {
        this.id = id;
        this.nomt = nomt;
        this.dated = dated;
        this.datef = datef;
        this.typet = typet;
        this.nbrc = nbrc;
        this.logo = logo;
    }

    public Tournoi(String nomt, String dated,String datef, String typet, int nbrc, String logo) {
        this.nomt = nomt;
        this.dated = dated;
        this.datef = datef;
        this.typet = typet;
        this.nbrc = nbrc;
         this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomt() {
        return nomt;
    }

    public void setNomt(String nomt) {
        this.nomt = nomt;
    }

    public  String getDated() {
        return dated;
    }

    public void setDated( String dated) {
        this.dated = dated;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef( String datef) {
        this.datef = datef;
    }

    public String getTypet() {
        return typet;
    }

    public void setTypet(String typet) {
        this.typet = typet;
    }

    public int getNbrc() {
        return nbrc;
    }

    public void setNbrc(int nbrc) {
        this.nbrc = nbrc;
    }

 
     public  String toString()
    {
        return  this.nomt;
    }
    
}
    