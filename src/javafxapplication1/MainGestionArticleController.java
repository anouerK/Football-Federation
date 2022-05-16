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
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MainGestionArticleController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button articles;
    @FXML
    private Button badges;
    @FXML
    private Button btx;
    @FXML
    private Button rah;
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    
    }    
    @FXML
    public void onshowarticles(ActionEvent event) throws IOException
    {
   Parent root = FXMLLoader.load(getClass().getResource("Article.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();

    }
    @FXML
    public void onshowbadges(ActionEvent event) throws IOException
    {
    Parent root = FXMLLoader.load(getClass().getResource("Badge.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("kk");
   stage.show();
        
    }

    @FXML
    private void showArticleF(ActionEvent event)throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("ArticleFront.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
  
   stage.show();
        
    }

    @FXML
    private void rah(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/gui/signinForm.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
  
   stage.show();
    }
     
    
}
