/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author oumayma
 */
public class classement {
      private int id;
    private club club;
    private Tournoi tournoi ;
 private int pts;
  private int rank;
 public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    public classement(int id, club club, Tournoi tournoi, int pts) {
        this.id = id;
        this.club = club;
        this.tournoi = tournoi;
        this.pts = pts;
    }

    public classement(int id, int pts) {
        this.id = id;
        this.pts = pts;
    }

    public classement(int pts) {
        this.pts = pts;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
    public classement() {
        int pts = 0;
        int rank = 0;
    }

    public classement(int id, club club, Tournoi tournoi) {
        this.id = id;
        this.club = club;
        this.tournoi = tournoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public club getClub() {
        return club;
    }

    public void setClub(club club) {
        this.club = club;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }
      public  String toString()
    {
        return  this.club.getLogo()+" " +this.club.getNomc()+" "+this.pts;
    } 
}
