/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author oumaymacherif
 */
public class Joueur {
    private int cin;
    private String nom, prenom,age, nbm,nba;
private String logo ;
private Image img ;
 public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    public String getLogo() {
        return logo;
    }

    public Joueur(int cin) {
        this.cin = cin;
    }

    public Joueur() {
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Joueur(int cin, String nom, String prenom, String age, String nbm, String nba) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.nbm = nbm;
        this.nba = nba;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNbm() {
        return nbm;
    }

    public void setNbm(String nbm) {
        this.nbm = nbm;
    }

    public String getNba() {
        return nba;
    }

    public void setNba(String nba) {
        this.nba = nba;
    }

    @Override
    public String toString() {
        return "Joueur{" + "cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", age=" + age + ", logo=" + logo + ", img=" + img + '}';
    }

   
    
    
}

