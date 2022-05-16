/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Rewards;
import entities.Tournoi;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class ServiceRewards  implements RewardInterface<Rewards>{
     Connection cnx;
    
   

    public ServiceRewards() {
         cnx = MyDB.getInstance().getConnection();
    }

   @Override 
    public void deleteReward(int id) {
       // throw new UnsupportedOperationException("Not supported yet.");
           try {
            String req = "delete from rewards  where id_r=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
         
          
            System.out.println("rewards deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   @Override  
    public void addReward(Rewards t) {
         try {
            String req = "insert into rewards(tournoi_id,rank,trophe,prix_r,img)"
                    + "values( '" + t.getT().getId()+ "','" + t.getRank() + "','" + t.getTrophe()+ "','" + t.getPrixR()+"','" + t.getImg() +
                     " ')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Reward ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
@Override
    public void updateReward(Rewards t) {
         try {
            String req = "update rewards set trophe= ?, rank =?, prix_r =? , img =?, tournoi_id =? where id_r= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
             ps.setString(1,t.getTrophe());
           
            ps.setInt(2, t.getRank());
           
             ps.setFloat(3,t.getPrixR());
              ps.setString(4, t.getImg());
                ps.setInt(5, t.getT().getId());
            ps.setInt(6, t.getId());
            ps.executeUpdate();
            System.out.println("Reward modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
  @Override
    public ObservableList<Rewards> affichReward(){
  
          ObservableList<Rewards> rewards =FXCollections.observableArrayList();
         
        try {
            String req = "select r.*,t.* from rewards r  inner join tournoi t on t.id=r.tournoi_id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                  Tournoi t = new Tournoi(rs.getString("nomt"));
                Rewards p = new Rewards();
                  p.setId(rs.getInt("id_r"));
                 
                  p.setT(t);  
                 
               p.setRank(rs.getInt("rank"));
                 p.setTrophe(rs.getString("trophe"));
                  p.setPrixR(rs.getFloat("prix_r"));
                // p.setImg(rs.getString("img"));
              
               FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Rewards.url_upload + rs.getString("img"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setLogo(image);
                      rewards.add(p);
                } catch (FileNotFoundException ex) {
                      System.out.println(ex.getMessage());

                }
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rewards;
    }
    
    public ObservableList<Rewards> rechercheReward(int id) throws FileNotFoundException{
  
          ObservableList<Rewards> rewards =FXCollections.observableArrayList();
         
        try {
            String req = "select * from rewards where tournoi_id= "+id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                  Tournoi t = new Tournoi(rs.getString("tournoi_id"));
                Rewards p = new Rewards(t.getId());
               FileInputStream inputStream;
                try {
                    inputStream = new FileInputStream(Rewards.url_upload + rs.getString("img"));
                    //   inputStream = new FileInputStream("src/voyagep.png");
                    Image image = new Image(inputStream);
                    //   imgViewV = new ImageView(image);
                    p.setLogo(image);
               rewards.add(p);
            }   catch (SQLException ex) {
                    Logger.getLogger(ServiceRewards.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rewards;
    }

}
