/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.DocumentException;
import entities.Tournoi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ServiceNotification;
import services.ServicePdf;
import services.ServiceTournoi;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class AffichTournoiController implements Initializable {

    @FXML
    private TableColumn<Tournoi, Integer> id_t;
    @FXML
    private TableColumn<Tournoi, String> id_nom;
    @FXML
    private TableColumn<Tournoi, String> id_dated;
    @FXML
    private TableColumn<Tournoi, String> id_datef;
    @FXML
    private TableColumn<Tournoi, String> id_typet;
    @FXML
    private TableColumn<Tournoi, Integer> id_nbrc;
    @FXML
    private TableColumn<Tournoi, Image> id_img;
    @FXML
    private TableView<Tournoi> tableTournoi;
    @FXML
    private ImageView id_log;
    @FXML
    private Button btx;
    private DatePicker cal;
    @FXML
    private Button pdf;
    @FXML
    private PieChart pie;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceTournoi st=new ServiceTournoi();
         //  Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
        id_t.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("id"));
        id_nom.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("nomt"));
        id_dated.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("dated"));
        id_datef.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("datef"));
        id_typet.setCellValueFactory(new PropertyValueFactory<Tournoi,String>("typet"));
        id_nbrc.setCellValueFactory(new PropertyValueFactory<Tournoi,Integer>("nbrc"));
       // id_img.setCellValueFactory(new PropertyValueFactory<Tournoi,Image>("img"));
         id_img.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Tournoi, Image> cell = new TableCell<Tournoi, Image>() {
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
        
        id_img.setCellValueFactory(new PropertyValueFactory<Tournoi, Image>("img"));
        // id_img.setCellValueFactory(new ImageView (new Image (this.getClass().getResourceAsStream(id_img.getText()))));
        tableTournoi.setItems(st.afficher());
         tableTournoi.setEditable(true);
           id_nom.setCellFactory(TextFieldTableCell.forTableColumn());
            id_dated.setCellFactory(TextFieldTableCell.forTableColumn());
             id_datef.setCellFactory(TextFieldTableCell.forTableColumn());
              id_typet.setCellFactory(TextFieldTableCell.forTableColumn());
            //  id_log.setImage(product);
            //   id_nbrc.setCellFactory(TextFieldTableCell.forTableColumn());
            stats_users();
    }    

    @FXML
    private void ajoutT(ActionEvent event) {
         Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("AddTournoi.fxml"));
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
    private void supp(TableColumn.CellEditEvent<Tournoi, Integer> event) {
         Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
//product.setId(event.getNewValue());
    ServiceTournoi st=new ServiceTournoi();
    //Tournoi t=new Tournoi (product);
  
     Alert alert = new Alert(AlertType.CONFIRMATION);
alert.setTitle("Confirmation Dialog");
alert.setHeaderText("Look, a Confirmation Dialog");
alert.setContentText("Are you ok to delete this tournament id ="+product.getId() +"?");

Optional<ButtonType> result = alert.showAndWait();
if (result.get() == ButtonType.OK){
    // ... user chose OK
      st.supprimer(product.getId());
        tableTournoi.setItems(st.afficher());
} else {
    // ... user chose CANCEL or closed the dialog
}
    }
    @FXML
    private void change(TableColumn.CellEditEvent<Tournoi, String> event) {
         Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
product.setNomt(event.getNewValue());
    ServiceTournoi st=new ServiceTournoi();
    Tournoi t=new Tournoi (product);
    st.modifier(t);
    }
    @FXML
    private void chandedated(TableColumn.CellEditEvent<Tournoi, String> event) {
                 Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
                   LocalDate format= cal.getValue();
              
          String dated= format.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
           
product.setDated(dated);
    ServiceTournoi st=new ServiceTournoi();
    Tournoi t=new Tournoi (product);
    st.modifier(t);
    }
    @FXML
    private void changedatef(TableColumn.CellEditEvent<Tournoi, String> event) {
                 Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
product.setDatef(event.getNewValue());
    ServiceTournoi st=new ServiceTournoi();
    Tournoi t=new Tournoi (product);
    st.modifier(t);
    }
    @FXML
    private void changetype(TableColumn.CellEditEvent<Tournoi, String> event) {
                 Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
product.setTypet(event.getNewValue());
    ServiceTournoi st=new ServiceTournoi();
    Tournoi t=new Tournoi (product);
    st.modifier(t);
    }
    @FXML
    private void changenbrc(TableColumn.CellEditEvent<Tournoi, Integer> event) {
                 Tournoi product=tableTournoi.getSelectionModel().getSelectedItem();
product.setNbrc(event.getNewValue());
    ServiceTournoi st=new ServiceTournoi();
    Tournoi t=new Tournoi (product);
    st.modifier(t);
    }

  

    @FXML
    private void afichimg(MouseEvent event) throws FileNotFoundException {
         //   FileInputStream ifp= new FileInputStream(file);
      
       /*      String url1 = "http://127.0.0.1/FirstProject/public/uploads/";
             
     ServiceTournoi st=new ServiceTournoi();
            
    
  Tournoi person = tableTournoi.getSelectionModel().getSelectedItem();
     Image image = new Image(new FileInputStream(url1+person.getLogo()));
   //    ImageView empiphoto= new ImageView (new Image (this.getClass().getResourceAsStream(url1+person.getLogo())));
     
        
        
    // String decodedString = new String(base64.decode(person.getImage().getBytes()));
   try{  byte[] bytes = person.getLogo().getBytes("UTF-8");
     String s2 = new String(bytes, "UTF-8");
   System.out.println(s2);
    id_log.setImage(image);
   }
   catch(Exception e){}
  //id_log.setImage(image);
  
   
    
  //LBshow.setText("aa");*/
 
    }

    

    @FXML
    private void PDF(ActionEvent event)  throws FileNotFoundException, DocumentException,Exception{
        ServicePdf st=new ServicePdf();
        ServiceNotification Notification = new ServiceNotification();
        Tournoi t= new Tournoi();
         boolean test = Notification.check_Box("", "Vous etes sur le point de generer un fichier pdf\n Vous voulez continuez ?");
        if (test) {
       
             
             
        st.liste_admins(t);
      //   Notification.Notification("Pdf Generé ", "Verifier votre repertoire");
    }

    }

     private void stats_users() {
        ServiceTournoi su = new ServiceTournoi();
        int admin = su.nbAdmins();
       
        int etudiants = su.nbEudiants();
        int all = etudiants + admin;
        System.out.println("number"+admin);
        ObservableList<PieChart.Data> list_stat = FXCollections.observableArrayList(
                new PieChart.Data("type club: " + (admin * 100) / all + "%", admin),
               
                new PieChart.Data("type eliminatoire:" + (etudiants * 100) / all + "%", etudiants)
        );
        pie.setData(list_stat);
        pie.setTitle(" Tournois Statistique ");

    }

   

    @FXML
    private void backMenu(ActionEvent event) {
          Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/javafxapplication1/MainBack.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
    }

   
    
    

    
}
