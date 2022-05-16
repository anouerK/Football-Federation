/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Rewards;
import entities.Tournoi;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.ServiceRewards;
import services.ServiceTournoi;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class AffichRewardsController implements Initializable {

    @FXML
    private TableColumn<Rewards, Integer> id;
    @FXML
    private TableColumn<Rewards, String> trophe;
    @FXML
    private TableColumn<Rewards, Integer> rank;
    @FXML
    private TableColumn<Rewards, Float> prixR;
    @FXML
    private TableColumn<Rewards, Image> img;
    @FXML
    private TableColumn<Rewards, Tournoi> id_tournoi;
    @FXML
    private Button btx;
    @FXML
    private TableView<Rewards> tableRewards;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceRewards st=new ServiceRewards();
          ServiceTournoi pst=new ServiceTournoi();
          ObservableList<String> listc =  FXCollections.observableArrayList();
         //  Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
        id.setCellValueFactory(new PropertyValueFactory<Rewards,Integer>("id"));
        trophe.setCellValueFactory(new PropertyValueFactory<Rewards,String>("trophe"));
        rank.setCellValueFactory(new PropertyValueFactory<Rewards,Integer>("rank"));
        prixR.setCellValueFactory(new PropertyValueFactory<Rewards,Float>("prixR"));
      
          id_tournoi.setCellValueFactory(new PropertyValueFactory<Rewards,Tournoi>("t")); 
     img.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(150);
            imageview.setFitWidth(150);

            //Set up the Table
            TableCell<Rewards, Image> cell = new TableCell<Rewards, Image>() {
                public void updateItem(Image item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item);
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
        });        
        
        img.setCellValueFactory(new PropertyValueFactory<Rewards, Image>("logo"));
        tableRewards.setItems(st.affichReward());
         tableRewards.setEditable(true);
           trophe.setCellFactory(TextFieldTableCell.forTableColumn());
          //  rank.setCellFactory(TextFieldTableCell.forTableColumn());
           //  prixR.setCellFactory(TextFieldTableCell.forTableColumn());
             // id_tournoi.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
    @FXML
    private void ajoutReward(ActionEvent event) {
            Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AjoutRewards.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
           String css= this.getClass().getResource("bootstrap.css").toExternalForm();

   Scene scene = new Scene(root);
   scene.getStylesheets().add(css);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());
      }
    }
 @FXML
    private void supp(TableColumn.CellEditEvent<Rewards,Integer> event) {
         Rewards product=tableRewards.getSelectionModel().getSelectedItem();
//product.setId(event.getNewValue());
      ServiceRewards st=new ServiceRewards();
    //Tournoi t=new Tournoi (product);
  
     Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok to delete this Rewards id ="+product.getId() +"?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
      st.deleteReward(product.getId());
        tableRewards.setItems(st.affichReward());
} else {
    // ... user chose CANCEL or closed the dialog
}
    }
 @FXML
    private void changeTrophe(TableColumn.CellEditEvent<Rewards, String> event) {
         Rewards product=tableRewards.getSelectionModel().getSelectedItem();
product.setTrophe(event.getNewValue());
    ServiceRewards st=new ServiceRewards();
     ServiceTournoi stv=new ServiceTournoi();
       Tournoi m=new Tournoi (product.getT().getId(),product.getT().getNomt());
    Rewards t=new Rewards (product.getRank(),product.getTrophe(),product.getPrixR(),product.getImg(),m);
    st.updateReward(t);
    }

   

  @FXML
    private void changeRank(TableColumn.CellEditEvent<Rewards,Integer> event) {
    }

    @FXML
    private void changePrix(TableColumn.CellEditEvent<Rewards,Float> event) {
    }

    @FXML
    private void changeImage(TableColumn.CellEditEvent<Rewards,String> event) {
    }

    @FXML
    private void changeTournoi(TableColumn.CellEditEvent<Rewards,String> event) {
    }

    @FXML
    private void backM(ActionEvent event) {
           Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/JavaFXapplication1/MainBack.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
    }
    
}
