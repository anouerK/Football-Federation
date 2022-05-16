/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.Article;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;
import services.ServiceArticle;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class ArticleFrontController implements Initializable {
  
 private Stage stage;
    private Scene scene;
    static public Article current_id = new Article();
    @FXML
    private ListView<Article> listA;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private ImageView rollback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
                combobox.getItems().addAll(
            "ASC Date",
            "Desc Date"
        
        );
        ServiceArticle st = new ServiceArticle();
       // combobox.setItems("HHH");
          listA.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(250);
            imageview.setFitWidth(400);

            //Set up the Table
           ListCell<Article> cell = new ListCell<Article>() {
                public void updateItem(Article item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item.getImg_a());
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
            
        });
          
          listA.setItems(st.recuperer(0));
          
         
    }    

    @FXML
    private void showDetail(MouseEvent event) throws IOException {
         
         
          current_id =new Article(listA.getSelectionModel().getSelectedItem());
          
           Parent root = FXMLLoader.load(getClass().getResource("ArticleDetail.fxml"));
   stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Article Detail");
   stage.show();
   




    }

    

    @FXML
    private void combochange(ActionEvent event) {
        // System.out.println(combobox.getValue());
        if(combobox.getValue()=="ASC Date")
        {
            display(1);
        }
            else
            
        {
                display(2);
                }
    }
    private  void display(int order)
    {
         ServiceArticle st = new ServiceArticle();
        listA.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(250);
            imageview.setFitWidth(400);

            //Set up the Table
           ListCell<Article> cell = new ListCell<Article>() {
                public void updateItem(Article item, boolean empty) {
                    if (item != null) {
                        imageview.setImage(item.getImg_a());
                    }
                }
            };
            // Attach the imageview to the cell
            cell.setGraphic(imageview);
            return cell;
            
        });
          
          listA.setItems(st.recuperer(order));
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
