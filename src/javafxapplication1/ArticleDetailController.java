/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Article;
import entities.Interaction;
import entities.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import services.ServiceArticle;
import services.ServiceInteraction;
import services.ServiceNotification;
import util.session;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class ArticleDetailController implements Initializable {


    private Label lab;
    @FXML
    private ImageView img;
    private Label op;
    @FXML
    private Label title;
    @FXML
    private Label descr;
    

    public static Interaction rowp;
    private VBox viewers;
    private ScrollPane scrollp;
    @FXML
    private VBox vbox;
    @FXML
    private ListView<Interaction> lsv;
    @FXML
    private TextArea textfc;
    @FXML
    private Button send;
    /**
     * Initializes the controller class.
     */
   // private User u = new User();
    @FXML
    private Button rate;
    @FXML
    private ImageView rollback;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
       ServiceInteraction st = new ServiceInteraction();
        if(st.recupererLike(ArticleFrontController.current_id.getId(),session.getSession().getId()))     
        rate.setText("Unlike ( "+st.recupererLikes(ArticleFrontController.current_id.getId())+" )");
        else
        {
            rate.setText("Like ( "+st.recupererLikes(ArticleFrontController.current_id.getId())+" )");
        }
        rate.setOnMousePressed((MouseEvent event) -> {
            if(session.getSession().getId()==0)
            {
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Connection Error");
alert.setContentText("you must connect first !");

alert.showAndWait();

            }
            else
            {
                if(rate.getText().indexOf("Unlike")!= -1)
            {
                //System.out.println("Unlike");
                 
                 ServiceInteraction si = new ServiceInteraction();
                 si.deleteLike(ArticleFrontController.current_id.getId(),session.getSession().getId());
                 rate.setText("Like ( "+st.recupererLikes(ArticleFrontController.current_id.getId())+" )");
            }
            else
            {
               // System.out.println("Like");
                ServiceNotification Notification = new ServiceNotification();
                 Notification.check_Box("", "are you sure u wanna add  like to the article ? \n would you like to continue ?");
                  ServiceInteraction si = new ServiceInteraction();
                  Interaction inter = new Interaction();
                  inter.setArticle(ArticleFrontController.current_id);
                  inter.setUser(session.getSession());
                  inter.setDescrp("");
                  inter.setType("up");
                  si.ajouter(inter);
                  rate.setText("Unlike ( "+st.recupererLikes(ArticleFrontController.current_id.getId())+" )");
                  
        
                //  Notification.Notification("Like added ", "aaa");
                
            }
            }
            
         
          
                    
        });
                
        
        // TODO
         
       
            //Set up the ImageView
            //final ImageView imageview = new ImageView();
            img.setImage(ArticleFrontController.current_id.getImg_a());
           // img.setFitHeight(350);
         //   img.setFitWidth(550);
 ObservableList<Interaction> listc =  FXCollections.observableArrayList();
 descr.setText(ArticleFrontController.current_id.getDescr());
 title.setText(ArticleFrontController.current_id.getTitre());
 display();
 
           ArrayList<Article> cell = new ArrayList<Article>() {
                public void updateItem(Article item, boolean empty) {
                    if (item != null) {
                       
                       
                    
                    }
                     
                }
            };
           
            // Attach the imageview to the cell
      
         
    }    
private void display()
{
   
      ServiceInteraction st = new ServiceInteraction();
    lsv.setCellFactory(param -> {
            //Set up the ImageView
            final Label owner_name = new Label();
             final Label owner_descrp = new Label();
            final ImageView img = new ImageView();
img.setFitHeight(25);
img.setFitWidth(25);
            //Set up the Table
           ListCell<Interaction> cell = new ListCell<Interaction>() {
                public void updateItem(Interaction item, boolean empty) {
                    if (item != null) {
                          
                        owner_name.setText(item.getUser().getUsername());
                        String descrp_filter = item.getDescrp();
                          try {
      File myObj = new File("C:\\Users\\ksaay\\Documents\\NetBeansProjects\\JavaFXApplication1\\src\\util\\banword.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
      
         if( descrp_filter.indexOf(data)!= -1)
            {
               descrp_filter= descrp_filter.replace(data, "******");
            }
      }
      
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
                       
                        owner_descrp.setText(descrp_filter);
                        img.setImage(item.getUser().getBadge().getImg());
                     
                        HBox owner = new HBox(1);
            Button remove = new Button();
              Image img_b = new Image("C:\\Users\\ksaay\\Documents\\NetBeansProjects\\JavaFXApplication1\\src\\icons\\android-trash-icon-1.jpg");
      ImageView view = new ImageView(img_b);
      view.setFitHeight(10);
      view.setFitWidth(10);
      view.setPreserveRatio(true);
        
          remove.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
         
          remove.setGraphic(view);
         
            owner.setAlignment(Pos.TOP_LEFT);
            owner.setPadding(new Insets(1));
            remove.setOnMousePressed((MouseEvent event) -> {
               
                ServiceInteraction rs = new ServiceInteraction();
                Interaction act = lsv.getSelectionModel().getSelectedItem();
                if(act.getIdi()> 0 )
            {
                rs.supprimer(act.getIdi());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText(" Done!");
                alert.show();
                display();
            }
                
            });
           
            if(session.getSession().getId()==item.getUser().getId() || session.getSession().getRole()=="admin")
            owner.getChildren().addAll(img,owner_name,remove);
            
            else
                owner.getChildren().addAll(img,owner_name);

            
            VBox root = new VBox(2);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(2));
       // Button buttonStartOperation = new Button("Start long lasting operation");
        root.getChildren().addAll(owner,owner_descrp);
                          setGraphic(root);
                    }
                }
            };
         
           
           
      // cell.setGraphic(root);
            return cell;
            
        });
                     lsv.setItems(st.recupererbyid(ArticleFrontController.current_id.getId()));
}
    @FXML
    private void onsend(MouseEvent event) {
        if(textfc.getText().isEmpty())
        {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Add Error");
alert.setContentText("all fields must  not be empty !");

alert.showAndWait();
        }
        else
        {
            if(session.getSession().getId()!=0)
            {
                ServiceInteraction si = new ServiceInteraction();
                Interaction i = new Interaction("comment",textfc.getText(),session.getSession(),ArticleFrontController.current_id);
                si.ajouter(i);
                display();
                textfc.setText("");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Connection Error");
alert.setContentText("you must connect first !");

alert.showAndWait();
            }
                
                }
    }

    @FXML
    private void rollback(MouseEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/Home_turbo_devs.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }


    
}
