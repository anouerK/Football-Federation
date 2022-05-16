/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package services;
import entities.categories;
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
public class CategorieService implements IService<categories> {

      Connection cnx;

    public CategorieService() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(categories t) {
        
        try {
            String req = "insert into categorie(type_c)"
                    + "values( '" + t.getTypec()+ "' )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Categorie ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    }

    @Override
    public void modifier(categories t) {
        
        try {
            String req = "update categorie set type_c= ? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTypec());
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            System.out.println("Categorie modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        
        try {
            String req = "delete from categorie  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
         
          
            System.out.println("Categorie deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    //@Override
    public ObservableList<categories> recuperer() {
 ObservableList<categories> personnes = FXCollections.observableArrayList();
        try {
            String req = "select * from categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                categories p = new categories();
                p.setId(rs.getInt("id"));
                p.setTypec(rs.getString("type_c"));
                
                personnes.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
    }
}
    

