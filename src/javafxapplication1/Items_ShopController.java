/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.Badge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import entities.categories;
import entities.marques;
import entities.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import services.CategorieService;
import services.CommandeService;
import services.MarqueService;
import services.ProduitService;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Items_ShopController implements Initializable {

    @FXML
    private Label namelabel1;
    @FXML
    private Label prixlabel1;
    @FXML
    private ImageView prodimg1;
    
    private Listner listner;
    
     private produit prod;
    @FXML
    private VBox vbox;
    
    @FXML 
    private void click(MouseEvent mouseEvent)
    {
        listner.onClickListener(prod);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
        
        
       
    }    
    
     public void  refresh()
     {
         vbox.getChildren().removeAll(vbox.getChildren());
     }
    public void setData(produit prod, Listner listner) 
    {
       
        this.prod=prod;
        this.listner=listner;
        namelabel1.setText(prod.getNom());
        prixlabel1.setText(prod.getPrix()+ "DT");
        Image image;
        try {
            image = new Image(new FileInputStream(Badge.url_upload +prod.getImg()));
            prodimg1.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Items_ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }    
}
