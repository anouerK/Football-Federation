/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Article;
import com.esprit.entities.Badge;
import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceBadge;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author ksaay
 */
public class ListArticle extends Form {
    
    public ListArticle(Form previous,Resources res)
    { super("List Articles");
        Container topp = BoxLayout.encloseY();
       
 topp.setScrollableY(true);
         topp.setScrollVisible(false);
          this.add(topp);
         // super("list Badges",new BorderLayout());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      Container b = new Container(BoxLayout.y());
    
        b.setScrollableY(true);
        
            add( b);
          
              // final BrowserComponent cmp = new BrowserComponent();
           /*    BrowserComponent browser = new BrowserComponent();
               browser.setURL("http://127.0.0.1:8000/user");
              add(browser);
               */
    //cmp.setPage("<!doctype html><html><body>Hello World <br><br>Helloooooo<br><br></body></html>", null);
    //add(cmp);
        
           String[] characters = { "none","","titre Asc", "","titre Desc","","datea ASC", "","datea DESC"};
     Picker p = new Picker();
p.setStrings(characters);
p.setSelectedString(characters[0]);
p.addActionListener((e) ->
        {
             ToastBar.showMessage("You sorted the Display by  " + p.getSelectedString(), FontImage.MATERIAL_INFO);
                   //this.removeAll();
                   b.removeAll();
                   if(p.getSelectedString()== characters[2] )
                  display(1,"",b); 
                   if(p.getSelectedString()== characters[4] )
                  display(2,"",b);
                   if(p.getSelectedString()== characters[6] )
                  display(3,"",b); 
                   if(p.getSelectedString()== characters[8] )
                  display(4,"",b);
                    if(p.getSelectedString()== characters[0] )
                  display(0,"",b);
                   
        }
    );
TextField searchbar = new TextField("","Search");
searchbar.addDataChangedListener(new DataChangedListener() {
            public void dataChanged(int type, int index) {
               b.removeAll();
               display(-1,searchbar.getText(),b); 
            
            }
        });

   topp.add(BorderLayout.west(p).add(BorderLayout.EAST,searchbar));
   

 
      display(0,"",b);   
     
    
    }
        public void display(int orderby,String search,Container b)
        {
             
            
b.removeAll();
//this.add(BorderLayout.NORTH,p); 
        SpanLabel sp = new SpanLabel();
        
         ArrayList<Article> list;
        list = new ArrayList<>();
        ArrayList<Button> buttons = null;
        if(orderby != -1)
        list = ServiceArticle.getInstance().getAll(orderby);
        else
        list = ServiceArticle.getInstance().getAllS(search);
        
        
      
        
            EncodedImage enc = null;
            
Image imgs;
ImageViewer imgv;
String urli ;


         for ( Article ev : list) {
            
             Container elements = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             elements.setScrollableY(true);
                 urli = Statics.BASE_URL + "uploads/img/"+ev.getImg();
              
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                 int mm = Display.getInstance().convertToPixels(6);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 6, mm * 8, 0), false);
                Image i = URLImage.createToStorage(placeholder, urli, urli);
              i = i.fill(350,350);
              Label profilePicLabel = new Label(i, "articlePicture");
             
               
            
               
               
        Container buttonss = BoxLayout.encloseX();
      
              Button update = new Button();
              Button delete = new Button();
             
                FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(update, FontImage.MATERIAL_UPDATE);
                buttonss.add(BorderLayout.west(update)
                 
              
                .add(BorderLayout.EAST,delete));

 Label titretext = new Label(ev.getTitre());
    Label usertext = new Label(ev.getUser().getUsername());
    
titretext.getAllStyles().setBgTransparency(0);
usertext.getAllStyles().setBgTransparency(0);
titretext.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));

             elements.add(titretext).add(usertext).add(buttonss);
          
                b.add(BorderLayout.center(elements).
        add(BorderLayout.WEST, profilePicLabel));
               
               
       update.addActionListener((evt) ->{
    
     new UpdateArticle(this,ev).show();
    
});
     
delete.addActionListener((evt) ->{
   
      if( ServiceArticle.getInstance().deleteArticle(ev.getId())){
                   Dialog.show("Success","Article Deleted","OK",null);
                   b.removeAll();
                     display(0,"",b);   
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
});


     
        
        }
         this.revalidate();

    }
}
