/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author Lord
 */
public class categories {
      private int id;
      private String typec;

    public categories() {
    }

    public categories(String typec) {
        this.typec = typec;
    }

    public categories(int id, String typec) {
        this.id = id;
        this.typec = typec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypec() {
        return typec;
    }

    public void setTypec(String typec) {
        this.typec = typec;
    }

    @Override
    public String toString() {
        return "categories{" + "typec=" + typec + '}';
    }
      
}
