/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.commande;
import entities.produit;
import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;

/**
 *
 * @author Lord
 */
public class CommandeService  implements IService<commande> {

    Connection cnx;
      Connection cnx2;
    

    public CommandeService() {
        cnx = MyDB.getInstance().getConnection();
         cnx2 = MyDB.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(commande t) {
        
        try {
         
            
         // Calendar date = Calendar.g().;
           // System.out.println(date);
            DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();
            System.out.println(date.format(now));
        
       String req = "insert into commande(prix_u,qte,date,id_p_id,id_u_id,etat,taille)"
       + "values( '" + t.getPrix()+ "' ,'" + t.getQtec()+ "', '" + date.format(now) + "' ,'" + t.getProduit().getId()+ "', '" + t.getUser().getId() + "','" + t.getEtat()+ "','" + t.getTaillec()+ "' )";
           
            //String req = "insert into commande(prix_u,qte,date,id_p_id,id_u_id,etat,taille)"
           // + "values( '" + t.getPrix()+ "' ,'" + t.getQtec()+ "', '" + date.format(now) + "' ,'" + 21 + "', '" + 4 + "','" + t.getEtat()+ "','" + t.getTaillec()+ "' )";
            
            
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Commande ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    }

    @Override
    public void modifier(commande t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //@Override
    public List<commande> recuperer() {
List<commande> personnes = new ArrayList<>();

        try {
            
            String req = "select c.*,p.*,u.* from commande c inner join produit p on p.id=c.id_p_id inner join user u on c.id_u_id=u.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
            
            while (rs.next()) {
              
                commande p = new commande();
                p.setId(rs.getInt("id"));
                p.setTaillec(rs.getString("taille"));
               
        
            produit prod = new produit(rs.getString("nom_p")); 
            User user = new User(rs.getString("username"));
            
                p.setProduit(prod);
                p.setUser(user);
                
                personnes.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
        }
}
