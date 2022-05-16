/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Arbitre;
import entities.Stade;
import gui.Afficher_arbitreController;
import gui.Afficher_stadeController;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import services.IService;
import util.MyDB;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class StadeService implements IService<Stade> {

    Connection cnx;

    public StadeService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Stade t) {
        String req = "insert into stade(lieu,noms,etat,nbr_p,photo)" + "values('" + t.getLieu() + "','" + t.getNoms() + "','" + t.getEtat() + "', " + t.getNbrp() + ", '" + t.getPhoto() + "')";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ajout Stade avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@Override
    public List<Stade> afficher() {
        List<Stade> stades = new ArrayList<>();
        try {
            String rep = "select * from stade ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rep);

            while (rs.next()) {
                Stade P = new Stade();
                P.setId(rs.getInt(1));
                P.setLieu(rs.getString("lieu"));
                P.setNoms(rs.getString("noms"));
                P.setEtat(rs.getString("etat"));
                P.setNbrp(rs.getInt("nbr_p"));
                P.setPhoto(rs.getString("photo"));

                stades.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stades;
    }

    @Override
    public void supprimer(int id) {
        try {
            PreparedStatement ps = cnx.prepareStatement("delete from stade where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Stade t) {
        String req = "update stade set lieu= ?, noms= ?, etat= ?, nbr_p= ?, photo= ? where id= ? ";
        try {
            PreparedStatement sp = cnx.prepareStatement(req);
            sp.setString(1, t.getLieu());
            sp.setString(2, t.getNoms());
            sp.setString(3, t.getEtat());
            sp.setInt(4, t.getNbrp());
            sp.setString(5, t.getPhoto());
            sp.setInt(6, t.getId());
            sp.executeUpdate();
            System.out.println("Stade Modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
     public ObservableList<Stade> afficherr() throws FileNotFoundException {
        ObservableList<Stade> stades =  FXCollections.observableArrayList();
        
        try {
            String rep = "select * from stade ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rep);

            while (rs.next()) {
                Stade P = new Stade();
                P.setId(rs.getInt(1));
                P.setLieu(rs.getString("lieu"));
                P.setNoms(rs.getString("noms"));
                P.setEtat(rs.getString("etat"));
                P.setNbrp(rs.getInt("nbr_p"));
                P.setPhoto(rs.getString("photo"));
                
                //image 
                FileInputStream inputStream;
        try  {
            inputStream = new FileInputStream(Stade.url_upload+rs.getString("photo"));
             Image image= new Image(inputStream);
                   P.setImage2(image);
                       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_stadeController.class.getName()).log(Level.SEVERE, null, ex);
        } 

                stades.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return stades;
        
     }

}
