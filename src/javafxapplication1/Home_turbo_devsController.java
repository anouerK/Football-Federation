/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import util.session;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class Home_turbo_devsController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView articlevf;
    @FXML
    private ImageView shopv;
    @FXML
    private ImageView contactusb;
    @FXML
    private ImageView homeb;
    @FXML
    private ImageView dashboard;
    @FXML
    private ImageView tournements;
    @FXML
    private ImageView loginv;
    @FXML
    private ImageView fakeback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image1 = new Image("/AhmedImages/articlej.png");
            articlevf.setImage(image1);
            image1 = new Image("/AhmedImages/shop3.png");
             shopv.setImage(image1);
               image1 = new Image("/AhmedImages/contact.png");
               contactusb.setImage(image1);
               
               image1 = new Image("/image/tournoi.png");
               tournements.setImage(image1);
                image1 = new Image("/AhmedImages/home9.png");
            homeb.setImage(image1);
            image1 = new Image("/AhmedImages/fakeback.png");
            fakeback.setImage(image1);
      //  articlevf.
      if(session.getSession().getId()!=0)
      {
            image1 = new Image("/AhmedImages/out.png");
          loginv.setImage(image1);
         
          if(session.getSession().getRole().toUpperCase().equals("ADMIN"))
          {
              image1 = new Image("/AhmedImages/dashboard.png");
           dashboard.setImage(image1);
          }
           
      }
      else
      {
           image1 = new Image("/AhmedImages/enter.png");
          loginv.setImage(image1);
      }
    }    
   

    @FXML
    private void articlevf(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArticleFront.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    /*
    private void loginb(ActionEvent event) throws IOException {
        if(loginb.getText()=="Logged")
        {
            session.setSession(null);
            loginb.setText("Login");
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/signInForm.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Login");
   stage.show();
        }
                     
        
    }*/

    @FXML
    private void shopv(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Shop");
   stage.show();
        
    }

    @FXML
    private void contactusb(MouseEvent event) throws IOException {
        if(session.getSession().getId()!= 0)
        {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/frontofficeForm.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Contact Us");
   stage.show(); 
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Connection Error");
alert.setContentText("you must connect first !");

alert.showAndWait();
        }
        
    }

    @FXML
    private void dashboard(MouseEvent event) throws IOException {
        if(session.getSession().getId()!= 0 && session.getSession().getRole().toUpperCase().equals("ADMIN"))
        {
             Parent root = FXMLLoader.load(getClass().getResource("MainBack.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Contact Us");
   stage.show(); 
        }
        
    }

    @FXML
    private void tournements(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/gui/TournoiFront.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Tournement");
   stage.show(); 
    }

    @FXML
    private void loginv(MouseEvent event) throws IOException {
         if(session.getSession().getId()!=0)
        {
            session.setSession(null);
          //  loginb.setText("Login");
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/signInForm.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
    }
    }
}
