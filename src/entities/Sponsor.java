
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author oumaymacherif
 */
public class Sponsor {
     private int id ;
    private String nom_s , logo_s;

    public Sponsor(int id, String nom_s, String logo_s) {
        this.id = id;
        this.nom_s = nom_s;
        this.logo_s = logo_s;
    }

    public Sponsor(String nom_s, String logo_s) {
        this.nom_s = nom_s;
        this.logo_s = logo_s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_s() {
        return nom_s;
    }

    public void setNom_s(String nom_s) {
        this.nom_s = nom_s;
    }

    public String getLogo_s() {
        return logo_s;
    }

    public void setLogo_s(String logo_s) {
        this.logo_s = logo_s;
    }
    
    
    
}
