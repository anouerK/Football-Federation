/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import views.ClubController;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class MainTournoiController implements Initializable {

    @FXML
    private Button id_tournoi;
    @FXML
    private Button id_reward;
    @FXML
    private Button tournoi_F;
    @FXML
    private Button club;
    @FXML
    private Button jour;
    @FXML
    private Button st;
    @FXML
    private Button ar;
    @FXML
    private Button gm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void showTournoi(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("affichTournoi.fxml"));
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
//stage.initStyle(StageStyle.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("bootstrap.css");   
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
   
    }

    @FXML
    private void showReward(ActionEvent event) {
          Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("affichRewards.fxml"));
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
//stage.initStyle(StageStyle.TRANSPARENT);

        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("bootstrap.css");   
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
    }

    @FXML
    private void showTournoiF(ActionEvent event) {
        
           Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("TournoiFront.fxml"));
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
//stage.initStyle(StageStyle.TRANSPARENT);

       // scene.setFill(Color.TRANSPARENT);
       // scene.getStylesheets().add("bootstrap.css");   
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
        
    }

    @FXML
    private void showClub(ActionEvent event) {
          try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Club.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showJoueur(ActionEvent event) {
         try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Joueur.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene); 
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showStade(ActionEvent event) {
         try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("afficher_stade.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene); 
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showArbitre(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("afficher_arbitre.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene); 
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void showGame(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("afficher_game.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene); 
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
