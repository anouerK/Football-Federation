/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lord
 */
public class produit {
    private int id;
    private String nom;
    private String taille;
    private String taille2;
    private String desc;
    private String img;
    private String couleur;
    private String date;
    private float prix;
    private int qte;
    private categories cat;
    private marques mar;
  

    public produit(String nom) {
        this.nom = nom;
    }

    
    
    public produit() {
    }

    public produit(int id, String nom, String taille, String taille2, String desc, String img, String couleur, float prix, int qte, categories cat, marques mar) {
        this.id = id;
        this.nom = nom;
        this.taille = taille;
        this.taille2 = taille2;
        this.desc = desc;
        this.img = img;
        this.couleur = couleur;
        this.prix = prix;
        this.qte = qte;
        this.cat = cat;
        this.mar = mar;
    }

    public produit(int id, String nom, String taille, String taille2, String desc, String img, String couleur, String date, float prix, int qte, categories cat, marques mar) {
        this.nom = nom;
        this.id=id;
        this.taille = taille;
        this.taille2 = taille2;
        this.desc = desc;
        this.img = img;
        this.couleur = couleur;
        this.date = date;
        this.prix = prix;
        this.qte = qte;
        this.cat = cat;
        this.mar = mar;
    }
    
    

    public produit(String nom, String taille, String taille2, String desc, String img, String couleur, float prix, int qte, categories cat, marques mar) {
        this.nom = nom;
        this.taille = taille;
        this.taille2 = taille2;
        this.desc = desc;
        this.img = img;
        this.couleur = couleur;
        this.prix = prix;
        this.qte = qte;
        this.cat = cat;
        this.mar = mar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public String getTaille2() {
        return taille2;
    }

    public void setTaille2(String taille2) {
        this.taille2 = taille2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public categories getCat() {
        return cat;
    }

    public void setCat(categories cat) {
        this.cat = cat;
    }

    public marques getMar() {
        return mar;
    }

    public void setMar(marques mar) {
        this.mar = mar;
    }

    @Override
    public String toString() {
        return "produit{" + "nom=" + nom + ", taille=" + taille + ", taille2=" + taille2 + ", desc=" + desc + ", img=" + img + ", couleur=" + couleur + ", date=" + date + ", prix=" + prix + ", qte=" + qte + ", cat=" + cat + ", mar=" + mar + '}';
    }

   
    
    
      
   
   
            
    
    
}
