/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Arbitre;
import gui.Afficher_arbitreController;
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
public class ArbitreService implements IService<Arbitre> {

    Connection cnx;

    public ArbitreService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Arbitre t) {
        String req = "insert into arbitre(nom_a,nbe,image,descrp)" + "values('" + t.getNoma() + "'," + t.getNbe() + ",'" + t.getImage() + "', '" + t.getDescp() + "')";
        Statement st;
        try {
            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("ajout Arbitre avec succee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 //   @Override
    public List<Arbitre> afficher() {
        List<Arbitre> arbitres = new ArrayList<>();
        try {
            String rep = "select * from arbitre ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rep);

            while (rs.next()) {
                Arbitre P = new Arbitre();
                P.setId(rs.getInt(1));
                P.setNoma(rs.getString("nom_a"));
                P.setNbe(rs.getInt("nbe"));
                P.setImage(rs.getString("image"));
                P.setDescp(rs.getString("descrp"));

                arbitres.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arbitres;
    }

    @Override
    public void supprimer(int id) {
        try {
            PreparedStatement ps = cnx.prepareStatement("delete from arbitre where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Arbitre t) {
        String req = "update arbitre set nom_a= ?, nbe= ?, image= ?, descrp= ? where id= ? ";
        try {
            PreparedStatement sp = cnx.prepareStatement(req);
            sp.setString(1, t.getNoma());
            sp.setInt(2, t.getNbe());
            sp.setString(3, t.getImage());
             sp.setString(4, t.getDescp());
            sp.setInt(5, t.getId());
            sp.executeUpdate();
            System.out.println("Arbitre Modifier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
     public ObservableList<Arbitre> afficherr() throws FileNotFoundException {
        ObservableList<Arbitre> arbitres =  FXCollections.observableArrayList();
        try {
            String rep = "select * from arbitre ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(rep);

            while (rs.next()) {
                Arbitre P = new Arbitre();
                P.setId(rs.getInt(1));
                P.setNoma(rs.getString("nom_a"));
                P.setNbe(rs.getInt("nbe"));
             //  P.setImage(rs.getString(4));
                P.setDescp(rs.getString("descrp"));

                //image 
                FileInputStream inputStream;
        try  {
            inputStream = new FileInputStream(Arbitre.url_upload+rs.getString("image"));
             Image image= new Image(inputStream);
                   P.setImage2(image);
                       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_arbitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                arbitres.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return arbitres;
    }
    
    
    
    
    
    
}
