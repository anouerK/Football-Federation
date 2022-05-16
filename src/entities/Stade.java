/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class Stade {
    
    private int id,nbrp;
    private String noms,lieu,etat,photo;
    private Image image2;
    public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    public Image getImage2() {
        return image2;
    }

    public Stade(String noms, String lieu, String photo) {
        this.noms = noms;
        this.lieu = lieu;
        this.photo = photo;
    }

    public Stade(String noms, String photo) {
        this.noms = noms;
        this.photo = photo;
    }

    public Stade(String noms) {
        this.noms = noms;
    }
public Stade(int noms) {
        this.id = noms;
    }
    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Stade() {
    }

    public Stade(int id, int nbrp, String noms, String lieu, String etat, String photo) {
        this.id = id;
        this.nbrp = nbrp;
        this.noms = noms;
        this.lieu = lieu;
        this.etat = etat;
        this.photo = photo;
    }

    public Stade(int nbrp, String noms, String lieu, String etat, String photo) {
        this.nbrp = nbrp;
        this.noms = noms;
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

    public int getNbrp() {
        return nbrp;
    }

    public void setNbrp(int nbrp) {
        this.nbrp = nbrp;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
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

    @Override
    public String toString() {
        return "Stade{" + "id=" + id + ", nbrp=" + nbrp + ", noms=" + noms + ", lieu=" + lieu + ", etat=" + etat + ", photo=" + photo + '}';
    }
    
    
}
