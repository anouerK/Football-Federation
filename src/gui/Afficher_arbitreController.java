/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Arbitre;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
import services.ArbitreService;
//import org.controlsfx.control.Notifications;
import util.MyDB;

/**
 * FXML Controller class
 *
 * @author Ahmed.A.Hsouna
 */
public class Afficher_arbitreController implements Initializable {

    @FXML
    private TableColumn<?, ?> id_arb;
    @FXML
    private TableColumn<?, ?> nom_arb;
    @FXML
    private TableColumn<?, ?> nbr_arb;
    @FXML
    private TableColumn<?, ?> desc_arb;
    @FXML
    private TableColumn<Arbitre, Image> image_arb;

    ArbitreService pcd = new ArbitreService();

    ObservableList<Arbitre> arbitres = FXCollections.observableArrayList();
    @FXML
    private TableView<Arbitre> tablearbitre;
    @FXML
    private Button upload;

    private FileChooser fileChooser;

    private File file;

    int index = -1;

    Connection cnx;

    @FXML
    private ImageView imageview;
    @FXML
    private TextField nom_arb1;
    @FXML
    private TextField nbr_arb1;
    @FXML
    private TextArea desc_arb1;
    @FXML
    private TextField image_arb1;
    @FXML
    private TextField recherche;
    @FXML
    private TableColumn<Arbitre, Image> image_ar;
    @FXML
    private Button btnadd;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnmodofier;
    @FXML
    private Button pdff;
    @FXML
    private Button refrechh;
    @FXML
    private Button backkk;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        afficherarbitre();
        FilteredList<Arbitre> filteredData = new FilteredList<>(arbitres, t -> true);

        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Arbitre -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Arbitre.getNoma().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Arbitre.getDescp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Arbitre> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablearbitre.comparatorProperty());

        tablearbitre.setItems(sortedData);

    }

    public void afficherarbitre() {

        try {
            arbitres = pcd.afficherr();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_arbitreController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id_arb.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_arb.setCellValueFactory(new PropertyValueFactory<>("noma"));
        nbr_arb.setCellValueFactory(new PropertyValueFactory<>("nbe"));
        desc_arb.setCellValueFactory(new PropertyValueFactory<>("descp"));
        //image_arb.setCellValueFactory(new PropertyValueFactory<>("image2"));

        //image 
        image_ar.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(90);

            //Set up the Table
            TableCell<Arbitre, Image> cell = new TableCell<Arbitre, Image>() {
                @Override
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

         image_ar.setCellValueFactory(new PropertyValueFactory<Arbitre, Image>("image2"));
        tablearbitre.setItems(arbitres);

    }

  

    @FXML
    private void upload(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.setTitle("Pick a picture");
        fileChooser.setInitialDirectory(new File("C:\\xampp\\htdocs\\Federation-de-football\\public\\uploads\\"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            image_arb1.setText(file.getAbsolutePath());
            imageview.setImage(image1);
        } catch (IOException ex) {
            System.out.println("image introuvable");
        }
    }

    @FXML
    private void selectarbitre(MouseEvent event) {

        index = tablearbitre.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }
        nom_arb1.setText(nom_arb.getCellData(index).toString());
        // date.setText(col_datee.getCellData(index));
        nbr_arb1.setText(nbr_arb.getCellData(index).toString());

        desc_arb1.setText(desc_arb.getCellData(index).toString());

        image_arb1.setText(image_ar.getCellData(index).toString());

    }

   

    @FXML
    private void addd(ActionEvent event) {
        if (nom_arb1.getText().isEmpty()==true || nbr_arb1.getText().isEmpty()==true || desc_arb1.getText().isEmpty()==true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("error");
            alert.setContentText("Veuiller remplir vos champs");
            alert.show();
        } else {
            Arbitre u = new Arbitre(Integer.parseInt(nbr_arb1.getText()), nom_arb1.getText(), image_arb1.getText(), desc_arb1.getText());
            pcd.ajouter(u);

            Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Arbitre ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.showError();
        }
    }

    @FXML
    private void deletee(ActionEvent event) {
        if (tablearbitre.getSelectionModel().getSelectedItem() != null) {
            pcd.supprimer(tablearbitre.getSelectionModel().getSelectedItem().getId());
            arbitres.remove(arbitres);
            Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Arbitre supprimée avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.showError();
        }
    }

    @FXML
    private void modifierr(ActionEvent event) {
        
        try {
            // cnx = MyConnection.getInstance().getCnx();
            cnx = MyDB.getInstance().getConnection();
            //String value1 = txt_id.getText();
            String value1 = nom_arb1.getText();
            int value5 = Integer.parseInt(nbr_arb1.getText());
            String value7 = desc_arb1.getText();
            String value8 = image_arb1.getText();

            String sql = "update arbitre set nom_a= '" + value1 + "',nbe= '"
                    + value5 + "',image= '" + value8 + "',descrp= '" + value7 + "'   where id='" + tablearbitre.getSelectionModel().getSelectedItem().getId() + "' ";

            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
           
             Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Arbitre modifier avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.showConfirm();
            //JOptionPane.showMessageDialog(null, "Update");
            //UpdateTable();
        } catch (HeadlessException | SQLException e) {
            // JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void pdff(ActionEvent event) throws DocumentException {
         try {
            String message = "              --------**Liste des Arbitres**-------- \n\n\n";
            
            String file_name = "src/liste_arbitre.pdf";
            
            
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Paragraph para = new Paragraph(message);
            document.add(para);
            List<Arbitre> Excursion = pcd.afficherr();
            PdfPTable table = new PdfPTable(4);
            
            PdfPCell cl = new PdfPCell(new Phrase("Id"));
            table.addCell(cl);
            PdfPCell cl1 = new PdfPCell(new Phrase("Nom"));
            table.addCell(cl1);
            PdfPCell cl2 = new PdfPCell(new Phrase("nombre d'éxperience"));
            table.addCell(cl2);
            PdfPCell cl3 = new PdfPCell(new Phrase("Description"));
            table.addCell(cl3);
          

            
            
            
            
            table.setHeaderRows(1);
            document.add(table);
            
            int i = 0;
            for (i = 0; i < Excursion.size(); i++) {
                table.addCell("" + Excursion.get(i).getId());
                table.addCell("" + Excursion.get(i).getNoma());
                table.addCell("" + Excursion.get(i).getNbe());
                table.addCell("" + Excursion.get(i).getDescp());
        

            }
            document.add(table);
            
            document.close();
            
            
            
             Notifications notificationBuilder = Notifications.create()
                .title("alert").text("Pdf ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                .position(Pos.BOTTOM_RIGHT)
                .onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("");
                    }
                });
        notificationBuilder.showInformation();
       
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_arbitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void refrechh(ActionEvent event) {
        
         try {
            arbitres = pcd.afficherr();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_arbitreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        id_arb.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_arb.setCellValueFactory(new PropertyValueFactory<>("noma"));
        nbr_arb.setCellValueFactory(new PropertyValueFactory<>("nbe"));
        desc_arb.setCellValueFactory(new PropertyValueFactory<>("descp"));
        //image_arb.setCellValueFactory(new PropertyValueFactory<>("image"));

        // col_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        //image 
        image_ar.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(100);

            //Set up the Table
            TableCell<Arbitre, Image> cell = new TableCell<Arbitre, Image>() {
                @Override
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

        image_ar.setCellValueFactory(new PropertyValueFactory<Arbitre, Image>("image2"));

        tablearbitre.setItems(arbitres);
    }


    @FXML
    private void backkk(ActionEvent event) throws IOException {
      
       Parent root = FXMLLoader.load(getClass().getResource("/javafxapplication1/MainBack.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Main");
   stage.show();
    }

    @FXML
    private void btnhome(ActionEvent event) {
        try {
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("mainTournoi.fxml"));
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            MainTournoiController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnstade(ActionEvent event) {
        try {
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("afficher_stade.fxml"));
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            Afficher_stadeController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void btnarbitre(ActionEvent event) {
        try {
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("afficher_arbitre.fxml"));
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            Afficher_arbitreController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
