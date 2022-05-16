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
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
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
import com.esprit.entities.Badge;
import com.esprit.services.ServiceBadge;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ksaay
 */
public class ListBadge extends Form {
    
        
        public ListBadge(Form previous,Resources res)
    {
         
        super("list Badges",new BorderLayout());
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
      Container b = BoxLayout.encloseY();
        b.setScrollableY(true);
         b.setScrollVisible(false);
            this.add(BorderLayout.CENTER, b);
        Container topp = BoxLayout.encloseY();
 topp.setScrollableY(true);
         topp.setScrollVisible(false);
           String[] characters = { "none","","nb Asc", "","nb Desc","","Badge Name ASC", "","Badge Name DESC"};
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
               display(-1,searchbar.getText(),b); 
             // System.out.println(searchbar.getText());
            }
        });
 //topp.add(BorderLayout.WEST,searchbar);
   topp.add(BorderLayout.west(p).add(BorderLayout.EAST,searchbar));
   
 this.add(BorderLayout.NORTH,topp);
      display(0,"",b);   
     
    
    }
        public void display(int orderby,String search,Container b)
        {
             
            
b.removeAll();
//this.add(BorderLayout.NORTH,p); 
        SpanLabel sp = new SpanLabel();
        
         ArrayList<Badge> list;
        list = new ArrayList<>();
        ArrayList<Button> buttons = null;
        if(orderby != -1)
        list = ServiceBadge.getInstance().getAll(orderby);
        else
        list = ServiceBadge.getInstance().getAllS(search);
        
        
      
        
            EncodedImage enc = null;
            
Image imgs;
ImageViewer imgv;
String urli ;
Container elements = BoxLayout.encloseY();

         for ( Badge ev : list) {
             
                 urli = Statics.BASE_URL + "uploads/img/"+ev.getLogoB();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(100,100);
              Label profilePicLabel = new Label(i, "badgePicture");
             
               
            
               
               
        Container buttonss = BoxLayout.encloseX();
       // b.setScrollableY(true);
       //  b.setScrollVisible(false);
              MultiButton tbl = new MultiButton("");
              Button update = new Button();
              Button delete = new Button();
             
                FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
                FontImage.setMaterialIcon(update, FontImage.MATERIAL_UPDATE);
                buttonss.add(BorderLayout.west(update)
                 
              
                .add(BorderLayout.EAST,delete));
tbl.setTextLine2('['+String.valueOf(ev.getId())+']'+ev.getNomB()+'['+String.valueOf(ev.getNb())+']');


tbl.setHorizontalLayout(false);
              FontImage.setMaterialIcon(tbl, FontImage.MATERIAL_STORAGE);
             
             
             
                b.add(BorderLayout.center(tbl)
                 .add(BorderLayout.EAST,buttonss)
              
                .add(BorderLayout.OVERLAY,profilePicLabel)
                );
               
               
       update.addActionListener((evt) ->{
    
     new UpdateBadge(this,ev).show();
    
});
     
delete.addActionListener((evt) ->{
   
      if( ServiceBadge.getInstance().deleteBadge(ev.getId())){
                   Dialog.show("Success","Badge Deleted","OK",null);
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

   

    private void addButton(String nomB, int nb, String logoB) {
       Container cnt = new Container();
TextArea ta = new TextArea(nomB);
ta.setUIID("newsTopLine");
ta.setEditable(false);
cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));
add(cnt);
    }

    
}
