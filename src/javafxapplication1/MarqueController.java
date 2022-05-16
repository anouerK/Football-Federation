/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

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
import javafx.scene.input.MouseEvent;
import services.MarqueService;
import services.CategorieService;
import services.CommandeService;
import services.ProduitService;

import entities.categories;
import entities.marques;
import entities.produit;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class MarqueController implements Initializable {

        private Stage stage;
    private Scene scene;
    private Parent root;
    
    
      @FXML
    private Button Home;
    @FXML
    private Button btndeletemar;
    @FXML
    private TextField tfmar;
    @FXML
    private TableView<marques> tablemar;
    @FXML
    private TableColumn<marques, Integer> idmar;
    @FXML
    private TableColumn<marques, String> nommar;
    @FXML
    private Button btnupdatemar;
    @FXML
    private Button btninsertmar;
    @FXML
    private Label mareroor;
    
       int id_mar=0;
    String nom_mar="";
    
     MarqueService psm = new MarqueService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showmarques();
        tablemar.setEditable(true);
        nommar.setCellFactory(TextFieldTableCell.forTableColumn());
    }    
    
    
     @FXML
     public void switchtohome(ActionEvent event) throws IOException
    {
   Parent root = FXMLLoader.load(getClass().getResource("HomaAhmed.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }
     
      @FXML
     public void marclick()
    {
        ObservableList<marques> cat;
        cat=tablemar.getSelectionModel().getSelectedItems();
        id_mar=cat.get(0).getId();
        nom_mar=cat.get(0).getNomM();
         tfmar.setText(cat.get(0).getNomM());
               
      
    }
     
     
      public void showmarques()
    {
        System.out.println("here");
        
        
        idmar.setCellValueFactory(new PropertyValueFactory<marques,Integer>("id"));
        nommar.setCellValueFactory(new PropertyValueFactory<marques,String>("nomM"));
        
        tablemar.setItems(psm.recuperer());
    }
     
     

    @FXML
    private void deletemar(ActionEvent event) {
          if(id_mar!=0)
          {
        psm.supprimer(id_mar);
         showmarques();
         mareroor.setStyle("-fx-text-fill: #008000");
        mareroor.setText("Marque  Deleted");
          }
    }

   
/*
    @FXML
    private void updatemar(ActionEvent event) {
        
       
        
         int x=0;
        if(tfmar.getText()!="" && tfmar.getText().length()>=4)
        {
            for (marques c : psm.recuperer() )
            {
                if(c.getNomM().equals(tfmar.getText()))
                {
                    
                    x=1;
                }
                    
            }   
            if(x==0)
            {
       marques cat= new marques(id_mar,tfmar.getText());
        psm.modifier(cat);
        showmarques();
        mareroor.setStyle("-fx-text-fill: #008000");
        mareroor.setText("Marque  Updated");
       
            }
            else
            {
                mareroor.setStyle("-fx-text-fill: #FF0000");
               mareroor.setText("Marque Already Exist"); 
            }  
        }
        else
        {
            mareroor.setStyle("-fx-text-fill: #FF0000");
            mareroor.setText("Marque non valide");
        }
    
        
        
        
        
    }
*/
    @FXML
    private void insertmar(ActionEvent event) {
        
        int x=0;
        if(tfmar.getText()!="" && tfmar.getText().length()>=4)
        {
            for (marques c : psm.recuperer() )
            {
                if(c.getNomM().equals(tfmar.getText()))
                {
                    
                    x=1;
                }
                    
            }   
            if(x==0)
            {
        marques c1 = new marques(tfmar.getText());
        psm.ajouter(c1);
        mareroor.setStyle("-fx-text-fill: #008000");
        mareroor.setText("Marque  Added");
         showmarques();
            }
            else
            {
                mareroor.setStyle("-fx-text-fill: #FF0000");
               mareroor.setText("Marque Already Exist"); 
            }  
        }
        else
        {
            mareroor.setStyle("-fx-text-fill: #FF0000");
            mareroor.setText("Marque non valide");
        }
    }

    @FXML
    public void marqueedit(TableColumn.CellEditEvent<marques, String> event) {
        marques cat =tablemar.getSelectionModel().getSelectedItem();
        int x =0;
        cat.setNomM(event.getNewValue());
        if(cat.getNomM()!="" && cat.getNomM().length()>=4)
        {
            
             for (marques c : psm.recuperer() )
            {
                if(c.getNomM().equals(cat.getNomM()))
                {
                    
                    x=1;
                }
                    
            }   
             if(x==0)
             {
        marques cat2= new marques(cat.getId(),cat.getNomM());
        psm.modifier(cat2);
          mareroor.setStyle("-fx-text-fill: #008000");
        mareroor.setText("Marque  Updated");
         showmarques();
             }
              else
            {
                  mareroor.setStyle("-fx-text-fill: #FF0000");
               mareroor.setText("Marque Already Exist"); 
            }  
        }
        else
        {
              mareroor.setStyle("-fx-text-fill: #FF0000");
            mareroor.setText("Marque non valide");
        }
    
    }
    
}
