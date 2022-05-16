/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Rewards;
import entities.Tournoi;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.GameService;
import services.ServiceClassement;
import services.ServiceRewards;
import services.ServiceTournoi;

/**
 * FXML Controller class
 *
 * @author oumayma
 */
public class TournoiFrontController implements Initializable {

    public static Tournoi selectedtournoi;//= new Tournoi() ;
    @FXML
    private ListView<Tournoi> list;
    @FXML
    private ImageView imgV;
    @FXML
    private HBox hb;
  private   Tournoi t ;
    @FXML
    private VBox xi;
    @FXML
    private Label nom;
    @FXML
    private Label dated;
    @FXML
    private Label datef;
    @FXML
    private Label type;
    @FXML
    private Label nbrc;
    @FXML
    private ImageView imgreward;
    @FXML
    private Button det;
    @FXML
    private Button det1;
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
          ServiceTournoi st=new ServiceTournoi();
        HBox l= new HBox();
        
        ScrollPane s= new ScrollPane();
        // for (int i = 0; i < st.affichTournoiImg().size(); i++) {
       
     //   BorderPane p =new BorderPane();
      
         
        
          //  s.getChildrenUnmodifiable().addAll(list,v);
             // v.getChildren().addAll(l);
      //  hb.getChildren().addAll(l);
     //   p.getChildren().addAll(hb);
           ObservableList<Tournoi> tournois = FXCollections.observableArrayList();
         list.setCellFactory(param ->{
      
       
 final ImageView imageview = new ImageView();
  Label lab = new Label();
  final VBox v= new VBox();
 imageview.setFitHeight(150);
            imageview.setFitWidth(250);
            imageview.setX(500);
           // lab.setPrefSize(40, 40);
    ListCell< Tournoi> cell = new ListCell< Tournoi>() {
       public void updateItem(Tournoi item, boolean empty){
           super.updateItem(item, true);
        
       
          if(item != null)
          {
 //for (int i = 0; i < st.affichTournoiImg().size(); i++) {
        imageview.setImage(item.getImg());
       // setText(item.getNomt());
       // setText(item.getNomt());
           // imgV.setImage (item);
               //  setGraphic(imgV);
                // cell.setText(item.getNomt());
                
             
 
                 }
           // imgV.setImage(img.getImage());
           
                 }        
      
    };
                  // cell.setText("aaaaa");
                  cell.setGraphic(imageview);
                  cell.setCenterShape(true);
                 
                
                // cell.setText(lab.getText());
                     return cell ;
         });
         // list.getItems().addAll(tournois);
       // list.getChildrenUnmodifiable();
          list.setItems(st.afficher());
      // hb.getChildren().addAll(imgV);
        
         // v.getChildren().addAll(hb);
        // list.setCellValueFactory(param->new ListCell<Tournoi>());
       
    }
    @FXML
    private void onClick(MouseEvent event) {
    }
      @FXML
    private void detail(MouseEvent event) {
    
                      Parent root;
        try {
          
             Tournoi t=list.getSelectionModel().getSelectedItem();
             System.out.println(t.getId());
              selectedtournoi= t;
               root = FXMLLoader.load(getClass().getResource("Classement.fxml"));
   //         FXMLLoader loader = new FXMLLoader(getClass().getResource("Classement.fxml"));
          //   ClassementController HomeScene = loader.getController();
      //  HomeScene.id=t.getId() ;
    
         
           ObservableList<Tournoi> tournois = FXCollections.observableArrayList();
            ServiceClassement s= new ServiceClassement();
           // s.affichcl(t.getId());
           // System.out.println( s.affichcl(t.getId()));
        //  tournois.subList(t.getId(), t.getId());
         /*  ListCell< Tournoi> cell = new ListCell< Tournoi>() {
                 public int updateItem(int item, boolean empty){
          
        item=t.getId();
  
           return item;
           
                 }
           };*/
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
//stage.initStyle(StageStyle.TRANSPARENT);

       // scene.setFill(Color.TRANSPARENT);
       // scene.getStylesheets().add("bootstrap.css");   
   stage.setScene(scene);
   stage.setTitle("Classement");
  
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
        
        
    }
   

    @FXML
    private void showClassement(ActionEvent event) throws FileNotFoundException {
           Tournoi t=list.getSelectionModel().getSelectedItem(); 
          ServiceRewards st=new ServiceRewards();
            ObservableList<Tournoi> tournois = FXCollections.observableArrayList();
          ObservableList<Rewards> y = FXCollections.observableArrayList();
         // Rewards r=new Rewards(t.getId(),t.getNomt());
         for(Rewards r :st.rechercheReward(t.getId())) 
         {
             
          imgreward.setImage(r.getLogo());
          System.out.println(r.getTrophe());
         // xi.getChildren().add(imgreward);
         
                        imgV.setImage(t.getImg());
                        nom.setText(t.getNomt());
                        dated.setText(t.getDated());
                         datef.setText(t.getDatef());
                          type.setText(t.getTypet());
                          nbrc.setText(String.valueOf(t.getNbrc()));
                    xi.getChildren().addAll(imgV,nom,dated,datef,type,nbrc,imgreward);}
                 //   tournois.subList(t.getId(), t.getId());
    }
/*
    private void backM(ActionEvent event) {
           Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("mainTournoi.fxml"));
          Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
    }*/

    @FXML
    private void showGames(ActionEvent event) {
            Parent root;
        try {
          
             Tournoi t=list.getSelectionModel().getSelectedItem();
             System.out.println(t.getId());
              selectedtournoi= t;
               root = FXMLLoader.load(getClass().getResource("game_front.fxml"));
 
    
         
        
          
                Stage  stage = (Stage)((Node)event.getSource()).getScene().getWindow();
   Scene scene = new Scene(root);
 
   stage.setScene(scene);
   stage.setTitle("Matches");
  
   stage.show();
      } catch(IOException ex){
      System.err.println(ex.getMessage());}
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
