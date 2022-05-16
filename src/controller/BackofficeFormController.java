 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.session;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class BackofficeFormController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Button btn_users;
    @FXML
    private AnchorPane ap2;
    @FXML
    private Label lb_username;
    @FXML
    private Label lb_email;
    @FXML
    private Button btn_logout;
    @FXML
    private ImageView rollback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lb_email.setText(session.getSession().getEmail());
        lb_username.setText(session.getSession().getUsername());
          Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
    }    

    @FXML
    private void btn_usersAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/userForm.fxml"));
                ap2.getChildren().setAll(pane);
    }

    @FXML
    private void btn_logoutAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/signInForm.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    private void profile(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/profilForm.fxml"));
                ap2.getChildren().setAll(pane);
    }

    @FXML
    private void rollback(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/MainBack.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }
    
}
