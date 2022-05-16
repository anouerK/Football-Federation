/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Joueur;
import entities.club;
import gui.ClassementController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.ServiceJoueur;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class ClubDetailController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label nom;
    @FXML
    private Label descr;
    @FXML
    private Label presi;
    @FXML
    private ListView<Joueur> listJ;
    @FXML
    private VBox xi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         //classement t=list.getSelectionModel().getSelectedItem(); 
        
          ServiceJoueur st=new ServiceJoueur();
      
          
          ObservableList<Joueur> y = FXCollections.observableArrayList();
          // st.rechercheReward(ClassementController.selectedClub.getClub().getId());
          club t=new club(ClassementController.selectedClub.getClub().getId(),ClassementController.selectedClub.getClub().getNomc(),ClassementController.selectedClub.getClub().getDescr(),ClassementController.selectedClub.getClub().getPresident(),ClassementController.selectedClub.getClub().getImg());
          // Rewards r=new Rewards(t.getId(),t.getNomt());
              img.setImage(t.getImg());
             
                nom.setText(t.getNomc());
                descr.setText(t.getDescr());
               presi.setText(t.getPresident());
              // xi.getChildren().addAll(nom,descr,presi);
       
        try {
           // for(Joueur r :st.rechercheReward(t.getId()))
            
                listJ.setCellFactory(param ->{
      
       
 final ImageView imageview = new ImageView();
 final Label lab = new Label();
  final VBox v= new VBox();
 imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            imageview.setX(300);
           // lab.setPrefSize(40, 40);
    ListCell<Joueur> cell = new ListCell< Joueur>() {
       public void updateItem(Joueur item, boolean empty){
           //super.updateItem(item, true);
        
       
          if(item != null)
          {
 //for (int i = 0; i < st.affichTournoiImg().size(); i++) {
        imageview.setImage(item.getImg());
     setText(item.getNom()+" "+ item.getPrenom());
       //setText(item.getPrenom());
        setLayoutX(10);
       setLayoutY(115);
       setFont(Font.font("Verdana", 20));
                System.out.println(item.getNom());
            getChildren().add(lab);
             setGraphic(imageview);
             // listJ.getAccessibleText();
                 }
      
            
                 }        
      
    };
                  // cell.setText("aaaaa");
                 
           
                 
                
                // cell.setText(lab.getText());
                     return cell ;
         });
         // list.getItems().addAll(tournois);
       // list.getChildrenUnmodifiable();
          listJ.setItems(st.rechercheReward(t.getId()));
            
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClubDetailController.class.getName()).log(Level.SEVERE, null, ex);
             System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void back(ActionEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/Classement.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
    }
    
}
