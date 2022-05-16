/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class Game {
     public static String url_upload = "C:\\Users\\ksaay\\Desktop\\anouer\\Federation-de-football-master-c\\public\\uploads\\";
    private int id;
    private club club1;
    private int r1;
    private int r2;
    private club club2;
    private String deted;
    private Arbitre arbitre;
    private Stade stade;
    private Tournoi tournoi;

    public Game() {
    }

    public Game(int id) {
        this.id = id;
    }

    public Game(int id, club club1, int r1, int r2, club club2, String deted) {
        this.id = id;
        this.club1 = club1;
        this.r1 = r1;
        this.r2 = r2;
        this.club2 = club2;
        this.deted = deted;
      
    }

    @Override
    public String toString() {
        return "Game{" + "id=" + id + ", club1=" + club1 + ", r1=" + r1 + ", r2=" + r2 + ", club2=" + club2 + ", deted=" + deted + ", arbitre=" + arbitre + ", stade=" + stade + ", tournoi=" + tournoi + '}';
    }

    public Game(int id, club club1, int r1, int r2, club club2, String deted, Arbitre arbitre, Stade stade, Tournoi tournoi) {
        this.id = id;
        this.club1 = club1;
        this.r1 = r1;
        this.r2 = r2;
        this.club2 = club2;
        this.deted = deted;
        this.arbitre = arbitre;
        this.stade = stade;
        this.tournoi = tournoi;
    }

    public Game(Tournoi tournoi,club club1, int r1, int r2, club club2, Arbitre arbitre, Stade stade, String deted) {
        this.club1 = club1;
        this.r1 = r1;
        this.r2 = r2;
        this.club2 = club2;
        this.deted = deted;
        this.arbitre = arbitre;
        this.stade = stade;
        this.tournoi = tournoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public club getClub1() {
        return club1;
    }

    public void setClub1(club club1) {
        this.club1 = club1;
    }

    public int getR1() {
        return r1;
    }

    public void setR1(int r1) {
        this.r1 = r1;
    }

    public int getR2() {
        return r2;
    }

    public void setR2(int r2) {
        this.r2 = r2;
    }

    public club getClub2() {
        return club2;
    }

    public void setClub2(club club2) {
        this.club2 = club2;
    }

    public String getDeted() {
        return deted;
    }

    public void setDeted(String deted) {
        this.deted = deted;
    }

    public Arbitre getArbitre() {
        return arbitre;
    }

    public void setArbitre(Arbitre arbitre) {
        this.arbitre = arbitre;
    }

    public Stade getStade() {
        return stade;
    }

    public void setStade(Stade stade) {
        this.stade = stade;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    
    
}
