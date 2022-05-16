/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import entities.User;
import entities.commande;
import entities.panier;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.CommandeService;
import util.session;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class Commande_verifController implements Initializable {
    
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView qrvVew;
    @FXML
    private TextField veriftxt;
    @FXML
    private Button verifbtn;
    
    CommandeService pscom = new CommandeService();
    
      String myWeb=JavaFXApplication1.myWeb2;
      User user = new User();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
     
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
   
            BitMatrix byteMatrix;
        try {
            byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
       
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
         } catch (WriterException ex) {
            java.util.logging.Logger.getLogger(Commande_verifController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
        qrvVew.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
    }    

    @FXML
    private void verifbtnaction(ActionEvent event) throws IOException {
        if(veriftxt.getText().equals(myWeb))
        {
            for(panier p : JavaFXApplication1.pan)
            {
                commande c  = new commande(session.getSession(),p.getProd(),p.getPrix(),p.getQte(),1,p.getTaille());
                pscom.ajouter(c);
                
            }
           JavaFXApplication1.pan.clear();
            Alert alert = new Alert(AlertType.INFORMATION);
           alert.setTitle("Commande Ajuter");
           alert.setContentText("Votre commande est ajouter avec succes ");
            
            Optional <ButtonType> result = alert.showAndWait();
            if(result.isPresent() && result.get() ==ButtonType.OK){
                Parent root = FXMLLoader.load(getClass().getResource("Shop.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
            }
        }
    }
    
}
