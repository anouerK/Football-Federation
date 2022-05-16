/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.components.ImageViewer;
  import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;


import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;

import com.esprit.entities.Tournoi;
import com.esprit.entities.classement;

import com.esprit.services.ServiceClassement;
import com.esprit.services.ServiceTournoi;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author oumayma
 */
public class ListClassment extends Form {
  
/**
 *
 * @author oumayma
 */

 
    
     public ListClassment(Form previous,Tournoi x) {
       
  super("  Classement ",BoxLayout.y());
    

        System.out.println("IMAGE = "+x.getId());
          ArrayList<classement> cl;
  
        List<Tournoi> listtotal = ServiceTournoi.getInstance().fillinto();
       
    
     cl = ServiceClassement.getInstance().getAl(x.getId(), x);
       Form hp = new Form("     Clubs                             Points", new BorderLayout());
            // clubsmai.add(BorderLayout.CENTER,hp);
            add(hp);
        Container clubsmain =  new Container(BoxLayout.y());
       add(clubsmain);
         Container clubsmai =  new Container(new BorderLayout());
        // add(BorderLayout.CENTER,clubsmai);
      
     for ( classement f : cl) {
        Container clubx =  new Container(BoxLayout.x());
            String u= Statics.BASE_URL_K+"uploads/"+f.getClub().getLogo();
             // Form h = new Form( new BoxLayout(BoxLayout.X_AXIS));
            
                Style s1 = UIManager.getInstance().getComponentStyle("Button");
           FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s1);
             // Image placehold = Image.createImage(200, 200);
               EncodedImage placeholder =EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight()* 4), false);
              
          Image roundMask = Image.createImage(placeholder.getWidth(),
 placeholder.getHeight(), 0xff000000);
Graphics gr = roundMask.getGraphics();
gr.setColor(0xffffff);
gr.fillArc(0, 0, placeholder.getWidth(), placeholder.getHeight(), 0, 360);
         //   EncodedImage en = EncodedImage.createFromImage(placehold, false);
                EncodedImage en = EncodedImage.createFromImage(placeholder, false);
            Image urli = URLImage.createToStorage(en, f.getClub().getLogo(), u );
           urli = urli.fill(100,100);
            ImageViewer img = new ImageViewer();
            img.setImage(urli);
            
            
             SpanLabel sp2 = new SpanLabel("" + f.getClub().getNomc());
                  SpanLabel sp3 = new SpanLabel(" " + f.getClub().getLogo());
          SpanLabel sp4 = new SpanLabel(""+f.getPts());
           ArrayList<Map<String, Object>> dat = new ArrayList<>();
         dat.add(createListEntry1(sp2.getText()+"                     "+sp4.getText(),img));
               DefaultListModel<Map<String, Object>> mode = new DefaultListModel<>(dat);
MultiList m = new MultiList(mode);
m.getUnselectedButton().setIconName("icon_URLImage");
m.getSelectedButton().setIconName("icon_URLImage");

m.getUnselectedButton().setIcon(img.getImage());
m.getSelectedButton().setIcon(img.getImage());
      
 
 
   clubx.add(m);
   //clubx.add(table);
          clubsmain.add(clubx);
     }
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    this.show();
               //   System.out.println(cl);
     
      
     
}

   private Map<String, Object> createListEntry1(String date,
ImageViewer coverURL) {
 Map<String, Object> entry = new HashMap<>();
entry.put("Line1", date);
 // entry.put("Line2", dat);
 entry.put("icon_URLImage", coverURL);
 entry.put("icon_URLImageName", date);
 return entry;
} 
}
