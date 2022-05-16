/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.getDisplayHeight;
import static com.codename1.ui.CN.getDisplayWidth;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.User;
import com.esprit.main.MyApplication;
import com.esprit.utils.Statics;
import java.io.IOException;

/**
 *
 * @author ksaay
 */
public class UserProfile extends Form{
    public UserProfile(Form previous,Resources res)
    {
        setUIID("LoginForm");
     setLayout(BoxLayout.y());
            getTitleArea().setUIID("Container");
      //  super("Profile");
       // setUIID("LoginForm");
    
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
         Image imgs;
ImageViewer imgv;
String urli ;
String urlb ;
EncodedImage enc = null;
  urli = Statics.BASE_URL + "uploads/img/"+MyApplication.u_c.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {

             }
              int mm = Display.getInstance().convertToPixels(6);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 12, mm * 24, 0), false);
                    
                Image profilePic = URLImage.createToStorage(placeholder, urli, urli);
                 profilePic = profilePic.fill(450,350);
        
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight());
        profilePic = profilePic.fill(mask.getWidth()*2, mask.getHeight()*2);
         String User_connected ;
         if(MyApplication.u_c.getId()!= 0)
        User_connected= MyApplication.u_c.getUsername();
         else
             User_connected = "Visitor";
        Label ProfileUser = new Label(User_connected);
        Label profilePicLabel = new Label("  ", profilePic, "SideMenuTitle");
        
        profilePicLabel.setMask(mask.createMask());   
        Container sidemenuTop = BoxLayout.encloseX();
        urlb = Statics.BASE_URL + "uploads/img/"+MyApplication.u_c.getBadge().getLogoB();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {

             } 
             Button orders = new Button("ORDERS");
             Button Edit = new Button("Edit");
             
             sidemenuTop.add(ProfileUser).add(profilePicLabel).add(orders).add(Edit);
            
  EncodedImage placeholderb = EncodedImage.createFromImage(Image.createImage(mm * 6, mm * 8, 0), false);
                    
                Image badgepic = URLImage.createToStorage(placeholderb, urlb, urlb);
                 badgepic = badgepic.fill(200,200);
       // badgepic = badgepic.fill(mask.getWidth(), mask.getHeight());
      
        
        Label BadgeLabel = new Label( badgepic, "SideMenuTitle");
      //   Container Badgec = BorderLayout.center(BadgeLabel);
           Container BadgeInfo = FlowLayout.encloseCenter(
                new Label(" ", MyApplication.u_c.getBadge().getNomB()),
            BadgeLabel
        );
      
       orders.addActionListener((evt) ->{
    
     new ShowCommande(this,0).show();
    
});
         Edit.addActionListener((evt) ->{
    
     new UpdateProfileUser(this).show();
    
});
       add(BorderLayout.center(sidemenuTop));
       add(BorderLayout.center(BadgeInfo));
   //    
        
        
    }
    
}
