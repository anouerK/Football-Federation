/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.services.ServiceMarques;
import com.esprit.entities.marques;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;

/**
 *
 * @author bhk
 */
public class ShowForm extends Form{
  Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
    
  public ShowForm(Form previous) {
      f=this;
        setTitle("List marques");
     
       
        SpanLabel spa = new SpanLabel();
       ArrayList<marques> list=ServiceMarques.getInstance().parseTasks();
   
       f= new Form("Marques",BoxLayout.y());
       
        Button btnAdd=new Button("Add Marque");
        btnAdd.addActionListener(e -> new AddForm(this).show());
        
        C2.add(btnAdd);
        
     for(marques m : list)
       {
           addmarque(m);
           
       }
      
//f.show(); 

addAll(C2);

   
           
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  
    
    }
    
    
    public void addmarque(marques m){
         
  
       
        ImageViewer img=null;
       // Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       /*try{
            img = new ImageViewer(Image3createImage(marques.getImg()));
        }catch(IOException ex)
        {
        }*/
        Container C1 = new Container(new BoxLayout (BoxLayout.X_AXIS));
       
Button b = new Button("remove");
Button mm = new Button("update");

b.addActionListener((evt) ->{
     ServiceMarques.getInstance().deletemarque(m.getId());
     Dialog.show("Success","Marque deleted","OK",null);
});
mm.addActionListener((evt) ->{

     new updateform(this,m).show();
     
     
});
   C1.addAll(b,mm);    
       
Label l = new Label(m.getNomM());
C2.addAll(l,C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }

    
    
    
    private void addButton(String nomM, marques m) {
        
        int height=Display.getInstance().convertToPixels(11.5f);
         int width=Display.getInstance().convertToPixels(14f);
         
   
        Container cnt = BorderLayout.west(null);
     TextArea ta=new TextArea(nomM);
     ta.setUIID("NewsTopLine");
     ta.setEditable(false);
     cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(ta));
     
     add(cnt);
    }
    
    
}
