/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.classement;
import entities.club;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
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
import util.MyDB;

/**
 *
 * @author oumayma
 */
public class ServiceClassement {
      Connection cnx;
    
   

    public ServiceClassement() {
         cnx = MyDB.getInstance().getConnection();
    }
    
     public ObservableList<classement> findT(int id){
          ObservableList<classement> clubs = FXCollections.observableArrayList();
    try {
            String req = "SELECT * FROM  classement c join club cm on cm.id = c.club_id  WHERE tournoi_id= "+ id ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
             while (rs.next()) {
                // Tournoi p = new Tournoi();
                 classement cl = new classement();
                 club c = new club();
                 c.setId(rs.getInt(4));
                 c.setNom(rs.getString("nomc"));
                 c.setPresident(rs.getString("president"));
                 c.setDescr(rs.getString("descr"));
                 c.setLogo(rs.getString("logo"));
                 cl.setClub(c);
                 clubs.add(cl);
                 
             }
           /// rewards.get(rs.findColumn(req));
        
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return clubs ;
}
       public int findR1(int idc,int idt){
          List<classement> rewards = new ArrayList<>();
          int rows = 0;
    try {
            String req = "SELECT * FROM  Game g WHERE g.club1_id = "+"'"+idc+"' and g.r1 > g.r2 and  g.tournoi_id ="+"'"+idt+"'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           // rewards.get(rs.findColumn(req));
           while(rs.next())
               rows++;
        
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    //return rewards ;
    return rows;
}
         public int findR2(int idc,int idt){
          List<classement> rewards = new ArrayList<>();
          int rows = 0;
    try {
            String req = "SELECT * FROM  Game g WHERE g.club2_id = "+"'"+idc+"' and g.r2 > g.r1 and  g.tournoi_id ="+"'"+idt+"'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
             while(rs.next())
               rows++;
        
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return rows ;
}
         
           public int findR(int idc,int idt){
          List<classement> rewards = new ArrayList<>();
          int rows=0;
    try {
            String req = "SELECT * FROM  Game g WHERE (g.club2_id = "+"'"+idc+"' or g.club1_id = "+"'"+idc+"' )and g.r2 = g.r1 and  g.tournoi_id ="+"'"+idt+"'" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           while(rs.next())
               rows++;
        
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    return rows ;
}
     public ObservableList<classement> affichcl(int id){
  
          ObservableList<classement> classement = FXCollections.observableArrayList();;
            List<classement> cl = new ArrayList<>();
             ArrayList<Long> c = new ArrayList<>();
            
       //  Array data[];
         int s=0;
         
             classement=findT(id);
             for(classement cls :classement)
             {
                 
              try {
                  s=0;
                  s+= findR(cls.getClub().getId(),id);
                  s+= (findR1(cls.getClub().getId(), id)*3);
                  s+= (findR2(cls.getClub().getId(), id)*3);
                  cls.setPts(s);
                  FileInputStream inputStream;
                  inputStream = new FileInputStream(club.url_upload + cls.getClub().getLogo()); //cls.setPts(99);
                  //   inputStream = new FileInputStream("src/voyagep.png");
                  Image image = new Image(inputStream);
                  //   imgViewV = new ImageView(image);
                  cls.getClub().setImg(image);
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(ServiceClassement.class.getName()).log(Level.SEVERE, null, ex);
              }
             }
    /*
                  try {
            String req = "select * from club ";
          
     rewards=findT(id);
  Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                 // Tournoi x = new Tournoi(rs.getString("nomt"));
                   club y = new club(rs.getString("nomc"),rs.getString("logo"));
                classement p = new classement();
                  p.setId(rs.getInt("id"));
                           for(club t : rewards)
         {
             cl=findR1(t.getTournoi().getId(),id);
                    // long total = list.stream().distinct().count();

             s1=cl.stream().count()*3;
              cl=findR2(t.getTournoi().getId(),id);
               s2=cl.stream().count() * 3;
                cl=findR(t.getTournoi().getId(),id);
                 s3=cl.stream().count() * 1;
                 s=s1+s2+s3;
              s=t.getPts();
              c.add(s);
             p.setClub(y);
               rewards.add(p);
         }
              //   p.setPts(0);
                
              
             
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }*/
        return classement;
    }
    
}
