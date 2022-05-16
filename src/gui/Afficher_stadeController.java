/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Badge;
import entities.Stade;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.controlsfx.control.Notifications;
//import org.controlsfx.control.Notifications;
import services.StadeService;
import util.MyDB;
import views.HomeController;

/**
 * FXML Controller class
 *
 * @author Ahmed.A.Hsouna
 */
public class Afficher_stadeController implements Initializable {

    @FXML
    private TableColumn<?, ?> id_sta;
    @FXML
    private TableColumn<?, ?> nom_st;
    @FXML
    private TableColumn<?, ?> lieu_sta;
    @FXML
    private TableColumn<?, ?> nbr_sta;
    @FXML
    private TableColumn<?, ?> etat_sta;
    @FXML
    private TableColumn<?, ?> image_sta;
    @FXML
    private TextField nom_st1;
    @FXML
    private TextField lieu_sta1;
    @FXML
    private TextField nbr_sta1;
    @FXML
    private TextField etat_sta1;
    @FXML
    private TextField image_sta1;

    StadeService pcd = new StadeService();

    ObservableList<Stade> stades = FXCollections.observableArrayList();

    private FileChooser fileChooser;

    private File file;

    int index = -1;

    Connection cnx;
    @FXML
    private TableView<Stade> tablestade;
    @FXML
    private TextField recherche_sta;
    @FXML
    private ImageView imageview;
    @FXML
    private Button upload;
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
    private TableColumn<Stade, Image> image_st;
    @FXML
    private Button profile;
    
    static Stade selectionedReclamation;
    static Stage stageAffichageUnique;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       

        afficherstade();
        FilteredList<Stade> filteredData = new FilteredList<>(stades, t -> true);

        recherche_sta.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Arbitre -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (Arbitre.getNoms().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Arbitre.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Arbitre.getLieu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Stade> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(tablestade.comparatorProperty());

        tablestade.setItems(sortedData);
        
        
        
        /*   tablestade.setOnMouseClicked((MouseEvent event2)
                -> {
            if (event2.getClickCount() >= 2) {
                if (tablestade.getSelectionModel().getSelectedItem() != null) {
                    selectionedReclamation = tablestade.getSelectionModel().getSelectedItem();

                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/pidev/bonplan/GUI/AfficherReclamationUniqueFXML.fxml"));
                        Scene scene = new Scene(root);
                        stageAffichageUnique.setScene(scene);
                        stageAffichageUnique.show();

                    } catch (IOException ex) {
                        Logger.getLogger(Afficher_stadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        });*/
    }

    public void afficherstade() {

        try {
            stades = pcd.afficherr();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_stadeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id_sta.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_st.setCellValueFactory(new PropertyValueFactory<>("noms"));
        nbr_sta.setCellValueFactory(new PropertyValueFactory<>("nbrp"));
        etat_sta.setCellValueFactory(new PropertyValueFactory<>("etat"));
        lieu_sta.setCellValueFactory(new PropertyValueFactory<>("lieu"));

        image_st.setCellValueFactory(new PropertyValueFactory<>("photo"));

        //image 
        image_st.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(90);

            //Set up the Table
            TableCell<Stade, Image> cell = new TableCell<Stade, Image>() {
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

        image_st.setCellValueFactory(new PropertyValueFactory<Stade, Image>("image2"));
        tablestade.setItems(stades);

    }

    @FXML
    private void selected(MouseEvent event) {

        index = tablestade.getSelectionModel().getSelectedIndex();

        if (index <= -1) {

            return;
        }
        nom_st1.setText(nom_st.getCellData(index).toString());
        nbr_sta1.setText(nbr_sta.getCellData(index).toString());
        lieu_sta1.setText(lieu_sta.getCellData(index).toString());
        etat_sta1.setText(etat_sta.getCellData(index).toString());
        if(image_sta.getCellData(index)==null)
        {
            image_sta1.setText("");
        }
        else
        {
            image_sta1.setText(image_sta.getCellData(index).toString());
        }
        /*
         imageview.setImage(new Image((InputStream) image_sta.getCellData(index)));
        imageview.setFitHeight(175);
        imageview.setFitWidth(320);*/
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
    private void upload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Pick a picture");
        fileChooser.setInitialDirectory(new File("C:\\Users"));
        Stage stage = new Stage();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        File file = fileChooser.showOpenDialog(stage);
        if(file!=null)
        {
             try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image1 = SwingFXUtils.toFXImage(bufferedImage, null);
            image_sta1.setText(file.getAbsolutePath());
            imageview.setImage(image1);
        } catch (IOException ex) {
            System.out.println("image introuvable");
        }
        }
       
    }

    @FXML
    private void addd(ActionEvent event) {

        if (nbr_sta1.getText().isEmpty() == true || nom_st1.getText().isEmpty() == true || lieu_sta1.getText().isEmpty() == true || etat_sta1.getText().isEmpty() == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("error");
            alert.setContentText("Veuiller remplir vos champs");
            alert.show();
        } else {

            String nameImage;
          

            nameImage = generateRandomPassword(10) + ".png";
              Image img3 = new Image(image_sta1.getText());
          
            File file = new File(Badge.url_upload2  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stade u = new Stade(Integer.parseInt(nbr_sta1.getText()), nom_st1.getText(), lieu_sta1.getText(), etat_sta1.getText(), nameImage);
            pcd.ajouter(u);
            Notifications notificationBuilder = Notifications.create()
                    .title("alert").text("Stade ajouté avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("");
                        }
                    });
            notificationBuilder.showInformation();
        }
    }

