package services;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import entities.club;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.MyDB;
/**
 *
 * @author oumaymacherif
 */
public class ServiceClub {
    
Connection cnx = MyDB.getInstance().getConnection();

  
    public void ajouter(club e) {
        try {
            String req = "INSERT INTO club (president, nomc, descr, logo ) VALUES ('" + e.getPresident()+ "','" + e.getNomc() + "','" + e.getDescr() + "','" + e.getLogo() +   "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Club ajoutée !");
            // JavaMailUtil.sendMail("adem.wertani@esprit.tn");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    public void supprimer(club e) {
        try {
            String req = "DELETE  FROM club where id=" + e.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("club supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 
    public void modifier(club e) {
        try {
            String req = "UPDATE club SET president='"+e.getPresident()+"',nomc='" + e.getNomc() + "',descr='" + e.getDescr()  + "',logo='" + e.getLogo() +  "' WHERE id =" + e.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("club modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
 public ObservableList<club> afficher(){
   
           

          ObservableList<club> tournois = FXCollections.observableArrayList();
        try {
            String req = "select * from club";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                club p = new club();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nomc"));
              p.setLogo(rs.getString("logo"));
             p.setDescr(rs.getString("descr"));
            p.setPresident(rs.getString("president"));
                   FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(club.url_upload + rs.getString("logo"));
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

    public void Trier() {
        ServiceClub se = new ServiceClub();
        List<club> Trier = se.afficher().stream().sorted(Comparator.comparing(club::getNomc)).collect(Collectors.toList());
        Trier.forEach(System.out::println);
    }

    public void rechercher(String nomc) {
        List<club> result = afficher().stream().filter(line -> nomc.equals(line.getNomc())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }

   
    public List<club> recherche(club t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

