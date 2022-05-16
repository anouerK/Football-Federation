/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Sponsor;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ServiceSponsor;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author oumaymacherif
 */
public class SponsorController implements Initializable {

    @FXML
    private TableView<Sponsor> table;
    @FXML
    private TableColumn<Sponsor, Integer> col_id;
    @FXML
    private TableColumn<Sponsor, String> col_nom;
    @FXML
    private TableColumn<Sponsor, String> col_logo;
    @FXML
    private TextField logo;
    @FXML
    private TextField chercher;
    @FXML
    private TextField nom;
    @FXML
    private TextField id;
    ObservableList<Sponsor> List = FXCollections.observableArrayList();
    ObservableList<Sponsor> dataList;
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
    private void update(ActionEvent event) {
        ServiceSponsor sp = new ServiceSponsor();
        sp.modifier(new Sponsor(Integer.parseInt(id.getText()), nom.getText(), logo.getText()));
        JOptionPane.showMessageDialog(null, "Sponsor modifié !");
        refresh();
    }

    @FXML
    private void delete(ActionEvent event) {
        Connection cnx = MyDB.getInstance().getConnection();
        String sql = "DELETE  FROM sponsor where id= ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.setString(1, id.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "delete");
            table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
            table.refresh();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
                table.refresh();

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
    private void Sponsor(ActionEvent event) {
        try {
            Parent exercices_parent = FXMLLoader.load(getClass().getResource("/views/Sponsor.fxml"));
            Scene ex_section_scene = new Scene(exercices_parent);
            Stage second_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            second_stage.setScene(ex_section_scene);
            second_stage.show();

        } catch (IOException ex) {
            Logger.getLogger(SponsorController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(JoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        ServiceSponsor sp = new ServiceSponsor();
        sp.ajouter(new Sponsor(Integer.parseInt(id.getText()), nom.getText(), logo.getText()));
        JOptionPane.showMessageDialog(null, "Sponsor ajoutée !");
        refresh();

    }

    @FXML
    private void getSelected(MouseEvent event) {
        int index = table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        id.setText(col_id.getCellData(index).toString());
        nom.setText((String) col_nom.getCellData(index));
        logo.setText((String) col_logo.getCellData(index));

    }

    @FXML
    private void refresh() {
        try {
            Connection cnx = MyDB.getInstance().getConnection();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM sponsor");
            while (rs.next()) {
                List.add(new Sponsor(rs.getInt(1), rs.getString("nom_s"), rs.getString("logo_s")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(JoueurController.class.getName()).log(Level.SEVERE, null, ex);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom_s"));
        col_logo.setCellValueFactory(new PropertyValueFactory<>("logo_s"));

        table.setItems(List);
                table.refresh();

    }
    
   
       // private void noti() {
       // Notifications notificationBuilder = Notifications.create()
              //  .title("alert").text("succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
              //  .position(Pos.CENTER_LEFT)
               // .onAction(new EventHandler<ActionEvent>(){
               //     public void handle(ActionEvent event)
                  //  {
                  //      System.out.println("clicked ON");
                  //  }});
       // notificationBuilder.darkStyle();
       // notificationBuilder.show();
    //}

    @FXML
    private void update(MouseEvent event) {
    }

    @FXML
    private void delete(MouseEvent event) {
    }

    @FXML
    private void add(MouseEvent event) {
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
