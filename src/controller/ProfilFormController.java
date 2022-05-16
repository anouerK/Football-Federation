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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ServiceUser;
import util.session;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ProfilFormController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        username.setText(session.getSession().getUsername());
        role.setText(session.getSession().getRole());
        email.setText(session.getSession().getEmail());
    }    

    @FXML
    private void update(ActionEvent event) throws IOException {
        if(!username.getText().equals("") && !role.getText().equals("") && !email.getText().equals("")) {
            ServiceUser us = new ServiceUser();
            User u = new User(session.getSession().getId(), username.getText(),session.getSession().getMdp(), role.getText(), email.getText());
            u.setBadge(session.getSession().getBadge());
            session s = new session(u);
            us.modifier(u);
            Alert alertA = new Alert(Alert.AlertType.NONE,"Updated successful!",ButtonType.APPLY);
            alertA.show();
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
        }
        
        
            
    }
    
}
