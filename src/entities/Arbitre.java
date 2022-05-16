/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class Arbitre {
     public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    private int id,nbe;
    private String noma,image,descp;
    private Image image2;

    public Image getImage2() {
        return image2;
    }

    public Arbitre(String noma, String image) {
        this.noma = noma;
        this.image = image;
    }

    public Arbitre(int id, String noma) {
        this.id = id;
        this.noma = noma;
    }

    public void setImage2(Image image2) {
        this.image2 = image2;
    }

    public Arbitre() {
    }

    public Arbitre(int id) {
        this.id = id;
    }

    public Arbitre(int id, int nbe, String noma, String image, String descp) {
        this.id = id;
        this.nbe = nbe;
        this.noma = noma;
        this.image = image;
        this.descp = descp;
    }

    public Arbitre(String noma) {
        this.noma = noma;
    }

    public Arbitre(int nbe, String noma, String image, String descp) {
        this.nbe = nbe;
        this.noma = noma;
        this.image = image;
        this.descp = descp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbe() {
        return nbe;
    }

    public void setNbe(int nbe) {
        this.nbe = nbe;
    }

    public String getNoma() {
        return noma;
    }

    public void setNoma(String noma) {
        this.noma = noma;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }

    @Override
    public String toString() {
        return "Arbitre{" + "id=" + id + ", nbe=" + nbe + ", noma=" + noma + ", image=" + image + ", descp=" + descp + "}\n";
    }
    
    
}
