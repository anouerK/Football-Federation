/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private TextField email;
    @FXML
    private AnchorPane apCode;
    @FXML
    private TextField code;
    @FXML
    private TextField password;
    
    User user;
    int c;
    @FXML
    private ImageView rollback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        apCode.setVisible(false);
        // TODO
          Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
    }    
    ServiceUser us = new ServiceUser();
    @FXML
    private void send(ActionEvent event) {
        User u = us.getByEmail(email.getText());
        user = u;
        if(u.getId() != 0) {
            c = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
            us.send(c, u.getEmail());
            apCode.setVisible(true);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Email doesn't exist!",ButtonType.APPLY);
            alertA.show();
        }
    } 

    @FXML
    private void confirme(ActionEvent event) throws IOException {
        if(code.getText().equals(c+"")) {
            user.setMdp(password.getText());
            us.modifier(user);
            Alert alertA = new Alert(Alert.AlertType.NONE,"Passwrd changed successfuly!",ButtonType.APPLY);
            alertA.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/signInForm.fxml"));
                ap.getChildren().setAll(pane);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Code incorrect!",ButtonType.APPLY);
            alertA.show();
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/signInForm.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    private void rollback(MouseEvent event) {
    }
    
}
