/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.Reclamation;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ReclamationFormController implements Initializable {

    @FXML
    private TableView<Reclamation> tv_rec;
    @FXML
    private TableColumn<Reclamation, String> tc_id;
    @FXML
    private TableColumn<Reclamation, String> tc_object;
    @FXML
    private TableColumn<Reclamation, String> tc_description;
    @FXML
    private TableColumn<Reclamation, String> tc_status;
    @FXML
    private Button btn_delete;
    @FXML
    private Button btn_add;
    @FXML
    private TextField tf_search;

    ReclamationService rs = new ReclamationService();
    @FXML
    private AnchorPane ap;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tc_object.setCellValueFactory(new PropertyValueFactory<>("objet"));
                    tc_description.setCellValueFactory(new PropertyValueFactory<>("desc"));
                    tc_status.setCellValueFactory(new PropertyValueFactory<>("status"));
                    tv_rec.setItems((ObservableList<Reclamation>) rs.afficher());
    }    

    @FXML
    private void btn_deleteAction(ActionEvent event) {
        Reclamation u = tv_rec.getSelectionModel().getSelectedItem();
        if (u == null) {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Please select reclamation!",ButtonType.APPLY);
            alertA.show();
        }
        else {
            rs.supprimer(u);
            tv_rec.setItems((ObservableList<Reclamation>) rs.afficher());
            Alert alertA = new Alert(Alert.AlertType.NONE,"Reclamation deleted!",ButtonType.APPLY);
            alertA.show();
        }
    }

    @FXML
    private void btn_addAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reclamationAddForm.fxml"));
                ap.getChildren().setAll(pane);
    }

    @FXML
    private void search(KeyEvent event) {
        tv_rec.setItems((ObservableList<Reclamation>) rs.search(tf_search.getText(), "search"));

    }

    @FXML
    private void doubleclick(MouseEvent event) throws IOException {
        if(event.getButton().equals(MouseButton.PRIMARY)){
            if(event.getClickCount() == 2){
                Reclamation r = tv_rec.getSelectionModel().getSelectedItem();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/reclamationUpdateForm.fxml"));
                Parent root = loader.load();
                ReclamationUpdateFormController controller = (ReclamationUpdateFormController) loader.getController();
                controller.setReclaamation(r);
                ap.getChildren().setAll(root);
            }
        }
    }
    
}
