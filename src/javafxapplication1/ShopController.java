/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import entities.categories;
import entities.marques;
import entities.panier;
import entities.produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
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
public class ShopController implements Initializable {

    @FXML
    private VBox ChosenProdCard;
    @FXML
    private Label namalabel;
    @FXML
    private Label prixlabel;
    @FXML
    private ImageView prodimg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    
     private Stage stage;
    private Scene scene;
    private Parent root;
    
    
    private Listner listner;
    produit prod2;
    
      CategorieService psc = new CategorieService();
     MarqueService psm = new MarqueService();
     CommandeService pscom = new CommandeService();
     ProduitService psprod = new ProduitService();
    @FXML
    private ComboBox<String> cbcact;
    @FXML
    private ComboBox<String> cbprix;
    @FXML
    private ComboBox<String> cbcolor;
    
    String cat1="";
    String col1="";
    String prix1="";
    String taille1="";
    String nom1="";
    
    @FXML
    private Button filter;
    @FXML
    private Button refresh;
    @FXML
    private ImageView home;
    @FXML
    private ImageView cart;
    @FXML
    private ImageView delivery;
    @FXML
    private ImageView time;
    @FXML
    private ComboBox<String> taillecombo;
    @FXML
    private TextField quantitetxt;
    @FXML
    private Button Add;
    @FXML
    private TextField searchtxt;
    @FXML
    private Label countlbl;
    
 
    private void setchosenproduit(produit prod)
    {
        namalabel.setText(prod.getNom());
        prixlabel.setText(prod.getPrix() +"DT");
        Image image;
        try {
            image = new Image(new FileInputStream(prod.getImg()));
            prodimg.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Items_ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }

     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
         Image image1;
         
         
     
      String size = String.valueOf(JavaFXApplication1.pan.size());
      countlbl.setText(size);
      
         
      
            
            image1 = new Image("/AhmedImages/ic_cart.png");
            cart.setImage(image1);
            
            Image image2 = new Image("/AhmedImages/home2.png");
            home.setImage(image2);
            
            Image image3 = new Image("/AhmedImages/ic_delivery.png");
           delivery.setImage(image3);
           
           Image image4 = new Image("/AhmedImages/ic_stopwatch.png");
            time.setImage(image4);


                           ObservableList<String> listc22 =  FXCollections.observableArrayList();

        
         if(psprod.recuperer().size()>0)
         {
            setchosenproduit(psprod.recuperer().get(0));
            listc22.add(psprod.recuperer().get(0).getTaille());
                   listc22.add(psprod.recuperer().get(0).getTaille2());
                   taillecombo.setItems(listc22);
           prod2=psprod.recuperer().get(0);
            listner = new Listner() {
                @Override
                public void onClickListener(produit prod) {
                   taillecombo.getItems().clear();
                   setchosenproduit(prod);
                   listc22.add(prod.getTaille());
                   listc22.add(prod.getTaille2());
                   taillecombo.setItems(listc22);
                prod2=prod;
                }
            };
         }
        
       
        showshop(psprod.recuperer());
        //showshop(psprod.filter(2, col1, "shirt"));
         
         ObservableList<String> listc =  FXCollections.observableArrayList();
         ObservableList<String> listcol =  FXCollections.observableArrayList();
         ObservableList<String> listpr =  FXCollections.observableArrayList();

          for(categories c :  psc.recuperer())
        {
            listc.add(c.getTypec());
        }
          listc.add("Show All");
          
          listcol.add("noire");
          listcol.add("blanc");
          listcol.add("rouge");
          listcol.add("Show All");
          
          listpr.add("Croissant");
          listpr.add("Decroissant");


        cbcact.setItems(listc);
        cbcolor.setItems(listcol);
        cbprix.setItems(listpr);
          
          }  
    
    
    
