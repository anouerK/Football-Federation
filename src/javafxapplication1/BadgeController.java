/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxapplication1;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Badge;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.System.Logger;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javax.imageio.ImageIO;
import services.ServiceBadge;

/**
 * FXML Controller class
 *
 * @author ksaay
 */
public class BadgeController implements Initializable {

    @FXML
    private TableColumn<Badge, Integer> idB;
    @FXML
    private TableColumn<Badge, String> nomB;
    @FXML
    private TableColumn<Badge, Number> nbB;
    @FXML
    private TableColumn<Badge, Image> logoB;
    @FXML
    private TextField tanomB;
    @FXML
    private TextField tanbB;
    @FXML
    private Label badgeimg;
 ServiceBadge psm = new ServiceBadge();
    @FXML
    private TableView<Badge> tabB;
    
    private byte[] person_image = null;
    @FXML
    private Button remove;
    @FXML
    private TextField searchbar;
    @FXML
    private ImageView rollback;
    @FXML
    private Button backkk;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         Image image1 = new Image("/AhmedImages/out.png");
            rollback.setImage(image1);
        showBadges();
        tabB.setEditable(true);
        nomB.setCellFactory(TextFieldTableCell.forTableColumn());
        searchbar.textProperty().addListener((o, oldVal, newVal) -> {
    searchbadges(newVal);
    });
   
       
    } 
    
    
    
    @FXML
    private void onremove(ActionEvent event) {
       Badge b = tabB.getSelectionModel().getSelectedItem();
       psm.supprimer(b.getId());
      showBadges();

    }   
    @FXML
    public void onEditChangelogoB(TableColumn.CellEditEvent<Badge,String> badgeStringCellEditEvent)
{
Badge b = tabB.getSelectionModel().getSelectedItem();
b.setLogoB(badgeStringCellEditEvent.getNewValue());
psm.modifier(b);

}
    @FXML
 public void onEditChangenomB(TableColumn.CellEditEvent<Badge,String> badgeStringCellEditEvent)
{
Badge b = tabB.getSelectionModel().getSelectedItem();
b.setNomB(badgeStringCellEditEvent.getNewValue());
psm.modifier(b);
} 
 @FXML
 public void onEditChangenbB(TableColumn.CellEditEvent<Badge,Number> badgeStringCellEditEvent)
{


 if(badgeStringCellEditEvent.getNewValue().intValue()>0)
 {
     Badge b = tabB.getSelectionModel().getSelectedItem();
b.setNb(badgeStringCellEditEvent.getNewValue().intValue());
psm.modifier(b);
 }
         else
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Update Error");
alert.setContentText("Error !");

alert.showAndWait();
         }
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
    private void save(ActionEvent event) {
        if(tanomB.getText().isEmpty()  || badgeimg.getText().isEmpty() || tanbB.getText().isEmpty())
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
              Image img3 = new Image(badgeimg.getText());
          
            File file = new File(Badge.url_upload  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
             Badge b = new Badge(tanomB.getText(),nameImage,Integer.parseInt(tanbB.getText()));
        ServiceBadge sb = new ServiceBadge(); 
        sb.ajouter(b);
        showBadges();
        }
       
        
        
    }
    
    
    @FXML
    private void upload(ActionEvent event)
    {
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

         badgeimg.setText(f.getAbsoluteFile().toURI().toString());
         
    }
     public void showBadges()
    {
       
          logoB.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Badge, Image> cell = new TableCell<Badge, Image>() {
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

      
         logoB.setCellValueFactory(new PropertyValueFactory<Badge, Image>("img"));
      idB.setCellValueFactory(new PropertyValueFactory<Badge,Integer>("id"));
       nomB.setCellValueFactory(new PropertyValueFactory<Badge,String>("nomB"));
       nbB.setCellValueFactory(new PropertyValueFactory<Badge,Number>("nb"));
        //tabB.setItems(list);
        tabB.setItems(psm.recuperer(0));
    }
    public void searchbadges(String x)
    {
       
          logoB.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Badge, Image> cell = new TableCell<Badge, Image>() {
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

      
         logoB.setCellValueFactory(new PropertyValueFactory<Badge, Image>("img"));
      idB.setCellValueFactory(new PropertyValueFactory<Badge,Integer>("id"));
       nomB.setCellValueFactory(new PropertyValueFactory<Badge,String>("nomB"));
       nbB.setCellValueFactory(new PropertyValueFactory<Badge,Number>("nb"));
        //tabB.setItems(list);
        //tabB.setItems(psm.recuperer(0));
        tabB.setItems(psm.rec_search(x));
    }
@FXML
    private void onEditStartlogoB(TableColumn.CellEditEvent<Badge, String> event) {
        FileChooser fileChooser = new FileChooser();

       
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

        
         Badge b = tabB.getSelectionModel().getSelectedItem();
         if(f.toString().isEmpty())
         {
             b.setLogoB(f.toString());
psm.modifier(b);

         }
         else
         {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("Error");
alert.setHeaderText("Update Error");
alert.setContentText("Error !");

alert.showAndWait();
         }
         showBadges();
    }

  
    @FXML
    private void oneditsearchbar(InputMethodEvent event) {
       
        tabB.setItems(psm.rec_search(event.toString()));
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
    private void backkk(ActionEvent event) {
    }

   
 
}
