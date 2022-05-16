/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import com.esprit.services.ServiceBadge;
import com.esprit.services.ServiceUser;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ksaay
 */
public class MainUser extends Form {
    public MainUser(Form previous)
    {
         
         super("list Users",new BorderLayout());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      Container b = BoxLayout.encloseY();
        b.setScrollableY(true);
         b.setScrollVisible(false);
            this.add(BorderLayout.CENTER, b);
//            this.add(BorderLayout.CENTER, b);
            refresh(b);
          
            
    }
    public void refresh(Container b)
        {
             
            
//this.add(BorderLayout.NORTH,p); 
        SpanLabel sp = new SpanLabel();
        
         ArrayList<User> list;
        list = new ArrayList<>();
        ArrayList<Button> buttons = null;
       
        
        list = ServiceUser.getInstance().getAll();
              
      
        
            EncodedImage enc = null;
            
Image imgs;
ImageViewer imgv;
String urli ;
Container elements = BoxLayout.encloseY();

         for ( User ev : list) {
             Container descrpuser = new Container(BoxLayout.y());
                 urli = Statics.BASE_URL + "uploads/img/"+ev.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(50,50);
              Label profilePicLabel = new Label(i, "UserPicture");
             
               
            
               
               
        Container buttonss = BoxLayout.encloseX();
       // b.setScrollableY(true);
       //  b.setScrollVisible(false);
              MultiButton tbl = new MultiButton("");
              Button update = new Button();
              Button delete = new Button();
             
                FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(update, FontImage.MATERIAL_UPDATE);
                buttonss.add(delete);
//tbl.setTextLine2('['+String.valueOf(ev.getId())+']'+ev.getUsername()+'['+String.valueOf(ev.getEmail())+']');
descrpuser.add(String.valueOf(ev.getId()));
descrpuser.add(String.valueOf(ev.getUsername()));
descrpuser.add(String.valueOf(ev.getEmail()));
tbl.setHorizontalLayout(false);
              FontImage.setMaterialIcon(tbl, FontImage.MATERIAL_STORAGE);
             
             
             b.add(descrpuser);
             b.add(buttonss);
             b.add(profilePicLabel);
            //    b.add(BorderLayout.center(descrpuser)
               //  .add(BorderLayout.EAST,buttonss)
              
                //.add(BorderLayout.OVERLAY,profilePicLabel)
                //);
               
               
     
     
delete.addActionListener((evt) ->{
   
      if( ServiceUser.getInstance().deleteUser(ev.getId())){
                   Dialog.show("Success","User Deleted","OK",null);
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
