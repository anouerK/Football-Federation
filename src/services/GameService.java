/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Arbitre;
import entities.club;
import entities.Game;
import entities.Stade;
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
 * @author Ahmed.A.Hsouna
 */
public class GameService implements gameinterface<Game> {

    Connection cnx;

    public GameService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void deleteGame(int id) {
        // throw new UnsupportedOperationException("Not supported yet.");
        try {
            String req = "delete from game  where id=" + " '" + id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();

            System.out.println("Game deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void addGame(Game t) {
        try {
            String req = "insert into game(club1_id ,club2_id,arbitre_id ,stade_id ,r1, r2, dated, tournoi_id)"
                    + "values( '" + t.getClub1().getId() + "','" + t.getClub2().getId() + "','" + t.getArbitre().getId() + "','" + t.getStade().getId() + "','" + t.getR1() + " ','" + t.getR2() + " ','" + t.getDeted() + " ','" + t.getTournoi().getId() + " ')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Game ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void updateGame(Game t) {
        try {
            String req = "update game set club1_id= ?, club2_id =?, arbitre_id =? , stade_id =?, r1 =?, r2 =?, dated =?, tournoi_id =? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getClub1().getId());

            ps.setInt(2, t.getClub2().getId());
            ps.setInt(3, t.getArbitre().getId());
            ps.setInt(4, t.getStade().getId());
            ps.setInt(5, t.getR1());
            ps.setInt(6, t.getR2());
            ps.setString(7, t.getDeted());
            ps.setInt(8, t.getTournoi().getId());
            ps.executeUpdate();
            System.out.println("Game modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public ObservableList<Game> affichGame() {

        ObservableList<Game> games = FXCollections.observableArrayList();

        try {
            String req = "SELECT * from game g inner join club c  on c.id=g.club1_id inner join club on  club.id=g.club2_id ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
               // System.out.println("\n "+rs.getString(18));
               // Tournoi t = new Tournoi(rs.getInt("tournoi_id"));
                club c = new club (rs.getString("nomc"));
                c.setId(rs.getInt("club1_id"));
                club c1 = new club (rs.getString(18));
                c1.setId(rs.getInt("club2_id"));
                
                
                Game p = new Game();
                Arbitre a =new Arbitre(rs.getString("nom_a"));
                a.setId(rs.getInt("arbitre_id"));
                 Stade s =new Stade(rs.getString("noms"));
                s.setId(rs.getInt("stade_id"));
                p.setId(rs.getInt("id"));

               // p.setTournoi(t);
               
                p.setClub1(c);
                p.setClub2(c1);
              p.setArbitre(a);
                  p.setStade(s);
                p.setR1(rs.getInt("r1"));
               
                p.setR2(rs.getInt("r2"));
               
                games.add(p);

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return games;
    }

    public ObservableList<Game> rechercheGame(int id) throws FileNotFoundException {

        ObservableList<Game> rewards = FXCollections.observableArrayList();

        try {
            String req = "SELECT * from game g inner join club c  on c.id=g.club1_id inner join club on  club.id=g.club2_id join arbitre a on a.id=g.arbitre_id join stade s on s.id=g.stade_id WHERE g.tournoi_id = " + id;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Tournoi t = new Tournoi(rs.getInt("tournoi_id"));
                System.out.println("hi"+t.getId());
                Game p = new Game(t.getId());
                 club c = new club (rs.getString("nomc"),rs.getString("logo"));
                   Arbitre a =new Arbitre(rs.getString("nomc"),rs.getString("image"));
               a.setId(rs.getInt("arbitre_id"));
                 Stade s =new Stade(rs.getString("noms"),rs.getString("lieu"),rs.getString("photo"));
                s.setId(rs.getInt("stade_id"));
                c.setId(rs.getInt("club1_id"));
              //  c.setLogo(rs.getString("logo"));
                club c1 = new club (rs.getString(18),rs.getString(19));
                c1.setId(rs.getInt("club2_id"));
               //  c1.setLogo(rs.getString("logo"));
                  p.setClub1(c);
                p.setClub2(c1);
              p.setArbitre(a);
                  p.setStade(s);
                p.setR1(rs.getInt("r1"));
               
                p.setR2(rs.getInt("r2"));
               
            
                FileInputStream inputStream;
                inputStream = new FileInputStream(Game.url_upload +p.getClub1().getLogo());
                //   inputStream = new FileInputStream("src/voyagep.png");
                Image image = new Image(inputStream);
                //   imgViewV = new ImageView(image);
              p.getClub1().setImg(image);
                FileInputStream inputStream1;
                inputStream1 = new FileInputStream(Game.url_upload +p.getClub2().getLogo());
                //   inputStream = new FileInputStream("src/voyagep.png");
                Image image1 = new Image(inputStream1);
                //   imgViewV = new ImageView(image);
               p.getClub2().setImg(image1);
                FileInputStream inputStream2;
                inputStream2 = new FileInputStream(Game.url_upload +p.getArbitre().getImage());
                //   inputStream = new FileInputStream("src/voyagep.png");
                Image image2 = new Image(inputStream2);
                p.getArbitre().setImage2(image2);
                 FileInputStream inputStream3;
                inputStream3 = new FileInputStream(Game.url_upload +p.getStade().getPhoto());
                //   inputStream = new FileInputStream("src/voyagep.png");
                Image image3 = new Image(inputStream3);
                p.getStade().setImage2(image3);
                    rewards.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rewards;
    } 

}
