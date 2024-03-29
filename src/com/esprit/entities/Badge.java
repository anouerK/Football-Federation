/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author ksaay
 */
public class Badge {
    private int id,nb;

    public Badge(int id, int nb, String nomB, String logoB) {
        this.id = id;
        this.nb = nb;
        this.nomB = nomB;
        this.logoB = logoB;
    }
    private String nomB,logoB;

    public Badge() {
      
    }
    @Override
    public String toString() {
        //return "Badge{" + "nb=" + nb + ", nomB=" + nomB + ", logoB=" + logoB + '}';
         return "Badge{" + "nomB=" + nomB + ", nb=" + nb + ", logoB=" + logoB + '}';
    }

   public Badge(Badge x)
   {
      
      

          this.id = x.id;
       this.logoB = x.logoB;
       this.nb = x.nb;
       this.nomB = x.nomB;
      
     
       
   }

    public Badge(int id,String nomB, String logoB,int nb) {
        this.id = id;
        this.nomB = nomB;
        this.nb = nb;
        this.logoB = logoB;
    }
    public Badge(String nomB, String logoB,int nb) {
       
        this.nomB = nomB;
        this.nb = nb;
        this.logoB = logoB;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public String getNomB() {
        return nomB;
    }

    public void setNomB(String nomB) {
        this.nomB = nomB;
    }

    public String getLogoB() {
        return logoB;
    }

    public void setLogoB(String logoB) {
        this.logoB = logoB;
    }
    
    
    
}
