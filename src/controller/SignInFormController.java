/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.User;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceUser;
import util.session;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class SignInFormController implements Initializable {

    @FXML
    private TextField tf_in_username;
    @FXML
    private Button btn_in;
    @FXML
    private TextField tf_password;
    @FXML
    private TextField tf_up_username;
    @FXML
    private TextField tf_up_password;
    @FXML
    private TextField tf_up_email;
    @FXML
    private Button btn_up;
    
    ServiceUser us = new ServiceUser();
    @FXML
    private AnchorPane ap;
    
    	Alert alert = new Alert(Alert.AlertType.NONE);
    @FXML
    private ImageView rollback;
    @FXML
    private ImageView background;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
              image1 = new Image("/AhmedImages/background.png");
            background.setImage(image1);
    }    

    @FXML
    private void btn_in_action(ActionEvent event) throws IOException {
        if(us.signIn(tf_in_username.getText(), tf_password.getText())) {
       //"/gui/signInForm.fxml"
   
                Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/Home_turbo_devs.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
            /*
            if(session.getSession().getRole().toUpperCase().equals("CLIENT")){
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/frontofficeForm.fxml"));
                ap.getChildren().setAll(pane);
            }
            else {
                AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/backofficeForm.fxml"));
                ap.getChildren().setAll(pane);
            } */  
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Check username or password!",ButtonType.APPLY);
            alertA.show();
        }
    }

    @FXML
    private void btn_up_action(ActionEvent event) {
        if(!tf_up_username.getText().equals("") && !tf_up_password.getText().equals("") && !tf_up_email.getText().equals("")) {
            User u = new User(tf_up_username.getText(), tf_up_password.getText(), "client", tf_up_email.getText()); 
            us.ajouter(u);
            tf_up_username.setText("");
            tf_up_password.setText("");
            tf_up_email.setText("");
            Alert alertA = new Alert(Alert.AlertType.NONE,"Sign up successful!",ButtonType.APPLY);
            alertA.show();
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
        }
        
    }

    @FXML
    private void fp(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/forgetPassword.fxml"));
                ap.getChildren().setAll(pane);
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
    
}
