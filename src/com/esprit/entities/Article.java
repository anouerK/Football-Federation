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
public class Article {
    int id;
    String titre;
    String descr;
    String datea;
    String img;
    User user;

    public Article() {
     
    }

    public Article(Article article) {
       this.id = article.id;
        this.titre = article.titre;
        this.descr = article.descr;
        this.datea = article.datea;
        this.img = article.img;
       // this.user = article.user;
    }

    

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", descr=" + descr + ", datea=" + datea + ", img=" + img + ", user=" + user + '}';
    }

    public Article(String titre, String descr, String img, User user) {
        this.titre = titre;
        this.descr = descr;
        this.img = img;
        this.user = user;
    }

    public Article(int id, String titre, String descr, String datea, String img, User user) {
        this.id = id;
        this.titre = titre;
        this.descr = descr;
        this.datea = datea;
        this.img = img;
        this.user = user;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setDatea(String datea) {
        this.datea = datea;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescr() {
        return descr;
    }

    public String getDatea() {
        return datea;
    }

    public String getImg() {
        return img;
    }

    public User getUser() {
        return user;
    }

    
    
    
}
