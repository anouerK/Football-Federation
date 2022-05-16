/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.Badge;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import entities.categories;
import entities.marques;
import entities.produit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import services.CategorieService;
import services.CommandeService;
import services.MarqueService;
import services.ProduitService;

import org.controlsfx.control.*;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author Lord
 */
public class ProduitController implements Initializable {
    
      private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfdesc;
    @FXML
    private ComboBox<String> cbcat;
    @FXML
    private ComboBox<String> cbmar;
    @FXML
    private ComboBox<String> cbcol;
    @FXML
    private ComboBox<String> cbt1;
    @FXML
    private ComboBox<String> cbt2;
    @FXML
    private TextField tfqte;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfimg;
    @FXML
    private Button btnupload;
    @FXML
    private Button btninsert;
    @FXML
    private Button btnupdate;
    @FXML
    private Button  Homebtn;
    
    @FXML
    private Button btndelete;
    @FXML
    private TableView<produit> tableprod;
 

    
     CategorieService psc = new CategorieService();
     MarqueService psm = new MarqueService();
     CommandeService pscom = new CommandeService();
     ProduitService psprod = new ProduitService();
    
     int id1=0;
     String cat1="";
     String mar1="";
     String t1="";
     String t2="";
     String col1="";
     
      File file;
      
    @FXML
    private Label nomer;
    @FXML
    private Label descer;
    @FXML
    private Label cater;
    @FXML
    private Label marer;
    @FXML
    private Label coler;
    @FXML
    private Label t1er;
    @FXML
    private Label t2er;
    @FXML
    private Label qter;
    @FXML
    private Label prixer;
    @FXML
    private Label imger;
    @FXML
    private ImageView imgview;
    @FXML
    private TableColumn<produit, Integer> idprod;
    @FXML
    private TableColumn<produit, String> nomprod;
    @FXML
    private TableColumn<produit, categories> categorieprod;
    @FXML
    private TableColumn<produit, marques> marqueprod;
    @FXML
    private TableColumn<produit, String> taille1prod;
    @FXML
    private TableColumn<produit, String> taille2prod;
    @FXML
    private TableColumn<produit, String> couleurprod;
    @FXML
    private TableColumn<produit, Integer> quantiteprod;
    @FXML
    private TableColumn<produit,Float> prixprod;
    @FXML
    private Label pppp;
    
    String cat2;
    String mar2;
    String nom2;
    String t12;
    String t22;
    String col2;
     int qte2;
     float prix2;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      // TODO
   
      
      tableprod.setEditable(true);
      nomprod.setCellFactory(TextFieldTableCell.forTableColumn());
      quantiteprod.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      prixprod.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
      
      for(produit prod : psprod.recuperer())
      {
          if(prod.getQte()==0)
          {
     
      
      TrayNotification tray  = new TrayNotification();
      AnimationType type = AnimationType.POPUP;
      tray.setAnimationType(type);
      tray.setTitle("Produit en rupture de stock");
      tray.setMessage("Produit  " + prod.getId() + " avec le nom: "+ prod.getNom()+ "  quantite egale a 0.");
      tray.setNotificationType(NotificationType.WARNING);
      Image image = new Image(prod.getImg());
      tray.setImage(image);
      tray.showAndDismiss(Duration.millis(100000));
      
      
     
          }
          
      }
      
      
      
     
      
      
      
     /* .title("Produit en rupture de stock")
      .text("Produit numero: est en quantite egale a 0."  )
      .hideAfter(Duration.seconds(5))
      .position(Pos.BOTTOM_RIGHT);
       notificationBuilder.darkStyle();
       notificationBuilder.show();*/
      
      
      
        ObservableList<String> listc =  FXCollections.observableArrayList();
        ObservableList<String> listm =  FXCollections.observableArrayList();
        
        ObservableList<String> listt1 =  FXCollections.observableArrayList("s","m");
        ObservableList<String> listt2 =  FXCollections.observableArrayList("l","xs");
        ObservableList<String> listcol =  FXCollections.observableArrayList("noire","blanc","rouge");
        
        for(categories c :  psc.recuperer())
        {
            listc.add(c.getTypec());
        }
        
