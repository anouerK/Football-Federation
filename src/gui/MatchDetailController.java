/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Arbitre;
import entities.Game;
import entities.Stade;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class MatchDetailController implements Initializable {

    @FXML
    private ImageView club_1;
    @FXML
    private ImageView club_2;
    @FXML
    private ImageView arbitre;
    @FXML
    private ImageView stade;
    @FXML
    private Label stade_nom;
    @FXML
    private Label arbitre_nom;
    @FXML
    private Label r1;
    @FXML
    private Label tir;
    @FXML
    private Label r2;
    @FXML
    private Button backkk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
      
         
      
          Game t=new Game(Game_frontController.selectedGame.getId(),Game_frontController.selectedGame.getClub1(),Game_frontController.selectedGame.getR1(),Game_frontController.selectedGame.getR2(),Game_frontController.selectedGame.getClub2(),Game_frontController.selectedGame.getDeted(),Game_frontController.selectedGame.getArbitre(),Game_frontController.selectedGame.getStade(),Game_frontController.selectedGame.getTournoi());
          // Rewards r=new Rewards(t.getId(),t.getNomt());
       //   System.out.println("kkkkkkt"+t);
            Arbitre a =new Arbitre(t.getId());
           Stade s =new Stade(t.getId());
              club_1.setImage(t.getClub1().getImg());
              club_2.setImage(t.getClub2().getImg());
               arbitre.setImage(t.getArbitre().getImage2());
               r1.setText(String.valueOf(t.getR1()));
               r2.setText(String.valueOf(t.getR2()));
               tir.setText("-");
                stade.setImage(t.getStade().getImage2());
                stade_nom.setText(t.getStade().getNoms()+"  " +t.getStade().getLieu());
              arbitre_nom.setText(t.getArbitre().getNoma());
         
    
  
    }    

    @FXML
    private void backkk(ActionEvent event) {
         try {
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("game_front.fxml"));
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            Game_frontController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
