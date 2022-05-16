/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Article;
import entities.Badge;
import entities.User;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.image.Image;
import util.MyDB;
import util.sendmail;
/**
 *
 * @author ksaay
 */
public class ServiceArticle implements IService<Article>{
 //   public ConnectionRequest req;
   Connection cnx;

    public ServiceArticle() {
        cnx = MyDB.getInstance().getConnection();
    }
    
    @Override
    public void ajouter(Article t) {
        try {
              DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();
          t.setDatea(date.format(now));
            String req = "insert into article(titre,descr,datea,img,user_id)"
                    + "values( '" + t.getTitre()+ "','" + t.getDescr()+ "','"
                    +  t.getDatea()+ "','"
                    +  t.getImg()+ "','" +  t.getUser().getId()+ " ')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Article ajoutée");
            ServiceUser u = new ServiceUser();
            for(User us : u.recuperer())
            {
                sendmail.sendEmail(us.getEmail(),t.getTitre(),t.getDescr(),us.getUsername());
            }
            
            
            //sendmail.sendEmail(sendEmail);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Article t) {
        try {
            String req = "update article set titre= ?, descr =?,  img =?, user_id =? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getTitre());
            ps.setString(2, t.getDescr());
            ps.setString(3, t.getImg());
            ps.setInt(4, t.getUser().getId());
            ps.setInt(5, t.getId());
            ps.executeUpdate();
            System.out.println("Article modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
       try {
           String req = "delete from  article where id= ?";
           PreparedStatement ps = cnx.prepareStatement(req);
           ps.setInt(1, id);
            ps.executeUpdate();
       } catch (SQLException ex) {
          System.out.println(ex.getMessage());
       }
    }

   // @Override
    public ObservableList<Article> recuperer(int order) {
        ServiceUser psu = new ServiceUser();
        ObservableList<Article> articles =FXCollections.observableArrayList();   
        try {
            String req="";
                   if(order == 1)
                   {
                       req = "select * from article a  inner join user u  on a.user_id = u.id order by a.id asc";
                   }
                   else
                   {
                       if(order == 2)
                       {
                           req = "select * from article a  inner join user u  on a.user_id = u.id order by a.id desc";
                       }
                       else
                       {
                           req = "select * from article a  inner join user u  on a.user_id = u.id ";
                       }
                   }
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
ResultSet rs2;
            while (rs.next()) {
                Article p = new Article();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setDescr(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                 p.setDatea(rs.getString("datea"));
                  ObservableList<String> listc =  FXCollections.observableArrayList();
                  int index = 0 ; 
                  int count = 0 ; 
                 for(User c :  psu.recuperer())
        {
           if(c.getId() == rs.getInt(7) )
               index = count;
            listc.add(c.getUsername());
           count++;
        } 
            p.getUsers().setItems(listc);
            p.getUsers().getSelectionModel().select(index);
         //   p.getUsers().addEventHandler(EventType.ROOT, eh);
         p.getUsers().getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            
             p.setUser(psu.recuperer().get(p.getUsers().getSelectionModel().getSelectedIndex()));
             //System.out.println(p.getUser().getId());
             modifier(p);
    
}); 
                 User u = new User();
                 
                  u.setId(rs.getInt(7));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setImg(rs.getString(14));
                
                 FileInputStream inputStream;
                try {
                        inputStream = new FileInputStream(Badge.url_upload + rs.getString("img"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setImg_a(image);

                } catch (FileNotFoundException ex) {
                   // Logger.getLogger(ReclamationFormController.class.getName()).log(Level.SEVERE, null, ex);

                }
                 u.setNbp(rs.getInt("nbp"));
                   u.setMdp(rs.getString("mdp"));
                    u.setRole(rs.getString("role"));
                      p.setUser(u);
                  articles.add(p);
            }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      // System.out.print(articles);
        return articles;
    }
     public List<Article> rec_search(String x) {
        List<Article> articles = new ArrayList<>();
        try {
            String req="select * from article a  inner join user u  on a.user_id = u.id where titre "
                    + "like '%" + x+ "%' or descr like '%" + x+ "%' ";
                    
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
ResultSet rs2;
            while (rs.next()) {
                Article p = new Article();
                p.setId(rs.getInt(1));
                p.setTitre(rs.getString("titre"));
                p.setDescr(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                 p.setDatea(rs.getString("datea"));
                 User u = new User();
                 
                  u.setId(rs.getInt(7));
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setImg(rs.getString(14));
                 u.setNbp(rs.getInt("nbp"));
                   u.setMdp(rs.getString("mdp"));
                    u.setRole(rs.getString("role"));
                      p.setUser(u);
                  articles.add(p);
            }
            

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      // System.out.print(articles);
        return articles;
    }
}
