/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Joueur;
import entities.club;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import util.MyDB;

/**
 *
 * @author oumaymacherif
 */
public class ServiceJoueur  {
    
Connection cnx = MyDB.getInstance().getConnection();

    public void ajouter(Joueur e) {
        try {
            String req = "INSERT INTO joueur (cin, age, nbm, nba , nom ,prenom) VALUES ('" + e.getCin() + "','" + e.getAge() + "','" + e.getNbm() + "','" + e.getNba() + "','" + e.getNom() + "','" + e.getPrenom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Joueur ajoutée !");
            // JavaMailUtil.sendMail("adem.wertani@esprit.tn");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ServiceJoueur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void supprimer(Joueur e) {
        try {
            String req = "DELETE  FROM joueur where cin=" + e.getCin();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Joueur supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void modifier(Joueur e) {
        try {
            String req = "UPDATE joueur SET cin='"+e.getCin()+"',age='" + e.getAge() + "',nbm='" + e.getNbm()  + "',nba='" + e.getNba() + "',nom='" + e.getNom() + "',prenom='" + e.getPrenom() + "' WHERE cin_joueur =" + e.getCin();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Joueur modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
 
 public ObservableList<Joueur> afficher(){
   
          ObservableList<Joueur> tournois = FXCollections.observableArrayList();
        try {
            String req = "select * from joueur";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Joueur p = new Joueur();
                p.setCin(rs.getInt("cin"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
            p.setAge(rs.getString("age"));
              p.setLogo(rs.getString("photo"));
                // p.setLogo(rs.getString("photo"));
                   FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Joueur.url_upload + rs.getString("photo"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setImg(image);

                } catch (FileNotFoundException ex) {
                      System.out.println(ex.getMessage());

                }
                tournois.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return tournois;
    }
 public ObservableList<Joueur> rechercheReward(int id) throws FileNotFoundException{
  
          ObservableList<Joueur> rewards =FXCollections.observableArrayList();
         
        try {
            String req = "select * from joueur where club_id= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                  club t = new club(rs.getInt("club_id"));
                Joueur p = new Joueur(t.getId());
               FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Joueur.url_upload + rs.getString("photo"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setImg(image);
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("prenom"));
               rewards.add(p);
            }   catch (SQLException ex) {
                    Logger.getLogger(ServiceRewards.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rewards;
    }
    public void Trier() {
        ServiceJoueur se = new ServiceJoueur();
        List<Joueur> Trier = se.afficher().stream().sorted(Comparator.comparing(Joueur::getNom)).collect(Collectors.toList());
        Trier.forEach(System.out::println);
    }

    public void rechercher(String nom_ev) {
        List<Joueur> result = afficher().stream().filter(line -> nom_ev.equals(line.getNom())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }

 
    public List<Joueur> recherche(Joueur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

