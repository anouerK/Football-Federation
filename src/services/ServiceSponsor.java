/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Sponsor;

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
import util.MyDB;

/**
 *
 * @author oumaymacherif
 */
public class ServiceSponsor  {
    
Connection cnx = MyDB.getInstance().getConnection();

 
    public void ajouter(Sponsor e) {
        try {
           
            String req = "INSERT INTO sponsor (id, nom_s, logo_s) VALUES ('" + e.getId() + "','" + e.getNom_s() + "','" + e.getLogo_s() +  "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Sponsor ajoutée !");
            // JavaMailUtil.sendMail("adem.wertani@esprit.tn");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(ServiceSponsor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public void supprimer(Sponsor e) {
        try {
            String req = "DELETE  FROM sponsor where id sponsor=" + e.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Sponsor supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    public void modifier(Sponsor e) {
        try {
            String req = "UPDATE sponsor SET nom_s='" + e.getNom_s() + "',logo_s='" + e.getLogo_s() + "' WHERE id sponsor =" + e.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Joueur modifé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    public List<Sponsor> afficher() {
        List<Sponsor> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM sponsor";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Sponsor(rs.getInt(1),rs.getString("nom_s"), rs.getString("logo_s")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void Trier() {
        ServiceSponsor se = new ServiceSponsor();
        List<Sponsor> Trier = se.afficher().stream().sorted(Comparator.comparing(Sponsor::getNom_s)).collect(Collectors.toList());
        Trier.forEach(System.out::println);
    }

    public void rechercher(String nom_ev) {
        List<Sponsor> result = afficher().stream().filter(line -> nom_ev.equals(line.getNom_s())).collect(Collectors.toList());
        System.out.println("----------");
        result.forEach(System.out::println);

    }


    public List<Sponsor> recherche(Sponsor t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

