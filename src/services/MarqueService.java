/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.marques;
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
import util.MyDB;
       

/**
 *
 * @author Lord
 */
public class MarqueService implements IService<marques> {

    Connection cnx;

    public MarqueService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(marques t) {
try {
            String req = "insert into marques(nom_m)"
                    + "values( '" + t.getNomM()+ "' )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Marque ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
}

    @Override
    public void modifier(marques t) {
        try {
            String req = "update marques set nom_m= ? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNomM());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("Marque modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "delete from marques  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
         
          
            System.out.println("Marque deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//    @Override
    public ObservableList<marques> recuperer() {
ObservableList<marques> personnes = FXCollections.observableArrayList();        
try {
            String req = "select * from marques";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                marques p = new marques();
                p.setId(rs.getInt("id"));
                p.setNomM(rs.getString("nom_m"));
                
                personnes.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }    
    
}
