/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Arbitre;
import entities.club;
import entities.Game;
import entities.Stade;
import entities.Tournoi;
//import entitie.Tournoi;
import java.io.FileNotFoundException;
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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.ArbitreService;
import services.GameService;

/**
 * FXML Controller class
 *
 * @author Ahmed.A.Hsouna
 */
public class Afficher_gameController implements Initializable {
    
    GameService pcd = new GameService();

    ObservableList<Game> games = FXCollections.observableArrayList();

    @FXML
    private TableView<Game> tablearbitre;
    @FXML
    private TableColumn<?, ?> id_gm;
    @FXML
    private TableColumn<Game,String> club1_gm;
    @FXML
    private TableColumn<?, ?> image_arb;
    @FXML
    private TableColumn<?, ?> r1_gm;
    @FXML
    private TableColumn<?, ?> r2_gm;
    @FXML
    private TableColumn<Game,String> club2_gm;
    @FXML
    private TableColumn<Game,Arbitre> arbitre_gm;
    @FXML
    private TableColumn<Game,Stade> stade_gm;
    @FXML
    private TextField recherche;
    @FXML
    private Button btndelete;
    @FXML
    private Button refrechh;
    @FXML
    private Button backkk;
    @FXML
    private Button profile;
    @FXML
    private TableColumn<Game,Tournoi> tournoi_gm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherarbitre();
      /* GameService st=new GameService();
        //   ServiceTournoi pst=new ServiceTournoi();
          ObservableList<String> listc =  FXCollections.observableArrayList();
         //  Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
        id_gm.setCellValueFactory(new PropertyValueFactory<>("id"));
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

        //affichGame();
      /*  FilteredList<Game> filteredData = new FilteredList<>(games, t -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Arbitre -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Game.getClub1().getNomc().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Arbitre.getDescp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Arbitre> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablearbitre.comparatorProperty());

        tablearbitre.setItems(sortedData); */

        
    }    
    public void afficherarbitre() {

        games = pcd.affichGame();

        id_gm.setCellValueFactory(new PropertyValueFactory<>("id"));
        r1_gm.setCellValueFactory(new PropertyValueFactory<>("r1"));
        r2_gm.setCellValueFactory(new PropertyValueFactory<>("r2"));
     //   tournoi_gm.setCellValueFactory(new PropertyValueFactory<Game,Tournoi>("tournoi"));
        club1_gm.setCellValueFactory(new PropertyValueFactory<Game,String>("club1"));
        club2_gm.setCellValueFactory(new PropertyValueFactory<Game,String>("club2"));
     //   stade_gm.setCellValueFactory(new PropertyValueFactory<Game,Stade>("stade"));
     //   arbitre_gm.setCellValueFactory(new PropertyValueFactory<Game,Arbitre>("arbitre"));
      //  desc_arb.setCellValueFactory(new PropertyValueFactory<>("descp"));
      
        tablearbitre.setItems(games);
        tablearbitre.setEditable(true);

    }


    @FXML
    private void selectarbitre(MouseEvent event) {
    }

    @FXML
    private void deletee(ActionEvent event) {
    }

    @FXML
    private void refrechh(ActionEvent event) {
    }

    @FXML
    private void backkk(ActionEvent event) {
    }

    @FXML
    private void btnhome(ActionEvent event) {
    }

    @FXML
    private void btnstade(ActionEvent event) {
    }

    @FXML
    private void btnarbitre(ActionEvent event) {
    }
    
}
