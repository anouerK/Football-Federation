/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;

/**
 *
 * @author Lord
 */
public class panier {
    
    int id;
    String taille ;
    int  qte;
    float prix;
    produit prod;
    
    

    public panier() {
    }

    public panier(String taille, int qte, float prix, produit prod) {
        this.taille = taille;
        this.qte = qte;
        this.prix = prix;
        this.prod = prod;
    }

    public panier(int id, String taille, int qte, float prix, produit prod) {
        this.id = id;
        this.taille = taille;
        this.qte = qte;
        this.prix = prix;
        this.prod = prod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public produit getProd() {
        return prod;
    }

    public void setProd(produit prod) {
        this.prod = prod;
    }

    @Override
    public String toString() {
        return "panier{" + "taille=" + taille + ", qte=" + qte + ", prix=" + prix + ", prod=" + prod + '}';
    }

   
    
    
    
}
