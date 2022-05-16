/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import javafx.scene.image.Image;

/**
 *
 * @author oumayma
 */
public class club {
    private int id; 
    private String nomc; 
    private String descr; 
    private String  president ; 
    private String logo;
      private Image img;
 public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public String getPresident() {
        return president;
    }

    public club(int id, String nomc, String descr, String president, Image img) {
        this.id = id;
        this.nomc = nomc;
        this.descr = descr;
        this.president = president;
        this.img = img;
    }

    public void setPresident(String president) {
        this.president = president;
    }

    public String getLogo() {
        return logo;
    }

    public club(int id, String nomc, String logo) {
        this.id = id;
        this.nomc = nomc;
        this.logo = logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public club(String nomc, String descr, String president, String logo) {
        this.nomc = nomc;
        this.descr = descr;
        this.president = president;
        this.logo = logo;
    }

    public club(club c) {
        this.id = c.id;
        this.nomc = c.nomc;
        this.descr = c.descr;
        this.president = c.president;
        this.logo = c.logo;
    }

    public club() {
    }

    public club(String nomc) {
        this.nomc = nomc;
    }

    
    
    public club(int id) {
        this.id = id;
    }

    public club(int id, String nomc) {
        this.id = id;
        this.nomc = nomc;
    }

    public club(int id, String nomc, String descr, String president ) {
        this.id = id;
        this.nomc = nomc;
        this.descr = descr;
        this.president = president;
    }

    public int getId() {
        return id;
    }

    public club(String nomc, String logo) {
        this.nomc = nomc;
        this.logo = logo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomc() {
        return nomc;
    }

    public void setNom(String nomc) {
        this.nomc = nomc;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

   

   

    @Override
    public String toString() {
        return " {" + "id=" + id + ", nomc=" + nomc + ", descr=" + descr + ", president=" + president  + ", logo=" + logo+'}';
    }

   

    
}
