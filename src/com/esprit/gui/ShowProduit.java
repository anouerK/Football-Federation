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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.services.ServiceProduits;
import com.esprit.utils.Statics;
import com.esprit.entities.produit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lord
 */
public class ShowProduit extends Form  {
    
    Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  Container C = new Container(new BoxLayout (BoxLayout.X_AXIS));
  
  EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;
    
  public ShowProduit(Form previous) {
      f=this;
        setTitle("List Produits");
     
       
        SpanLabel spa = new SpanLabel();
       ArrayList<produit> list=ServiceProduits.getInstance().parseTasksP();
   
       f= new Form("Marques",BoxLayout.y());
       
        Button btnAdd=new Button("Add Produit");
        Button btnstat = new Button("Statistique");
        btnAdd.addActionListener(e -> new AddProduit(this).show());
        
        C.addAll(btnAdd,btnstat);
        
         EncodedImage enc = null;
            
  btnstat.addActionListener((evt)->new Statistique_Produit(this).show());
        
     for(produit m : list)
       {
           addmarque(m);
           
       }
      
//f.show(); 

addAll(C,C2);

   
           
        
       
      getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
  
    
    }
    
    
    public void addmarque(produit m){
         
  
       
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
     ServiceProduits.getInstance().deletep(m.getId());
       Dialog.show("Success","product deleted","OK",null);
});
mm.addActionListener((evt) ->{

     new UpdateProduit(this,m).show();
     C2.refreshTheme();
     
});
   C1.addAll(mm,b);   
        System.out.println(m.getImg());
    urli = Statics.BASE_URL + "/uploads/img/"+m.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(100,100);
              Label profilePicLabel = new Label(i, "badgePicture");
       
Label l = new Label(m.getNom());
C2.addAll(profilePicLabel,l,C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }

    
    
    
    private void addButton(String nomM, produit m) {
        
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