        cbcat.setItems(listc);
        
        
        for(marques m :  psm.recuperer())
        {
            listm.add(m.getNomM());
        }
        

        
        cbmar.setItems(listm);
        cbt1.setItems(listt1);
        cbt2.setItems(listt2);
        cbcol.setItems(listcol);
        
   showProduit ();   
    }    
    
    public void showProduit()
    {
        idprod.setCellValueFactory(new PropertyValueFactory<produit,Integer>("id"));
        nomprod.setCellValueFactory(new PropertyValueFactory<produit,String>("nom"));
        categorieprod.setCellValueFactory(new PropertyValueFactory<produit,categories>("cat") );
        
        marqueprod.setCellValueFactory(new PropertyValueFactory<produit,marques>("mar") );
        taille1prod.setCellValueFactory(new PropertyValueFactory<produit,String>("taille"));
       taille2prod.setCellValueFactory(new PropertyValueFactory<produit,String>("taille2"));
       couleurprod.setCellValueFactory(new PropertyValueFactory<produit,String>("couleur"));
      quantiteprod.setCellValueFactory(new PropertyValueFactory<produit,Integer>("qte"));
      prixprod.setCellValueFactory(new PropertyValueFactory<produit,Float>("prix"));

        tableprod.setItems(psprod.recuperer());
        
        
      
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
    private void selectcat(ActionEvent event) {
        cat1 =cbcat.getSelectionModel().getSelectedItem().toString();
       
    }

    @FXML
    private void selectmar(ActionEvent event) {
         mar1 =cbmar.getSelectionModel().getSelectedItem().toString();
       
    }

    @FXML
    private void selectcol(ActionEvent event) {
        col1  =cbcol.getSelectionModel().getSelectedItem().toString();
       
    }

    @FXML
    private void selectt1(ActionEvent event) {
         t1 =cbt1.getSelectionModel().getSelectedItem().toString();
       
    }

    @FXML
    private void selectt2(ActionEvent event) {
         t2 =cbt2.getSelectionModel().getSelectedItem().toString();
       
        
    }

    @FXML
    private void uploadimg(ActionEvent event) throws FileNotFoundException {
        /*
         FileChooser fileChooser = new FileChooser();

         File f = fileChooser.showOpenDialog(new Stage());*/
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


         tfimg.setText(f.getAbsoluteFile().toURI().toString());
       
      
       
    }

    @FXML
    private void insertproduit(ActionEvent event) {
        int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,k=0,o=0;
        if(tfnom.getText()!="" && tfnom.getText().length()>=4)
        {
        a=1; 
            
        }  
        if(tfdesc.getText()!="" && tfdesc.getText().length()>=4)
        {
        b=1;    
        }   
        if(tfqte.getText()!="" && tfqte.getText().matches("^[0-99]*$") && !tfqte.getText().startsWith("0"))
        {
            c=1;
        }   
        if(tfprix.getText()!="" && tfprix.getText().matches("^[0-99]*$") && !tfprix.getText().startsWith("0"))
        {
        d=1;
        }
        if(cat1!="")
        {
            e=1;
            
        }
        if(mar1!="")
        {
            f=1;
        }
        if(t1!="")
        {
            g=1;
        }
        if(t2!="")
        {
            h=1;
        }
        if(col1!="")
        {
            k=1;
        }
      
        if(tfimg.getText()!="")
        {
            o=1;
         
        }
    
        if(a==0)
        {  
            System.out.println("aaaa");
        nomer.setStyle("-fx-text-fill: #FF0000");
        nomer.setText("Nom no valide");
        }
        else
         nomer.setText("");
         if(b==0)
        {  
        descer.setStyle("-fx-text-fill: #FF0000");
        descer.setText("Description no valide");
        }
         else
          descer.setText("");   
          if(c==0)
        {  
        qter.setStyle("-fx-text-fill: #FF0000");
        qter.setText("Quantite no valide");
        }
          else
          qter.setText("");    
          if(d==0)
        {  
        prixer.setStyle("-fx-text-fill: #FF0000");
        prixer.setText("Prix no valide");
        } 
          else
          prixer.setText("");
           if(e==0)
        {  
        cater.setStyle("-fx-text-fill: #FF0000");
        cater.setText("Categorie no valide");
        }
           else
         cater.setText("");
            if(f==0)
        {  
        marer.setStyle("-fx-text-fill: #FF0000");
        marer.setText("Marque no valide");
        }
            else
           marer.setText(""); 
             if(g==0)
        {  
        t1er.setStyle("-fx-text-fill: #FF0000");
        t1er.setText("Taille no valide");
        }
             else
        t1er.setText("");
              if(h==0)
        {  
        t2er.setStyle("-fx-text-fill: #FF0000");
        t2er.setText("Taille no valide");
        }
              else
               t2er.setText("");    
               if(k==0)
        {  
        coler.setStyle("-fx-text-fill: #FF0000");
        coler.setText("Coueleur no valide");
        }
               else
               coler.setText("");
               
                if(o==0)
        {  
        imger.setStyle("-fx-text-fill: #FF0000");
        imger.setText("Image no valide");
        }
                else
                imger.setText("");    
        
        if(a==1 && b==1 && c==1 && d==1 && e==1 && f==1 && g==1 && h==1 && k==1 && o==1)
        {

    float prixx = Float.valueOf(tfprix.getText());
    int qtt = Integer.valueOf(tfqte.getText());
    categories cats=new categories();
    marques marqs=new marques();
    for (categories cc : psc.recuperer())
    {
        if(cat1.equals(cc.getTypec()))
                {
                    cats =new categories(cc.getId(),cc.getTypec());
                }
                
    }
    
    for (marques m : psm.recuperer())
    {
        if(mar1.equals(m.getNomM()))
                {
                    marqs =new marques(m.getId(),m.getNomM());
                }
                
    }
   
     String nameImage;
          

            nameImage = generateRandomPassword(10) + ".png";
              Image img3 = new Image(tfimg.getText());
          
            File file = new File(Badge.url_upload  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    
        produit prod = new produit(tfnom.getText(),t1,t2,tfdesc.getText(),nameImage,col1,prixx,qtt,cats,marqs);
        psprod.ajouter(prod);
        
        showProduit();
        pppp.setText("Produit Added");
        }
        
        }

    @FXML
    private void updateproduit(ActionEvent event) {
          int a=0,b=0,c=0,d=0,e=0,f=0,g=0,h=0,k=0,o=0;
        if(tfnom.getText()!="" && tfnom.getText().length()>=4)
        {
        a=1; 
            
        }  
        if(tfdesc.getText()!="" && tfdesc.getText().length()>=4)
        {
        b=1;    
        }   
        if(tfqte.getText()!="" && tfqte.getText().matches("^[0-99]*$") && !tfqte.getText().startsWith("0"))
        {
            c=1;
        }   
        if(tfprix.getText()!="" && tfprix.getText().matches("^[0-99]*$") && !tfprix.getText().startsWith("0"))
        {
        d=1;
        }
        if(cat1!="")
        {
            e=1;
            
        }
        if(mar1!="")
        {
            f=1;
        }
        if(t1!="")
        {
            g=1;
        }
        if(t2!="")
        {
            h=1;
        }
        if(col1!="")
        {
            k=1;
        }
      
        if(tfimg.getText()!="")
        {
            o=1;
         
        }
    
        if(a==0)
        {  
            System.out.println("aaaa");
        nomer.setStyle("-fx-text-fill: #FF0000");
        nomer.setText("Nom no valide");
        }
        else
         nomer.setText("");
         if(b==0)
        {  
        descer.setStyle("-fx-text-fill: #FF0000");
        descer.setText("Description no valide");
        }
         else
          descer.setText("");   
          if(c==0)
        {  
        qter.setStyle("-fx-text-fill: #FF0000");
        qter.setText("Quantite no valide");
        }
          else
          qter.setText("");    
          if(d==0)
        {  
        prixer.setStyle("-fx-text-fill: #FF0000");
        prixer.setText("Prix no valide");
        } 
          else
          prixer.setText("");
           if(e==0)
        {  
        cater.setStyle("-fx-text-fill: #FF0000");
        cater.setText("Categorie no valide");
        }
           else
         cater.setText("");
            if(f==0)
        {  
        marer.setStyle("-fx-text-fill: #FF0000");
        marer.setText("Marque no valide");
        }
            else
           marer.setText(""); 
             if(g==0)
        {  
        t1er.setStyle("-fx-text-fill: #FF0000");
        t1er.setText("Taille no valide");
        }
             else
        t1er.setText("");
              if(h==0)
        {  
        t2er.setStyle("-fx-text-fill: #FF0000");
        t2er.setText("Taille no valide");
        }
              else
               t2er.setText("");    
               if(k==0)
        {  
        coler.setStyle("-fx-text-fill: #FF0000");
        coler.setText("Coueleur no valide");
        }
               else
               coler.setText("");
               
                if(o==0)
        {  
        imger.setStyle("-fx-text-fill: #FF0000");
        imger.setText("Image no valide");
        }
                else
                imger.setText("");    
        
        if(a==1 && b==1 && c==1 && d==1 && e==1 && f==1 && g==1 && h==1 && k==1 && o==1)
        {

    float prixx = Float.valueOf(tfprix.getText());
    int qtt = Integer.valueOf(tfqte.getText());
    categories cats=new categories();
    marques marqs=new marques();
    for (categories cc : psc.recuperer())
    {
        if(cat1.equals(cc.getTypec()))
                {
                    cats =new categories(cc.getId(),cc.getTypec());
                }
                
    }
    
    for (marques m : psm.recuperer())
    {
        if(mar1.equals(m.getNomM()))
                {
                    marqs =new marques(m.getId(),m.getNomM());
                }
                
    }
       String image = tfimg.getText();
       String nameImage=tfimg.getText();
       if(!image.contains("\\"))
       {
         image = image.replace("\\", "\\\\");
       }
       if(tfimg.getText().contains("file:/")){
        //   tfimg.setText( generateRandomPassword(10) + ".png");
        
        nameImage = generateRandomPassword(10) + ".png";
              Image img3 = new Image(tfimg.getText());
          
            File file = new File(Badge.url_upload  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       }
        produit prod = new produit(id1,tfnom.getText(),t1,t2,tfdesc.getText(),nameImage,col1,"2000-05-13",prixx,qtt,cats,marqs);
        psprod.modifier(prod);
        pppp.setText("Produit Updated");
        showProduit();
        }
    }

    @FXML
    private void deleteproduit(ActionEvent event) {
        if(id1!=0)
        {
        psprod.supprimer(id1);
         showProduit();
         pppp.setText("Produit Deleted");
        }
        }
        

    @FXML
    private void prodclick(MouseEvent event) throws FileNotFoundException {
         ObservableList<produit> prod;
         prod=tableprod.getSelectionModel().getSelectedItems();
         nom2=prod.get(0).getNom();
         qte2=prod.get(0).getQte();
         prix2=prod.get(0).getPrix();
         cat1=prod.get(0).getCat().getTypec();
         mar1=prod.get(0).getMar().getNomM();
         t1=prod.get(0).getTaille();
         t2=prod.get(0).getTaille2();
         col1=prod.get(0).getCouleur();
       tfnom.setText(nom2);
         tfdesc.setText(prod.get(0).getDesc());
         tfimg.setText(prod.get(0).getImg());
        String qte = String.valueOf(prod.get(0).getQte());
         int prix1 = (int)prod.get(0).getPrix();
         String prix = String.valueOf(prix1);
         tfqte.setText(qte);
         tfprix.setText(prix);
         id1=prod.get(0).getId();
           Image image = new Image(new FileInputStream(Badge.url_upload+tfimg.getText()));
           imgview.setImage(image);
                 
    }
    @FXML
    private void nom_edit(TableColumn.CellEditEvent<produit, String> event) {
        produit prod=tableprod.getSelectionModel().getSelectedItem();
       
        prod.setNom(event.getNewValue());
        tfnom.setText(prod.getNom());
   
    }
    @FXML
    private void qte_edit(TableColumn.CellEditEvent<produit, Integer> event) {
    produit prod=tableprod.getSelectionModel().getSelectedItem();
    prod.setQte(event.getNewValue());
        
    tfqte.setText(String.valueOf(prod.getQte()));
    }
    @FXML
    private void prix_edit(TableColumn.CellEditEvent<produit, Float> event) {
    produit prod=tableprod.getSelectionModel().getSelectedItem();
    prod.setPrix(event.getNewValue());
        int prix1 = (int)prod.getPrix();
         String prix = String.valueOf(prix1);
         tfprix.setText(prix);
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

    

  
   
  
   

    
}
