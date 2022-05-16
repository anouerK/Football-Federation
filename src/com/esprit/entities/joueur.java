/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

import java.util.Objects;

/**
 *
 * @author oumayma
 */
public class joueur {

    private int cin;
    private int age;
    private int nbm;
    private int nba;
    private int numT;
    private String nom;
    private String prenom;
    private String poste;
    private String nationalite;
    private String photo;

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public int getNumT() {
        return numT;
    }

    public void setNumT(int numT) {
        this.numT = numT;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public joueur() {
    }

    public joueur(String nom, String poste) {
        this.nom = nom;
        this.poste = poste;
    }

    public joueur(int cin) {
        this.cin = cin;
    }

    public joueur(int cin, int age, int nbm, int nba, int numT, String nom, String prenom, String poste, String nationalite, String photo) {
        this.cin = cin;
        this.age = age;
        this.nbm = nbm;
        this.nba = nba;
        this.numT = numT;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.photo = photo;
    }

    public joueur(int age, int nbm, int nba, String nom, String prenom, String poste, String nationalite, String photo) {
        this.age = age;
        this.nbm = nbm;
        this.nba = nba;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.photo = photo;
    }

   

   

    public joueur(int age, int nbm, int nba, int numT, String nom, String prenom, String poste, String nationalite, String photo) {
        this.age = age;
        this.nbm = nbm;
        this.nba = nba;
        this.numT = numT;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.nationalite = nationalite;
        this.photo = photo;
    }

    public int getcin() {
        return cin;
    }

    public void setcin(int cin) {
        this.cin = cin;
    }

    public int getage() {
        return age;
    }

    public void setage(int age) {
        this.age = age;
    }

    public int getnbm() {
        return nbm;
    }

    public void setnbm(int nbm) {
        this.nbm = nbm;
    }

    public int getnba() {
        return nba;
    }

    public void setnba(int nba) {
        this.nba = nba;
    }

    public int getnumT() {
        return numT;
    }

    public void setnumT(int numT) {
        this.numT = numT;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getprenom() {
        return prenom;
    }

    public void setprenom(String prenom) {
        this.prenom = prenom;
    }

    public String getposte() {
        return poste;
    }

    public void setposte(String poste) {
        this.poste = poste;
    }
 public void setphoto(String photo) {
        this.photo = photo;
    }
    public String getphoto() {
        return photo;
    }

    public void setnationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getnationalite() {
        return nationalite;
    }

    @Override
    public String toString() {
        return "servicejoueur {" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", poste=" + poste + "age=" + age + ", nbm=" + nbm + ", nba=" + nba + ", nationalite=" + nationalite + ", numT=" + numT + ", photo=" + photo + '}';
    }

}
