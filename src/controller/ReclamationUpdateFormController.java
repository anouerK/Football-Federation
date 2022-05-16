/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReclamationUpdateFormController implements Initializable {

    @FXML
    private TextField tf_object;
    @FXML
    private TextArea tf_desc;
    @FXML
    private Button btn;
    @FXML
    private Button btn1;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    Reclamation reclamation = new Reclamation();
    
    void setReclaamation(Reclamation r) {
        reclamation = r;
        tf_desc.setText(r.getDesc());
        tf_object.setText(r.getObjet());
    }

    public ReclamationUpdateFormController() {
    }
    
    ReclamationService rs = new ReclamationService();

    @FXML
    private void btnAction(ActionEvent event) throws IOException {
        if(!tf_desc.getText().equals("") && !tf_object.getText().equals("")) {
            Reclamation r = new Reclamation(reclamation.getId(), tf_object.getText(), tf_desc.getText(), "1");
            rs.modifier(r);
            Alert alertA = new Alert(Alert.AlertType.NONE,"Reclamation added!",ButtonType.APPLY);
            alertA.show();
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reclamationForm.fxml"));
                ap.getChildren().setAll(pane);
        }
        else {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Empty field!",ButtonType.APPLY);
            alertA.show();
        }
        
    }

    @FXML
    private void bacckAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/gui/reclamationForm.fxml"));
                ap.getChildren().setAll(pane);
    }
    
}