    @FXML
    private void deletee(ActionEvent event) {
        if (tablestade.getSelectionModel().getSelectedItem() != null) {
            pcd.supprimer(tablestade.getSelectionModel().getSelectedItem().getId());
            stades.remove(stades);

            Notifications notificationBuilder = Notifications.create()
                    .title("alert").text("Stade supprimée avec succés").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
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
            cnx = MyDB.getInstance().getConnection();
            String value1 = nom_st1.getText();
            int value5 = Integer.parseInt(nbr_sta1.getText());
            String value7 = lieu_sta.getText();
            String value9 = etat_sta.getText();
            String nameImage=image_sta1.getText();
            //String value8 = image_sta1.getText();
            if(nameImage.contains("C:"))
            {
                
          

            nameImage = generateRandomPassword(10) + ".png";
              Image img3 = new Image(image_sta1.getText());
          
            File file = new File(Badge.url_upload2  + nameImage);

            try {
                ImageIO.write(SwingFXUtils.fromFXImage(img3, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            }

            String sql = "update stade set noms= '" + value1 + "',nbr_p= '"
                    + value5 + "',photo= '" + nameImage + "',lieu= '" + value7 + "',etat= '" + value9 + "'   where id='" + tablestade.getSelectionModel().getSelectedItem().getId() + "' ";

            PreparedStatement pst = cnx.prepareStatement(sql);
            pst.execute();
            Notifications notificationBuilder = Notifications.create()
                    .title("alert").text("Referee Updated").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.BOTTOM_RIGHT)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println("");
                        }
                    });
          //  notificationBuilder.showConfirm();

        } catch (HeadlessException | SQLException e) {
        }
    }

    @FXML
    private void pdff(ActionEvent event) throws DocumentException {
        try {
            SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat Heure = new SimpleDateFormat("hh:mm:ss");
            String message = "--------------------------------------------------------\n--------**Stadiums List**--------\n--------------------------------------------------------\n \n\n";

            String file_name = "src/liste_stade.pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            document.open();
            Paragraph para = new Paragraph(message);
            document.add(para);

            document.add(new Paragraph("                                                           "
                    + "                                                                                           "
                    + "    " + date.format(new java.util.Date()) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ));

            document.add(new Paragraph("                                                           "
                    + "                                                                                                 "
                    + "  " + Heure.format(new java.util.Date()) + "\n\n\n" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            ));

            List<Stade> Excursion = pcd.afficherr();
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(60);
            table.setSpacingBefore(11f);

            Font f = new Font(Font.FontFamily.HELVETICA, 13, Font.NORMAL, GrayColor.GRAYWHITE);

            PdfPCell c2 = new PdfPCell(new Paragraph("Id"));
            table.addCell(c2);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell c11 = new PdfPCell(new Phrase("Nom"));
            table.addCell(c11);
            c11.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell c12 = new PdfPCell(new Phrase("Capacite"));
            table.addCell(c12);
            c12.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cl3 = new PdfPCell(new Phrase("Lieu"));
            table.addCell(cl3);
            cl3.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell cl4 = new PdfPCell(new Phrase("Etat"));
            table.addCell(cl4);
            cl4.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.setHeaderRows(1);
            document.add(table);

            int i = 0;
            for (i = 0; i < Excursion.size(); i++) {
                table.addCell("" + Excursion.get(i).getId());
                table.addCell("" + Excursion.get(i).getNoms());
                table.addCell("" + Excursion.get(i).getNbrp());
                table.addCell("" + Excursion.get(i).getLieu());
                table.addCell("" + Excursion.get(i).getEtat());

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
            stades = pcd.afficherr();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Afficher_stadeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        id_sta.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_st.setCellValueFactory(new PropertyValueFactory<>("noms"));
        nbr_sta.setCellValueFactory(new PropertyValueFactory<>("nbrp"));
        etat_sta.setCellValueFactory(new PropertyValueFactory<>("etat"));
        lieu_sta.setCellValueFactory(new PropertyValueFactory<>("lieu"));
        // image_st.setCellValueFactory(new PropertyValueFactory<>("photo"));

        //image 
        image_st.setCellFactory(param -> {
            //Set up the ImageView
            final ImageView imageview = new ImageView();
            imageview.setFitHeight(70);
            imageview.setFitWidth(90);

            //Set up the Table
            TableCell<Stade, Image> cell = new TableCell<Stade, Image>() {
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

        image_st.setCellValueFactory(new PropertyValueFactory<Stade, Image>("image2"));
        tablestade.setItems(stades);
    }

    @FXML
    private void backkk(ActionEvent event) throws IOException {
        /*try {
            FXMLLoader LOADER = new FXMLLoader(getClass().getResource("/javafxapplication1/MainBack.fxml"));
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            MainTournoiController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }*/
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
