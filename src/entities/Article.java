/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;

/**
 *
 * @author ksaay
 */
public class Article {
    public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\img\\";
    int id;
    String titre;
    String descr;
    String datea;
    String img;
    User user;
    private Image img_a ; 
    private ComboBox users;

    public ComboBox getUsers() {
        return users;
    }

    public void setUsers(ComboBox users) {
        this.users = users;
    }

    public Image getImg_a() {
        return img_a;
    }

    public void setImg_a(Image img_a) {
        this.img_a = img_a;
    }

    public Article() {
       this.users = new ComboBox();
    }

    public Article(Article article) {
       this.id = article.id;
        this.titre = article.titre;
        this.descr = article.descr;
        this.datea = article.datea;
        this.img = article.img;
        this.img_a = article.img_a;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    @Override
    public String toString() {
        return "\n Article{" + "id=" + id + ", titre=" + titre + ", descr=" + descr + ", datea=" + datea + ", img=" + img + ", user=" + user + '}';
    }

    public Article(String titre, String descr, String img, String datea, User user) {
        this.titre = titre;
        this.descr = descr;
        this.datea = datea;
        this.img = img;
        this.user = user;
        this.users = new ComboBox();
    }

    public Article(String titre, String descr, String img, User user) {
        this.titre = titre;
        this.descr = descr;
        this.img = img;
        this.user = user;
          this.users = new ComboBox();
    }

    public Article(int id, String titre, String descr, String datea, String img, User user) {
        this.id = id;
        this.titre = titre;
        this.descr = descr;
        this.datea = datea;
        this.img = img;
        this.user = user;
          this.users = new ComboBox();
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
