/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.panier;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Cart_itemController implements Initializable {

    @FXML
    private Label nom1;
    @FXML
    private Label taille1;
    @FXML
    private Label qte1;
    @FXML
    private Label prix1;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView image;

     
    @FXML
    private VBox vbox;
    
    
     panier panier1;
    @FXML
    private AnchorPane pane1;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setData(panier pan)
    {
        panier1=pan;
       
        Image image1;
       
        try {
            
            image1 = new Image(new FileInputStream(pan.getProd().getImg()));
            image.setImage(image1);
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cart_itemController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        Image image2;
            
            image2 = new Image("/AhmedImages/delete.png");
            delete.setImage(image2);
            
            nom1.setText("Nom: "+pan.getProd().getNom());
            taille1.setText("Taille: "+pan.getTaille());
            qte1.setText("Qunatite: "+ pan.getQte());
            prix1.setText("Prix: "+ pan.getPrix());
        
        
        
            
        
    }
    @FXML
    private void deleteaction(MouseEvent event) {
        JavaFXApplication1.pan.remove(panier1);
        pane1.getChildren().removeAll(pane1.getChildren());
       
        
        
    }
    
}
