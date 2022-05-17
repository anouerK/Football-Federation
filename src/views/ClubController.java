/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Badge;
import entities.club;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    private Label logo;
    @FXML
    private TextField chercher;
    @FXML
    private TextField nom;
    @FXML
    private TextField descr;
    @FXML
    private Button refresh;
    @FXML
    private AnchorPane rollback;
    @FXML
    private TextField president;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshx();
    }    
    public void refreshx()
    {
         ServiceClub st=new ServiceClub();
table.setEditable(true);
col_nom.setCellFactory(TextFieldTableCell.forTableColumn());
col_sponsor.setCellFactory(TextFieldTableCell.forTableColumn());
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

   



   

 

    @FXML
    private void onupload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);

         File f = fileChooser.showOpenDialog(new Stage());

         logo.setText(f.getAbsoluteFile().toURI().toString());
    }
     public  String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

    @FXML
    private void ondelete(ActionEvent event) {
          club b = table.getSelectionModel().getSelectedItem();
           ServiceClub  sc = new ServiceClub();
           sc.supprimer(b);
           refreshx();
           
          
    }

    @FXML
    private void onadd(ActionEvent event) {
        
             if(nom.getText().isEmpty() || descr.getText().isEmpty() || president.getText().isEmpty() || logo.getText().isEmpty())
             {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
              String nameImage;
          

            nameImage = generateRandomPassword(10) + ".png";
              Image img3 = new Image(logo.getText());
          
            File file = new File(Badge.url_upload2  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        club cl = new club(nom.getText(),descr.getText(),president.getText(),nameImage);
        ServiceClub  sc = new ServiceClub();
        sc.ajouter(cl);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("success");
alert.setHeaderText("Added");
alert.setContentText("added !");

alert.showAndWait();
        refreshx();
                
                }
        
        
    }

    @FXML
    private void oneditname(TableColumn.CellEditEvent<club, String> event) {
        club b = table.getSelectionModel().getSelectedItem();
b.setNom(event.getNewValue());
ServiceClub  sc = new ServiceClub();
sc.modifier(b);
refreshx();
    }

    @FXML
    private void oneditdescr(TableColumn.CellEditEvent<club, String> event) {
        club b = table.getSelectionModel().getSelectedItem();
b.setDescr(event.getNewValue());
ServiceClub  sc = new ServiceClub();
sc.modifier(b);
refreshx();
    }


   

   

    

    
   

    
}
