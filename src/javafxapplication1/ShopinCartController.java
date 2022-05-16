/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.produit;
import entities.panier;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import util.session;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class ShopinCartController implements Initializable {

    @FXML
    private ImageView home;
    @FXML
    private ImageView shop;
    @FXML
    private GridPane grid;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button commande;
    @FXML
    private Label prodvide;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        if (JavaFXApplication1.pan.size()==0)
        {
            prodvide.setText("Panier vide");
             commande.setVisible(false);
        }
            else
         prodvide.setText("Votre Produits:");  
            
        showpanier();
    }    
 
    public void showpanier()
    {
        int column=0;
        int row=1;
        int x=0;
       
         try {
        for(panier pan : JavaFXApplication1.pan)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Cart_item.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                Cart_itemController items = fxmlloader.getController();
                items.setData(pan);
                
                if(column == 1 )
                {
                    column = 0;
                    row++;
                }
                
                grid.add(pane,column++,row);
                
                //width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                
                
                GridPane.setMargin(pane, new Insets(10));
    
        }
        } catch (IOException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    @FXML
    private void homeaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("Home_turbo_devs.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void shopaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void commandeaction(ActionEvent event) throws Exception{
        if(session.getSession().getId()!=0)
        sendMail(event);
        else
        {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Connection Error");
alert.setContentText("you must connect first !");

alert.showAndWait();
        }
     // AhmedMail.sendMail("12355", "yaga77328@gmail.com");
        
        
    }
    
    public void sendMail(ActionEvent event) throws IOException
    {
        
           Random r = new Random();
        String  y =""+ r.nextInt(99999);
        JavaFXApplication1.myWeb2=y;
        Properties props = System.getProperties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        
        String myAccount = "yaga77328@gmail.com";
        String password="BabaYaga12345";
        
        Session session = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
        
            protected PasswordAuthentication getPasswordAuthentication(){
                return new  PasswordAuthentication(myAccount,password);
            }
        });
    
      try{
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(myAccount));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress("missaouiahmed2000@gmail.com"));
            m.setSubject("Turbo devs");
            m.setText(y);
            
            Transport.send(m);
            
            System.out.println("sent");
            
          
      }catch(Exception e){
          e.printStackTrace();
      }
    
    nextpage(event);
      
    }
    
    public void nextpage(ActionEvent event) throws IOException{
   Parent root = FXMLLoader.load(getClass().getResource("Commande_verif.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
    
}
