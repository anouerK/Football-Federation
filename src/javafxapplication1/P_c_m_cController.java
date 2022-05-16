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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import entities.categories;
import entities.marques;
import entities.produit;
import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import services.CategorieService;
import services.CommandeService;
import services.MarqueService;
import services.ProduitService;

        

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class P_c_m_cController  implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
  
    @FXML
    private TextField tfcat;
    @FXML
    private TableView<categories> tablecat;
    @FXML
    private TableColumn<categories, Integer> idcat;
    @FXML
    private TableColumn<categories, String> typecat;
    @FXML
    private Label cateroor;    
    
    @FXML
    private Button btndeletecat;
   
    
    @FXML
    private Button btninsertcat;
    @FXML
    private Button Homebtn;
    
    
    
    
    
    
    
    
    
    
    
     CategorieService psc = new CategorieService();
     MarqueService psm = new MarqueService();
     CommandeService pscom = new CommandeService();
     ProduitService psprod = new ProduitService();
    int id_cat=0;
    String type_cat="";
    
     int id_mar=0;
    String nom_mar="";
   
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tablecat.setEditable(true);
        typecat.setCellFactory(TextFieldTableCell.forTableColumn());
        showcategories();
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
    public void catclick()
    {
        ObservableList<categories> cat;
        cat=tablecat.getSelectionModel().getSelectedItems();
        id_cat=cat.get(0).getId();
        type_cat=cat.get(0).getTypec();
        // tfcat.setText(cat.get(0).getTypec());
               
      
    }
     
    
    
    public void showcategories()
    {
        System.out.println("here");
        
        
        idcat.setCellValueFactory(new PropertyValueFactory<categories,Integer>("id"));
        typecat.setCellValueFactory(new PropertyValueFactory<categories,String>("typec"));
        
        tablecat.setItems(psc.recuperer());
    }
   

    @FXML
    private void deletecat(ActionEvent event) {
        if(id_cat!=0)
        {
        psc.supprimer(id_cat);
         showcategories();
          cateroor.setStyle("-fx-text-fill: #008000");
        cateroor.setText("Categorie Deleted");
        }
        }
        
/*
    @FXML
    private void updatecat(ActionEvent event) {
       
         
         int x=0;
        if(tfcat.getText()!="" && tfcat.getText().length()>=4)
        {
            for (categories c : psc.recuperer() )
            {
                if(c.getTypec().equals(tfcat.getText()))
                {
                    
                    x=1;
                }
                    
            }   
            if(x==0)
            {
        categories cat= new categories(id_cat,tfcat.getText());
        psc.modifier(cat);
         showcategories();
        cateroor.setStyle("-fx-text-fill: #008000");
        cateroor.setText("Categorie  Updated");
         
            }
            else
            {
                cateroor.setStyle("-fx-text-fill: #FF0000");
               cateroor.setText("Categorie Already Exist"); 
            }  
        }
        else
        {
              cateroor.setStyle("-fx-text-fill: #FF0000");
            cateroor.setText("Categorie non valide");
        }
    }*/

    @FXML
    private void insertcat(ActionEvent event) {
        int x=0;
        if(tfcat.getText()!="" && tfcat.getText().length()>=4)
        {
            for (categories c : psc.recuperer() )
            {
                if(c.getTypec().equals(tfcat.getText()))
                {
                    
                    x=1;
                }
                    
            }   
            if(x==0)
            {
        categories c1 = new categories(tfcat.getText());
        psc.ajouter(c1);
        cateroor.setStyle("-fx-text-fill: #008000");
        cateroor.setText("Categorie  Added");
         showcategories();
            }
            else
            {
                  cateroor.setStyle("-fx-text-fill: #FF0000");
               cateroor.setText("Categorie Already Exist"); 
            }  
        }
        else
        {
              cateroor.setStyle("-fx-text-fill: #FF0000");
            cateroor.setText("Categorie non valide");
        }
        
        }
@FXML
    public void catedit(TableColumn.CellEditEvent<categories, String> event) {
        categories cat =tablecat.getSelectionModel().getSelectedItem();
        int x =0;
        cat.setTypec( event.getNewValue());
        if(cat.getTypec()!="" && cat.getTypec().length()>=4)
        {
            
             for (categories c : psc.recuperer() )
            {
                if(c.getTypec().equals(cat.getTypec()))
                {
                    
                    x=1;
                }
                    
            }   
             if(x==0)
             {
        categories cat2= new categories(cat.getId(),cat.getTypec());
        psc.modifier(cat2);
          cateroor.setStyle("-fx-text-fill: #008000");
        cateroor.setText("Categorie  Updated");
         showcategories();
             }
              else
            {
                  cateroor.setStyle("-fx-text-fill: #FF0000");
               cateroor.setText("Categorie Already Exist"); 
            }  
        }
        else
        {
              cateroor.setStyle("-fx-text-fill: #FF0000");
            cateroor.setText("Categorie non valide");
        }
        
        }  

   
       
    
    
    
    
    
    
    
    
    
    
    

    
    
    
}
