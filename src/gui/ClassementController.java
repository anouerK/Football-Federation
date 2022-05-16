/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Tournoi;
import entities.classement;
import entities.club;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceClassement;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class ClassementController implements Initializable {
public int id;
  public static classement selectedClub;
    @FXML
    private TableView<classement> tab;
    @FXML
    private TableColumn<classement, club> club;
    @FXML
    private TableColumn<classement, Integer> pts;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceClassement s= new ServiceClassement();
         ObservableList<Tournoi> tournois = FXCollections.observableArrayList();
      //  Tournoi t=list.getSelectionModel().getSelectedItem();
      
      s.affichcl(TournoiFrontController.selectedtournoi.getId());
       //  Tournoi t=new Tournoi();
       
          classement c =new classement();
       
        
         club.setCellValueFactory(new PropertyValueFactory<classement,club>("club"));
        pts.setCellValueFactory(new PropertyValueFactory<classement,Integer>("pts"));
     
       pts.setSortType(TableColumn.SortType.DESCENDING);
          club.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(65);
            imageview.setFitWidth(75);

            //Set up the Table
            TableCell<classement, club> cell = new TableCell<classement, club>() {
                public void updateItem(club item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item.getImg());
                    setText(item.getNomc());
                   
                    }
                }
            };
            
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            // setText(it.getNomc());
            return cell;
        });        
          
             tab.setItems(s.affichcl(TournoiFrontController.selectedtournoi.getId()));
        System.out.println(s.affichcl(TournoiFrontController.selectedtournoi.getId()));
      // tab.sort();
        //System.out.println(s.affichcl(t.getTournoi().getId()));
        
    }    

    @FXML
    private void backMe(ActionEvent event) {
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

    @FXML
    private void ClubDetail(MouseEvent event) {
           Parent root;
        try {
          
             classement t=tab.getSelectionModel().getSelectedItem();
             System.out.println(t.getClub());
              selectedClub= t;
               root = FXMLLoader.load(getClass().getResource("/views/ClubDetail.fxml"));
            System.out.println(selectedClub.getClub());
         
           ObservableList<classement> tournois = FXCollections.observableArrayList();
      
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);

   stage.setScene(scene);
   stage.setTitle("Club Detail");
  
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
       
    }
    
}
