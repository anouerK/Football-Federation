/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MainBackController implements Initializable {

    @FXML
    private ImageView rollback;
    @FXML
    private ImageView articles;
    @FXML
    private ImageView users;
    @FXML
    private ImageView badges;
    @FXML
    private ImageView shopb;
    @FXML
    private ImageView tournements;
    @FXML
    private ImageView stadiums;
    @FXML
    private ImageView referee;
    @FXML
    private ImageView rewards;
    @FXML
    private ImageView club;
    @FXML
    private ImageView sponsor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*
          Image image1 ;
          image1= new Image("/image/tournoi.png");
          tournements.setImage(image1);
          image1= new Image("/AhmedImages/st.png");
          stadiums.setImage(image1);
          image1= new Image("/AhmedImages/referee.png");
          referee.setImage(image1);
          image1= new Image("/AhmedImages/out.png");
          
          rollback.setImage(image1);
          image1 = new Image("/AhmedImages/users.png");
          users.setImage(image1);
           image1 = new Image("/AhmedImages/articlej.png");
          articles.setImage(image1);
          image1 = new Image("/AhmedImages/badge.png");
          badges.setImage(image1);
          image1 = new Image("/AhmedImages/shop3.png");
          shopb.setImage(image1);
          image1 = new Image("/image/reward.png");
          rewards.setImage(image1);
            */
    }    

    @FXML
    private void rollback(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/Home_turbo_devs.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void articles(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/Article.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void users(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/backofficeForm.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void badges(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/Badge.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void shopb(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/HomaAhmed.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void tournements(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/affichTournoi.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Tournement");
   stage.show();
    }


    @FXML
    private void stadiums(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/afficher_stade.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void referee(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/afficher_arbitre.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void rewards(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/AffichRewards.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void club(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/Club.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Club");
   stage.show();
    }

    @FXML
    private void sponsor(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/views/Sponsor.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Sponsor");
   stage.show();
    }
    
}
