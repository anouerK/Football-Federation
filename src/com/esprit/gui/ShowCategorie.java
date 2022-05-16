/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;



import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.services.ServiceMarques;
import com.esprit.entities.categories;
import java.util.ArrayList;
import com.codename1.ui.util.Resources;
import com.esprit.services.ServiceCategories;

/**
 *
 * @author Lord
 */
public class ShowCategorie extends Form {
    
     Form f;
  Container C2 = new Container(new BoxLayout (BoxLayout.Y_AXIS));
  
  
    
  public ShowCategorie(Form previous) {
      f=this;
      
      Toolbar.setGlobalToolbar(true);
      Style s = UIManager.getInstance().getComponentStyle("Categorie");
        //setTitle("List Categories");
     
       
        SpanLabel spa = new SpanLabel();
       ArrayList<categories> list=ServiceCategories.getInstance().parsecat();
       
       AutoCompleteTextField ac =new AutoCompleteTextField("aaa","bb");
       ac.setMinimumElementsShownInPopup(3);
       
   
       //f= new Form("Marques",BoxLayout.y());
       
        Button btnAdd=new Button("Add Categorie");
        btnAdd.addActionListener(e -> new AddCategorie(this).show());
        
        C2.add(btnAdd);
        
     for(categories m : list)
       {
           addmarque(m);
           
       }
      
//f.show(); 

addAll(C2,ac);

   
           
        
       
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
  
    
    }
    
    
    public void addmarque(categories m){
         
  
       
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
     ServiceCategories.getInstance().deletecat(m.getId());
       Dialog.show("Success","categorie deleted","OK",null);
});
mm.addActionListener((evt) ->{

     new updatecategorie(this,m).show();
     
     
});
   C1.addAll(b,mm);    
       
Label l = new Label(m.getTypec());
C2.addAll(l,C1);
  
  // f.add(C2);
//f.refreshTheme();
      
    
    }
    
}
