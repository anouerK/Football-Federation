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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class HomaAhmedController implements Initializable {

    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button categories;
    @FXML
    private Button marques;
    @FXML
    private Button produits;
    @FXML
    private ImageView rollback;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
    }    

   @FXML
    public void switchtocategories(ActionEvent event) throws IOException
    {
   Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    @FXML
    public void switchtomarques(ActionEvent event) throws IOException
    {
    Parent root = FXMLLoader.load(getClass().getResource("Marque.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
        
    }
    
     @FXML
    public void switchtoproduits(ActionEvent event) throws IOException
    {
   Parent root = FXMLLoader.load(getClass().getResource("Produit.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();

    }

    private void switchtoshop(ActionEvent event) throws IOException {
    
   Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
        
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
