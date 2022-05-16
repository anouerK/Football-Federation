/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import static com.codename1.ui.layouts.BorderLayout.center;
import static com.codename1.ui.layouts.BorderLayout.centerAbsolute;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Article;
import com.esprit.entities.Interaction;
import com.esprit.entities.User;
import com.esprit.main.MyApplication;
import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceInteraction;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
/**
 *
 * @author ksaay
 */
public class ViewArticleFrontE extends Form{
     EncodedImage enc = null;
            
Image imgs;
ImageViewer imgv;
String urli ;
    public ViewArticleFrontE(Form previous,Article ev,Interaction in){
        super(ev.getTitre());
          
         this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
         Container elements = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container textarea = BoxLayout.encloseY();
         textarea.setScrollableY(true);
             elements.setScrollableY(true);
                 urli = Statics.BASE_URL + "uploads/img/"+ev.getImg();
              
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
               //  int mm = Display.getInstance().convertToPixels(6);
 // EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 6, mm * 8, 0), false);
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(1100,1700);
              Label profilePicLabel = new Label(i, "articlePicture");
            

             Label bottomSpace = new Label();
          //   SpanLabel articlet = new SpanLabel(ev.getTitre(), "WalkthruWhite");
             SpanLabel articledesc =   new SpanLabel(ev.getDescr()+".",  "WalkthruBody");
              Label usertext = new Label(ev.getUser().getUsername());
            Container tab1 = BorderLayout.centerAbsolute(BoxLayout.encloseY(
                    profilePicLabel,
              articledesc,
                bottomSpace));
             this.add(tab1);
               
               
               ArrayList<Interaction> listp=ServiceInteraction.getInstance().getAll(ev.getId(),ev);
               boolean valid = false;
             //  if(MyApplication.u_c.getId()!=0)
               //{
             
               for(Interaction likes : listp) {
    if(likes.getType().contains("up".toLowerCase()) && likes.getUser().getId()==MyApplication.u_c.getId())
    {
        valid = true;
    }
               }
               
               if(valid)
               {
                   Button liked = new Button("Dislike");
                   add(liked);
                   System.out.println("ey");
               }
               else
               {
                    Button liked = new Button("Like");
                   add(liked);
                   System.out.println("NO");
               }
   // }
                 Container commentBox = new Container(BoxLayout.x());
                 
          TextField commentb = new TextField(in.getDescrp(),"Your Comment");
          Button btnSave = new Button("Save");
          commentBox.add(commentb).add(btnSave);
          add(commentBox);
          btnSave.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (commentb.getText() == "")
        {
            Dialog.show("Error","Comment not valid","OK",null);
        }
        else
        {
            
            Interaction Commente= new Interaction(in.getIdi(),"comment",commentb.getText(),ev.getUser(),ev);
            
                if(ServiceInteraction.getInstance().EditInteraction(Commente))
                {
                      Dialog.show("Success", "Done", "OK", null);
                      commentb.setText("");
                }
                    
                
            
        }
    }
});
                Container list = new Container(BoxLayout.y());
     list.setScrollableY(true);
  

for(Interaction c : listp) {
    if(c.getType().contains("comment".toLowerCase()))
    {
        Container buttonss = BoxLayout.encloseX();
        String role_c = MyApplication.u_c.getRole();
              if(role_c == "admin" ||  MyApplication.u_c.getId() == c.getUser().getId())
              {
                  Button update = new Button();
              Button delete = new Button();
             
                FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
             
                FontImage.setMaterialIcon(update, FontImage.MATERIAL_UPDATE);
                buttonss.add(BorderLayout.west(update)
                 
              
                .add(BorderLayout.EAST,delete));
                delete.addActionListener((evt) ->{
   
      if( ServiceInteraction.getInstance().deleteInteraction(c.getIdi())){
                   Dialog.show("Success","comment Deleted","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
});
                Container xc = BoxLayout.encloseX();
                     xc.add(BorderLayout.west(createContactContainer(c)).add(BorderLayout.EAST,buttonss));
       list.add(FlowLayout.encloseIn(xc));
              }
              else
              {
                   list.add(FlowLayout.encloseIn(createContactContainer(c)));
              }
              
       
      
       for(Interaction reply : listp) {
          if(reply.getType().contains("reply?".toLowerCase()))
    {
        int reply_c = Integer.parseInt(reply.getType().substring(6));
        if(reply_c == c.getIdi())
        {
              
         Container buttonssr = BoxLayout.encloseX();
        String role_c_r = MyApplication.u_c.getRole();
              if(role_c =="admin" ||  MyApplication.u_c.getId() == reply.getUser().getId())
              {
                  Button updater = new Button();
              Button deleter = new Button();
             
                FontImage.setMaterialIcon(deleter, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(updater, FontImage.MATERIAL_UPDATE);
                buttonssr.add(BorderLayout.west(updater)
                 
              
                .add(BorderLayout.EAST,deleter));
                Container xc = BoxLayout.encloseX();
                deleter.addActionListener((evt) ->{
   
      if( ServiceInteraction.getInstance().deleteInteraction(reply.getIdi())){
                   Dialog.show("Success","comment Deleted","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
});
                     xc.add(BorderLayout.west(createContactContainer(reply)).add(BorderLayout.EAST,buttonssr));
       list.add(centerAbsolute(xc));
              }
              else
              {
                   list.add(centerAbsolute(createContactContainer(reply)));
                
              }
        }
      
       
    }  
           
       }
       
    }
    
    
   
}
add(list);
           //  this.add(profilePicLabel);
          
               // this.add(BorderLayout.center(elements).
      //  add(BorderLayout.WEST, profilePicLabel));
        
    } 
     private Container createContactContainer(Interaction i) {
          FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);
          MultiButton finishLandingPage = new MultiButton(" ["+i.getUser().getUsername()+"] "+i.getDescrp()+" ");
          if(i.getType().contains("comment".toLowerCase()))
       finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
         urli = Statics.BASE_URL + "uploads/img/"+i.getUser().getBadge().getLogoB();
         
              
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
               //  int mm = Display.getInstance().convertToPixels(6);
 // EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 6, mm * 8, 0), false);
                Image img_u = URLImage.createToStorage(enc, urli, urli);
                if(i.getType().contains("comment".toLowerCase()))
              img_u = img_u.fill(150,150);
                else
                       img_u = img_u.fill(140,140);
            //  Label profilePicLabel = new Label(img_u, "userPicture");
        finishLandingPage.setIcon(img_u);
        finishLandingPage.setIconUIID("Container");
   // SpanLabel name = new SpanLabel(i.getIdi()+);
  
   
  
    
    
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      if(i.getType().contains("comment".toLowerCase()))
    cnt.add(finishLandingPage);
      else
     cnt.add(finishLandingPage);
     
      
   
   
  //  pic.setIcon(person.getImg());
   return cnt;
  //return BorderLayout.center(cnt);
}
      private Image createCircleLine(Image i, int height) {
        i = i.fill(height,height);
        Graphics g = i.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
       // g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return i;
    }
}
