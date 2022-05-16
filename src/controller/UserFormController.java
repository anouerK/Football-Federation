/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class UserFormController implements Initializable {

                ServiceUser us = new ServiceUser();

    @FXML
    private TableView<User> tv_user;
    @FXML
    private TableColumn<User, String> tc_id;
    @FXML
    private TableColumn<User, String> tc_username;
    @FXML
    private TableColumn<User, String> tc_password;
    @FXML
    private TableColumn<User, String> tc_role;
    @FXML
    private TableColumn<User, String> tc_email;
    @FXML
    private Button btn_delete;
    @FXML
    private AnchorPane ap;
    @FXML
    private TextField tf_search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tc_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                    tc_email.setCellValueFactory(new PropertyValueFactory<>("email"));
                    tc_password.setCellValueFactory(new PropertyValueFactory<>("mdp"));
                    tc_username.setCellValueFactory(new PropertyValueFactory<>("username"));
					tc_role.setCellValueFactory(new PropertyValueFactory<>("role"));
                    tv_user.setItems((ObservableList<User>) us.recuperer());
    }    

    @FXML
    private void btn_deleteAction(ActionEvent event) {
        User u = tv_user.getSelectionModel().getSelectedItem();
        if (u == null) {
            Alert alertA = new Alert(Alert.AlertType.NONE,"Please select user!",ButtonType.APPLY);
            alertA.show();
        }
        else {
            us.supprimer(u);
            tv_user.setItems((ObservableList<User>) us.recuperer());
            Alert alertA = new Alert(Alert.AlertType.NONE,"User deleted!",ButtonType.APPLY);
            alertA.show();
        }
    }

    

    @FXML
    private void search(KeyEvent event) {
        tv_user.setItems((ObservableList<User>) us.search(tf_search.getText(), "search"));

    }
    
}
