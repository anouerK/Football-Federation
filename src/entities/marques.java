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
public class marques {
    
    private int id;
    private String nomM;

    public marques() {
    }
    
    

    public marques(String nomM) {
        this.nomM = nomM;
    }

    public marques(int id, String nomM) {
        this.id = id;
        this.nomM = nomM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomM() {
        return nomM;
    }

    public void setNomM(String nomM) {
        this.nomM = nomM;
    }

    @Override
    public String toString() {
        return  nomM  ;
    }

    
    
    
    
}
