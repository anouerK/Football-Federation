/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package turbooo_devs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class MarqueController implements Initializable {

    @FXML
    private Button btndeletemar;
    @FXML
    private Button btnupdatemar;
    @FXML
    private Button btninsertmar;
    @FXML
    private Label mareroor;
    @FXML
    private Button Home;
    @FXML
    private TableColumn<?, ?> idB;
    @FXML
    private TableColumn<?, ?> nomB;
    @FXML
    private TableColumn<?, ?> nbB;
    @FXML
    private TableColumn<?, ?> logoB;
    @FXML
    private TextField tfnbB;
    @FXML
    private TextField tfnomB;
    @FXML
    private TableView<?> tablebadge;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void deletemar(ActionEvent event) {
    }

    @FXML
    private void updatemar(ActionEvent event) {
    }

    @FXML
    private void insertmar(ActionEvent event) {
    }

    @FXML
    private void switchtohome(ActionEvent event) {
    }
    
}