    public void showshop(ObservableList<produit> list)
    {
       // System.out.println("listt" + list);
         int column=0;
        int row=1;
        int x=0;
        
        
          FXMLLoader fxmlloader2 = new  FXMLLoader();
                fxmlloader2.setLocation(getClass().getResource("Items_Shop.fxml"));
        try {
            AnchorPane pane2 = fxmlloader2.load();
        } catch (IOException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
                 
                Items_ShopController items2 = fxmlloader2.getController();
                items2.refresh();
                grid.getChildren().removeAll(grid.getChildren());
       
         try {
        for(produit prod : list)
        {
           
                FXMLLoader fxmlloader = new  FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("Items_Shop.fxml"));
                AnchorPane pane = fxmlloader.load();
                 
                Items_ShopController items = fxmlloader.getController();
                items.setData(prod,listner);
                
                if(column == 3 )
                {
                    column = 0;
                    row++;
                }
                
                grid.add(pane,column++,row);
                
                //width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);
                
                //height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                
                
                
                GridPane.setMargin(pane, new Insets(10));
    
        }
        } catch (IOException ex) {
                Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    

    @FXML
    private void selectcat(ActionEvent event) {
        cat1 =cbcact.getSelectionModel().getSelectedItem().toString();
        if(cat1.equals("Show All"))
        {//System.out.println("hhheefef");
            cat1="";
        }
    }

    @FXML
    private void selectprix(ActionEvent event) {
        prix1 =cbprix.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void selectcolor(ActionEvent event) {
        col1 =cbcolor.getSelectionModel().getSelectedItem().toString();
        
        if(col1.equals("Show All"))
        {
            col1="";
        }
    }

    @FXML
    private void filteraction(ActionEvent event) {
        if(cat1!="")
        {
            if(prix1=="Croissant")
            {
            showshop(psprod.filter(1, col1, cat1,nom1));
               // System.out.println("tes1" + col1 + cat1);
            //    System.out.println(psprod.filter(1, col1, cat1,nom1));
            }
            else if(prix1=="Decroissant")
            {
            showshop(psprod.filter(0, col1, cat1,nom1));
           //  System.out.println("tes2" + col1 + cat1);
           //  System.out.println(psprod.filter(0, col1, cat1,nom1));
            }
            else
            {
             showshop(psprod.filter(2, col1, cat1,nom1));
             // System.out.println("tes3" + col1 + cat1 );
               // System.out.println(psprod.filter(2, col1, cat1,nom1));

            }   
        }
        else
        {
            showshop(psprod.recuperer());
        }
        
    }

    @FXML
    private void refreshaction(ActionEvent event) {
          showshop(psprod.recuperer());
         
          
          
         

    }

    @FXML
    private void homeaction(MouseEvent event) throws IOException {
          
   Parent root = FXMLLoader.load(getClass().getResource("Home_turbo_devs.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    
    }

    @FXML
    private void Cartaction(MouseEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("ShopinCart.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
    }

    @FXML
    private void taillecomboaction(ActionEvent event) {
        taille1=taillecombo.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void AddToCart(ActionEvent event) {
        
        int a=0, b=0,c=0;
        if(taille1!="")
        {
            a=1;
        }
 if(quantitetxt.getText()!="" && quantitetxt.getText().matches("^[0-99]*$") && !quantitetxt.getText().startsWith("0"))
 {
     b=1;
 }
  if(a==1 && b==1)
  {
        int qte2 = Integer.valueOf(quantitetxt.getText());
        for(panier pan2: JavaFXApplication1.pan )
        {
            if(pan2.getProd().getId()==prod2.getId() && pan2.getTaille().equals(taille1))
            {
                qte2+=pan2.getQte();
                JavaFXApplication1.pan.remove(pan2);
                break;
            }
        }
        
        
        panier p = new panier(taille1,qte2,prod2.getPrix()*qte2,prod2);
        JavaFXApplication1.pan.add(p);
    ///    System.out.println(JavaFXApplication1.pan);
        String size = String.valueOf(JavaFXApplication1.pan.size());
        countlbl.setText(size);
  } 
  
  }

    @FXML
    private void searchtxtaction(ActionEvent event) {
       
        System.out.println(event.getTarget().toString());
        
        //System.out.println("here");
    }

    @FXML
    private void searchtxtaction2(KeyEvent event) {
      nom1= event.getText();
    //    System.out.println(nom1);
       showshop(psprod.filter(2, col1, cat1,nom1));
    }
    
    
    
}
