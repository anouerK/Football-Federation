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
public class commande {
   private int id ;
   private User user;
   private produit produit;
   private float prix;
   private int qtec;
   private String datec;
   private int etat ;
   private String taillec;

    public commande() {
    }

    public commande(int id, User user, produit produit, float prix, int qtec, int etat, String taillec) {
        this.id = id;
        this.user = user;
        this.produit = produit;
        this.prix = prix;
        this.qtec = qtec;
        this.etat = etat;
        this.taillec = taillec;
    }

    public commande(User user, produit produit, float prix, int qtec, int etat, String taillec) {
        this.user = user;
        this.produit = produit;
        this.prix = prix;
        this.qtec = qtec;
        this.etat = etat;
        this.taillec = taillec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public produit getProduit() {
        return produit;
    }

    public void setProduit(produit produit) {
        this.produit = produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQtec() {
        return qtec;
    }

    public void setQtec(int qtec) {
        this.qtec = qtec;
    }

    public String getDatec() {
        return datec;
    }

    public void setDatec(String datec) {
        this.datec = datec;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getTaillec() {
        return taillec;
    }

    public void setTaillec(String taillec) {
        this.taillec = taillec;
    }

    @Override
    public String toString() {
        return "commande{" + "user=" + user + ", produit=" + produit + ", prix=" + prix + ", qtec=" + qtec + ", datec=" + datec + ", etat=" + etat + ", taillec=" + taillec + '}';
    }
   
   
   
   
   
   
    
    
}
