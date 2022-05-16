/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Game;
import entities.Tournoi;
import entities.classement;
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
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import services.GameService;

/**
 * FXML Controller class
 *
 * @author Ahmed.A.Hsouna
 */
public class Game_frontController implements Initializable {
 public static Game selectedGame;
    @FXML
    private ListView<Game> list_gm;
    @FXML
    private Button backkk;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           GameService st=new GameService();
      
          
          ObservableList<Game> y = FXCollections.observableArrayList();
          // st.rechercheReward(ClassementController.selectedClub.getClub().getId());
          Tournoi t=new Tournoi(TournoiFrontController.selectedtournoi.getId());
    
       
        try {
           // for(Joueur r :st.rechercheReward(t.getId()))
            
                list_gm.setCellFactory(param ->{
      
       
 final ImageView imageview = new ImageView();
 final Label l=new Label();
   final GridPane gridPane = new GridPane(); 
  final VBox v= new VBox();
 imageview.setFitHeight(150);
            imageview.setFitWidth(100);
                        imageview.setLayoutX(300);
           final ImageView imageview1 = new ImageView();
 
 
 imageview1.setFitHeight(150);
            imageview1.setFitWidth(100);
                        imageview1.setLayoutX(100);

           // lab.setPrefSize(40, 40);
    ListCell<Game> cell = new ListCell< Game>() {
       public void updateItem(Game item, boolean empty){
           //super.updateItem(item, true);
        
          // System.out.println(item.getArbitre());
          if(item != null)
          {
 //for (int i = 0; i < st.affichTournoiImg().size(); i++) {
        imageview.setImage(item.getClub1().getImg());
         imageview1.setImage(item.getClub2().getImg());
    // setText(item.getClub1().getNomc()+"       "+item.getClub2().getNomc());
       //setText(item.getPrenom());
        setLayoutX(10);
       setLayoutY(115);
       setFont(Font.font("Verdana", 20));
        GridPane.setConstraints(imageview, 0, 0, 1, 3); 
        GridPane.setValignment(imageview, VPos.TOP);
         l.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;"); 
         l.setText(item.getClub1().getNomc()+"        "  +item.getR1()+"   -    "+item.getR2()+"     "+item.getClub2().getNomc());
          GridPane.setConstraints(l, 1, 0); 
         GridPane.setConstraints(imageview1, 3, 0); 
          GridPane.setValignment(imageview1, VPos.CENTER);
           gridPane.getChildren().setAll(imageview, imageview1,l); 
             setGraphic(gridPane );
             // setGraphic(imageview1);
            // v.getChildren().addAll(imageview,imageview1);
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
        System.out.println("ok"+st.rechercheGame(t.getId())); 
          list_gm.setItems(st.rechercheGame(t.getId()));
             
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Game_frontController.class.getName()).log(Level.SEVERE, null, ex);
             System.err.println(ex.getMessage());
        }
    }    

    @FXML
    private void show_gameD(MouseEvent event) {
            Parent root;
        try {
          
             Game t=list_gm.getSelectionModel().getSelectedItem();
             System.out.println(t);
              selectedGame= t;
               root = FXMLLoader.load(getClass().getResource("MatchDetail.fxml"));
            System.out.println(selectedGame);
         
           ObservableList<classement> tournois = FXCollections.observableArrayList();
      
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);

   stage.setScene(scene);
   stage.setTitle("Club Detail");
  
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
    }

    @FXML
    private void backkk(ActionEvent event) {
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("TournoiFront.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
    }
    
}
