/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import entities.Article;
import entities.Badge;
import entities.User;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import services.ServiceArticle;
import services.ServiceBadge;
import services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class ArticleController implements Initializable {

    @FXML
    private TableColumn<Article, Integer> id;
    @FXML
    private TableColumn<Article, String> user_id;
    @FXML
    private TableColumn<Article,String > titre;
    @FXML
    private TableColumn<Article, String> descr;
    @FXML
    private TableColumn<Article, String> datea;
    @FXML
    private TableColumn<Article, Image> img;
    @FXML
    private TextField tatitre;
    @FXML
    private TextField tadescr;
    @FXML
    private ComboBox<String> user_c;
    @FXML
    private Button upload;
    @FXML
    private TableView<Article> taba;
     ServiceArticle psm = new ServiceArticle();
     ServiceUser psu = new ServiceUser();
    @FXML
    private Button save;
    @FXML
    private Label imglabel;
    @FXML
    private Button remove;
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
        taba.setEditable(true);
        titre.setCellFactory(TextFieldTableCell.forTableColumn());
        descr.setCellFactory(TextFieldTableCell.forTableColumn());
        
         ObservableList<String> listc =  FXCollections.observableArrayList();
         for(User c :  psu.recuperer())
        {
           
            listc.add(c.getUsername());
        }
        
        user_c.setItems(listc);
        showArticles();
    }    

     public void showArticles()
    {
        img.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Article, Image> cell = new TableCell<Article, Image>() {
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
        
         img.setCellValueFactory(new PropertyValueFactory<Article, Image>("img_a"));
        id.setCellValueFactory(new PropertyValueFactory<Article,Integer>("id"));
        titre.setCellValueFactory(new PropertyValueFactory<Article,String>("titre"));
        descr.setCellValueFactory(new PropertyValueFactory<Article,String>("descr"));
       user_id.setCellValueFactory(new PropertyValueFactory<Article,String>("users"));
     
   
         datea.setCellValueFactory(new PropertyValueFactory<Article,String>("datea"));
        taba.setItems(psm.recuperer(0));
        
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

         imglabel.setText(f.getAbsoluteFile().toURI().toString());
    }

    @FXML
    private void onsave(ActionEvent event) {
      
         if(tatitre.getText().isEmpty()  || tadescr.getText().isEmpty() || imglabel.getText().isEmpty())
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
              Image img3 = new Image(imglabel.getText());
          
            File file = new File(Badge.url_upload  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
             Article a = new Article(tatitre.getText(),tadescr.getText(),nameImage,psu.recuperer().get(user_c.getSelectionModel().getSelectedIndex()));
        ServiceArticle sb = new ServiceArticle(); 
        sb.ajouter(a);
        showArticles();
        }
       
        
        
    }
    @FXML
    private void onedittitre(TableColumn.CellEditEvent<Article, String> event) {
          Article b = taba.getSelectionModel().getSelectedItem();
        
b.setTitre(event.getNewValue());
psm.modifier(b);
    }
    @FXML
    private void oneditdescr(TableColumn.CellEditEvent<Article, String> event) {
        Article b = taba.getSelectionModel().getSelectedItem();
        
b.setDescr(event.getNewValue());
psm.modifier(b);
    }

    

   

    @FXML
    private void onremove(ActionEvent event) {
        Article b = taba.getSelectionModel().getSelectedItem();
psm.supprimer(b.getId());
showArticles();
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
