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
public class Interaction {
    int idi;
    String type;
    String descrp;
    User user;
    Article article;

    @Override
    public String toString() {
        return "Interaction{" + "idi=" + idi + ", type=" + type + ", descrp=" + descrp + ", user=" + user + ", article=" + article + '}';
    }

    public void setIdi(int idi) {
        this.idi = idi;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescrp(String descrp) {
        this.descrp = descrp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getIdi() {
        return idi;
    }

    public String getType() {
        return type;
    }

    public String getDescrp() {
        return descrp;
    }

    public User getUser() {
        return user;
    }

    public Article getArticle() {
        return article;
    }

    public Interaction(String type, String descrp, User user, Article article) {
        this.type = type;
        this.descrp = descrp;
        this.user = user;
        this.article = article;
    }

    public Interaction(int idi, String type, String descrp, User user, Article article) {
        this.idi = idi;
        this.type = type;
        this.descrp = descrp;
        this.user = user;
        this.article = article;
    }
}
