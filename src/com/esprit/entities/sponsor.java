package com.esprit.entities;

import java.util.Objects;

/**
 *
 * @author oumayma
 */
public class sponsor {

    private int id;
    public String nomS;
    private String logoS;

    public sponsor() {
    }

    public sponsor(int id) {
        this.id = id;
    }

    public sponsor(int id, String nomS, String logoS) {
        this.id = id;
        this.nomS = nomS;
        this.logoS = logoS;
    }

    public sponsor(String nomS, String logoS) {
        this.nomS = nomS;
        this.logoS = logoS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnomS() {
        return nomS;
    }

    public void setnoms(String nomS) {
        this.nomS = nomS;
    }

    public String getlogoS() {
        return logoS;
    }

    public void setlogoS(String logoS) {
        this.logoS = logoS;
    }

    @Override
    public String toString() {
        return "serviceclub {" + "id=" + id + ", nomS=" + nomS + ", logoS=" + logoS + '}';
    }

}
