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
public class Rewards {
     private int id;
      private int rank;
      private String trophe;
       private float prixR;
        private  String img;
        private Tournoi t;
        private Image logo;
 public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Rewards(int id, String img) {
        this.id = id;
        this.img = img;
    }

    public Rewards(int id) {
        this.id = id;
    }

    public Rewards(int id, Tournoi t) {
        this.id = id;
        this.t = t;
    }

    public Rewards(Tournoi t) {
        this.t = t;
    }

    public Rewards(String trophe) {
        this.trophe = trophe;
    }

    public Rewards() {
    }

    

    public Rewards(int rank, String trophe, float prixR, String img, Tournoi t) {
        this.rank = rank;
        this.trophe = trophe;
        this.prixR = prixR;
        this.img = img;
        this.t = t;
    }

    public Rewards( int rank, String trophe, float prixR, String img) {
   
        this.rank = rank;
        this.trophe = trophe;
        this.prixR = prixR;
        this.img = img;
    }
 public Rewards(Rewards t) {
           this.id = t.id;
        this.rank = t.rank;
        this.trophe = t.trophe;
        this.prixR = t.prixR;
        this.img = t.img;
        this.t = t.t;
    }

    public Rewards(int id, String trophe, Tournoi t) {
        this.id = id;
        this.trophe = trophe;
        this.t = t;
    }

    public Rewards(String trophe, Tournoi t) {
        this.trophe = trophe;
        this.t = t;
    }
    public Rewards(int id, int rank, String trophe, float prixR, String img, Tournoi t) {
        this.id = id;
        this.rank = rank;
        this.trophe = trophe;
        this.prixR = prixR;
        this.img = img;
        this.t = t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getTrophe() {
        return trophe;
    }

    public void setTrophe(String trophe) {
        this.trophe = trophe;
    }

    public float getPrixR() {
        return prixR;
    }

    public void setPrixR(float prixR) {
        this.prixR = prixR;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Tournoi getT() {
        return t;
    }

    public void setT(Tournoi t) {
        this.t = t;
    }
       
        public  String toString()
    {
        return  this.t.getNomt()+" " +this.trophe +" "+this.rank+" "+this.prixR+" "+this.img;
    } 
    
}
