/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.club;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ServiceClub;

/**
 * FXML Controller class
 *
 * @author oumaymacherif
 */
public class ClubController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private TableView<club> table;
    @FXML
    private TableColumn<club, Integer> col_id;
    @FXML
    private TableColumn<club, String> col_nom;
    @FXML
    private TableColumn<club, String> col_sponsor;
    @FXML
    private TableColumn<club,   Image> col_logo;
    @FXML
    private TextField logo;
    @FXML
    private TextField chercher;
    @FXML
    private TextField nom;
    @FXML
    private TextField descr;
    @FXML
    private TextField id;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane rollback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceClub st=new ServiceClub();

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nomc"));
        col_logo.setCellValueFactory(new PropertyValueFactory<>("img"));
       // col_logo.setCellValueFactory(new PropertyValueFactory<>("img"));
        col_sponsor.setCellValueFactory(new PropertyValueFactory<>("descr"));
       col_logo.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<club, Image> cell = new TableCell<club, Image>() {
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
        //col_nbm.setCellValueFactory(new PropertyValueFactory<>("nbm"));
        //col_nba.setCellValueFactory(new PropertyValueFactory<>("nba"));
        table.setItems(st.afficher());
    }    

    @FXML
    private void update(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void delete(MouseEvent event) {
    }

    @FXML
    private void delete(ActionEvent event) {
    }

    private void home(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void Club(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Club.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    @FXML
    private void add(MouseEvent event) {
    }

    @FXML
    private void add(ActionEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void refresh() {
        
        
        
        
        
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
