/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.io.Log;
import static com.codename1.io.Log.e;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.services.ServiceProduits;
import com.esprit.utils.Statics;
import com.esprit.entities.produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lord
 */
public class store extends Form  {
    Resources res;
    Container list = new Container(BoxLayout.y());
    Container c = new Container(BoxLayout.x());
      EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;


        String[] characters = { "Prix Croissant", "Prix Decroissant"};
String[] actors = { "Peter Dinklage", "Nikolaj Coster-Waldau", "Lena Headey"};
int size = Display.getInstance().convertToPixels(7);
EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(size, size, 0xffcccccc), true);
Image[] pictures = {
    URLImage.createToStorage(placeholder, "tyrion","http://i.lv3.hbo.com/assets/images/series/game-of-thrones/character/s5/tyrion-lannister-512x512.jpg%22"),
    URLImage.createToStorage(placeholder, "jaime","http://i.lv3.hbo.com/assets/images/series/game-of-thrones/character/s5/jamie-lannister-512x512.jpg%22"),
    URLImage.createToStorage(placeholder, "cersei","http://i.lv3.hbo.com/assets/images/series/game-of-thrones/character/s5/cersei-lannister-512x512.jpg%22")
};

 ArrayList<produit> listp;
 String t="";
  // Button view = new Button("View");
    
    public store(Form previous){
        Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");

TextField searchField = new TextField("", "Search"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);

getToolbar().setTitleComponent(searchField);

FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);




searchField.addDataChangeListener((i1, i2) -> { // <2>
     t = searchField.getText();
    //System.out.println("t here "+t);
   
     if(t.length()>0)
    {
    list.removeAll();
                
                 listp=ServiceProduits.getInstance().parseprod2(t);
                 
                 for(produit c : listp) {
                  list.add(createContactContainer(c));
   
                      }
    }
    else if(t.length()==0)
            {
               //System.out.println("t xxxxx "+t);
               t="";
                list.removeAll();
                
                 listp=ServiceProduits.getInstance().parseTasksP();
                 for(produit c : listp) {
                  list.add(createContactContainer(c));
   
                      }
                
            }
         
    
    getContentPane().animateLayout(250);
});
getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); // <4>
});

     
listp=ServiceProduits.getInstance().parseTasksP();

MultiButton b = new MultiButton("Filter...");
b.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < characters.length ; iter++) {
        MultiButton mb = new MultiButton(characters[iter]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            if(mb.getTextLine1()=="Prix Croissant")
            {
                System.out.println("cccccc");
                list.removeAll();
                
                 listp=ServiceProduits.getInstance().parseTasksPtri("c");
                 
                 for(produit c : listp) {
                  list.add(createContactContainer(c));
   
                      }
                
                
                
            }
            else if (mb.getTextLine1()=="Prix Decroissant")
            {
                System.out.println("ddddd");
               list.removeAll();
                
                 listp=ServiceProduits.getInstance().parseTasksPtri("d");
                 
                 for(produit c : listp) {
                  list.add(createContactContainer(c));
   
                      }
                
            }
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    d.showPopupDialog(b);
});
c.add(b);

        
        
        
  
    
     
    
     list.setScrollableY(true);
  
   
            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)-> new BaseFront().show());


for(produit c : listp) {
    list.add(createContactContainer(c));
    
}
  add(c);
  add(list);
    }
private Container createContactContainer(produit person) {
    Label name = new Label("");
    Label email = new Label("");
    //Label pic = new Label("");
    Label pic2 = new Label("______________________________");
    
   
    
    String prix=String.valueOf(person.getPrix());
    
    urli = Statics.BASE_URL + "/uploads/img/"+person.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(300,300);
              Label pic = new Label(i, "ProduitPicture");
    
    Button view = new Button("view");
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    name.getAllStyles().setBgTransparency(0);
    name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    email.getAllStyles().setBgTransparency(0);
    
     view.addActionListener((evtt)->new product(person).show());
    
    cnt.add(name);
    cnt.add(email);
    cnt.add(view);
    cnt.add(pic2);
    name.setText(person.getNom());
    email.setText(prix+"DT");
  //  pic.setIcon(person.getImg());
    return BorderLayout.center(cnt).
        add(BorderLayout.WEST, pic);
}
    
    
    
    
}
    

