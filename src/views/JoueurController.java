/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Joueur;
import entities.club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceJoueur;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author oumaymacherif
 */
public class JoueurController implements Initializable {

    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button add;
    @FXML
    private TableView<Joueur> table;
    @FXML
    private TableColumn<Joueur, Integer> col_cin;
    @FXML
    private TableColumn<Joueur, String> col_nom;
    @FXML
    private TableColumn<Joueur, String> col_prenom;
    @FXML
    private TableColumn<Joueur, Integer> col_age;
    @FXML
    private TableColumn<Joueur, Image> col_nbm;
    private TableColumn<Joueur, Integer> col_nba;
    @FXML
    private TextField age;
    @FXML
    private TextField chercher;
    @FXML
    private TextField nom;
    @FXML
    private TextField cin;
    @FXML
    private Button refresh;
    @FXML
    private TextField nba;
    @FXML
    private TextField nbm;
    @FXML
    private TextField prenom;
    ObservableList<Joueur> List = FXCollections.observableArrayList();
    ObservableList<Joueur> dataList;
    @FXML
    private ImageView rollback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         refresh();
    }

    @FXML
    private void update(MouseEvent event) {
        ServiceJoueur sp = new ServiceJoueur();
        sp.modifier(new Joueur(Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), age.getText(),nbm.getText(),nba.getText()));
        JOptionPane.showMessageDialog(null, "Joueur modifié !");
        refresh();
        
    
    }

    @FXML
    private void update(ActionEvent event) {
ServiceJoueur sp = new ServiceJoueur();
        sp.modifier(new Joueur(Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), age.getText(),nbm.getText(),nba.getText()));
        JOptionPane.showMessageDialog(null, "Joueur modifié !");
        refresh();
        
    
    }

    @FXML
    private void delete(MouseEvent event) {
         Connection cnx = MyDB.getInstance().getConnection();
        String sql = "DELETE  FROM joueur where cin= ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setString(1, cin.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "delete");
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            table.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        refresh();
    }

    @FXML
    private void delete(ActionEvent event) {
         Connection cnx = MyDB.getInstance().getConnection();
        String sql = "DELETE  FROM joueur where cin= ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setString(1, cin.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "delete");
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            table.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        refresh();
    }

    private void home(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Home.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
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
        }
    }

    private void Sponsor(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Sponsor.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
        }
    }

    private void Joueur(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Joueur.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
        }
    }

    @FXML
    private void add(MouseEvent event) {
        ServiceJoueur sp = new ServiceJoueur();
        sp.ajouter(new Joueur(Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), age.getText(),nbm.getText(),nba.getText()));
        JOptionPane.showMessageDialog(null, "Joueur ajoutée !");
        refresh();
    }

    @FXML
    private void add(ActionEvent event) {
        ServiceJoueur sp = new ServiceJoueur();
        sp.ajouter(new Joueur(Integer.parseInt(cin.getText()), nom.getText(), prenom.getText(), age.getText(),nbm.getText(),nba.getText()));
        JOptionPane.showMessageDialog(null, "Joueur ajoutée !");
        refresh();
    }

    @FXML
    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        cin.setText(col_cin.getCellData(index).toString());
        nom.setText((String) col_nom.getCellData(index));
        prenom.setText((String) col_prenom.getCellData(index));
        age.setText(col_age.getCellData(index).toString());
        nbm.setText(col_nbm.getCellData(index).toString());
        nba.setText(col_nba.getCellData(index).toString());

    }

    

    @FXML
    private void refresh() {
        
ServiceJoueur st =new ServiceJoueur();
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_age.setCellValueFactory(new PropertyValueFactory<>("age"));
     //   col_nba.setCellValueFactory(new PropertyValueFactory<>("nba"));
      col_nbm.setCellValueFactory(new PropertyValueFactory<>("img"));
     
        col_nbm.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Joueur, Image> cell = new TableCell<Joueur, Image>() {
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
        table.setItems(st.afficher());
   
    }

    @FXML
    private void pdf(ActionEvent event) {
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
