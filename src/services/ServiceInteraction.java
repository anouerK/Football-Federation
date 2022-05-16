/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Badge;
import entities.Interaction;
import entities.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import util.MyDB;
import util.session;

/**
 *
 * @author ksaay
 */
public class ServiceInteraction implements IService<Interaction> {

    Connection cnx;
    ServiceUser su = new ServiceUser();
    public ServiceInteraction() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(Interaction t) {
        try {
            String req = "insert into interaction(descrp,user_id,type,article_id)"
                    + "values( '" + t.getDescrp() + "','" + t.getUser().getId() + "','" + t.getType() + "',"
                    + t.getArticle().getId() + " )";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("interaction ajoutée");
            if(t.getType().equals("comment"))
            {
                 String req2 = "update user set nbp = nbp+1 where id = ?";
                  PreparedStatement ps = cnx.prepareStatement(req2);
            ps.setInt(1, t.getUser().getId());
            ps.executeUpdate();
            
                System.out.println(t.getUser().getNbp()+1);
                 String req3 = "select * from badge where nb <= '" + t.getUser().getNbp()+1 + "' order by nb desc";
           
            
          // Statement st3 = cnx.createStatement();
         // PreparedStatement st = cnx.prepareStatement(req);
            //  st.setInt(1, idarticle);
            ResultSet rs3 = st.executeQuery(req3);
            
            if(rs3.next()) {
                
                 Badge p = new Badge();
                p.setId(rs3.getInt(1));
                p.setNomB(rs3.getString("nom_b"));
                p.setLogoB(rs3.getString("logo_b"));
                 FileInputStream inputStream;
                try {
                        inputStream = new FileInputStream(Badge.url_upload + rs3.getString("logo_b"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setImg(image);

                } catch (FileNotFoundException ex) {
                   // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                p.setNb(rs3.getInt("nb"));
                session.getSession().setBadge(p);
                su.modifier( session.getSession());
                
            
                
            }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifier(Interaction t) {
        try {
            String req = "update interaction set descrp= ?, user_id =?, type =? , article_id where idi= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getDescrp());
            ps.setInt(2, t.getUser().getId());
            ps.setString(3, t.getType());
            ps.setInt(4, t.getArticle().getId());
            ps.setInt(4, t.getIdi());
            ps.executeUpdate();
            System.out.println("Interaction modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void supprimer(int id) {
        try {
            String req = "delete from  interaction where idi= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //@Override
    public ObservableList<Interaction> recuperer(int order) {
        
        ObservableList<Interaction> interactions = FXCollections.observableArrayList();        
        try {
            String req = "";
            if (order == 1) {
                req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id   order by a.id asc";
            } else {
                if (order == 2) {
                    req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id  order by a.id desc";
                } else {
                    req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id ";
                }
            }
            
            java.sql.Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                Interaction p = new Interaction();
                p.setIdi(rs.getInt(1));
                p.setDescrp(rs.getString("descrp"));
                p.setType(rs.getString("type"));
                User u = new User();
                Badge b = new Badge();
                
                u.setId(rs.getInt(6));
                u.setUsername(rs.getString(8));
                // u.(rs.getString("img"));
                // p.set(rs.getInt("nb"));
                FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Badge.url_upload + rs.getString("logo_b"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    b.setImg(image);
                    
                } catch (FileNotFoundException ex) {
                    // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                u.setBadge(b);
                p.setUser(u);
                interactions.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return interactions;
    }
    
    public ObservableList<Interaction> recupererbyid(int idarticle) {
        
        try {
            ObservableList<Interaction> interactions = FXCollections.observableArrayList();
            
            
           
            String req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id where a.article_id = '" + idarticle + "'and type = 'comment' order by a.idi asc";
           
            
           java.sql.Statement st = cnx.createStatement();
         // PreparedStatement st = cnx.prepareStatement(req);
            //  st.setInt(1, idarticle);
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                Interaction p = new Interaction();
                p.setIdi(rs.getInt(1));
                p.setDescrp(rs.getString("descrp"));
                p.setType(rs.getString("type"));
                User u = new User();
                Badge b = new Badge();
                
                u.setId(rs.getInt(6));
                u.setUsername(rs.getString(8));
                // u.(rs.getString("img"));
                // p.set(rs.getInt("nb"));
                FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Badge.url_upload + rs.getString("logo_b"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    b.setImg(image);
                    
                } catch (FileNotFoundException ex) {
                    // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                u.setBadge(b);
                
                p.setUser(u);
                interactions.add(p);
             
            }
            
            return interactions;
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean deleteLike(int idarticle,int user_id)
    {
        
          try {
            String req = "delete from  interaction where article_id= ? and user_id = ? and type ='up'";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idarticle);
             ps.setInt(2, user_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
    
    
    public boolean recupererLike(int idarticle,int user_id) {
        
        try {
            ObservableList<Interaction> interactions = FXCollections.observableArrayList();
            
            
           
            String req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id where a.article_id = '" + idarticle + "'and a.user_id = '" + user_id + "' and type = 'up' order by a.idi asc";
           
            
           java.sql.Statement st = cnx.createStatement();
         // PreparedStatement st = cnx.prepareStatement(req);
            //  st.setInt(1, idarticle);
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                Interaction p = new Interaction();
                p.setIdi(rs.getInt(1));
                p.setDescrp(rs.getString("descrp"));
                p.setType(rs.getString("type"));
                User u = new User();
                Badge b = new Badge();
                
                u.setId(rs.getInt(6));
                u.setUsername(rs.getString(8));
                // u.(rs.getString("img"));
                // p.set(rs.getInt("nb"));
                FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Badge.url_upload + rs.getString("logo_b"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    b.setImg(image);
                    
                } catch (FileNotFoundException ex) {
                    // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                u.setBadge(b);
                
                p.setUser(u);
                interactions.add(p);
              return true;
            }
            
           
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int recupererLikes(int idarticle) {
         int count =  0 ; 
        try {
            ObservableList<Interaction> interactions = FXCollections.observableArrayList();
            
            
          
            String req = "select * from interaction a  inner join user u  on a.user_id = u.id inner join badge b on b.id = u.badge_id where a.article_id = '" + idarticle + "' and type = 'up' order by a.idi asc";
           
            
           java.sql.Statement st = cnx.createStatement();
         // PreparedStatement st = cnx.prepareStatement(req);
            //  st.setInt(1, idarticle);
            ResultSet rs = st.executeQuery(req);
            
            while (rs.next()) {
                count++;
               
           
            }
            
           
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(ServiceInteraction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    //@Override
    public ObservableList<Interaction> rec_search(String x) {
        ObservableList<Interaction> interactions = FXCollections.observableArrayList();
        /*
        try {
            String req="select * from interaction where nom_b "
                    + "like '%" + x+ "%' ";
                
            
             
            java.sql.Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Badge p = new Badge();
                p.setId(rs.getInt(1));
                p.setNomB(rs.getString("nom_b"));
                p.setLogoB(rs.getString("logo_b"));
                 FileInputStream inputStream;
                try {
                        inputStream = new FileInputStream(Badge.url_upload + rs.getString("logo_b"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setImg(image);

                } catch (FileNotFoundException ex) {
                   // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                p.setNb(rs.getInt("nb"));
                badges.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        return interactions;
    }
}
