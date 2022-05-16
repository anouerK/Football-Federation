/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.Rewards;
import com.esprit.entities.Tournoi;

import com.esprit.services.ServiceRewards;
import com.esprit.services.ServiceTournoi;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import rest.file.uploader.tn.FileUploader;
/**
 *
 * @author oumayma
 */
public class addRewards extends Form {
    static    ArrayList<Tournoi>   tr;
      private String fileNameInServer = "";
     public addRewards(Form previous) {
    
  //  super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

     setTitle("Add a new reward");
        //setLayout(BoxLayout.y());
          Form hi = new Form(new BoxLayout(BoxLayout.Y_AXIS));

     TableLayout tl;
int spanButton = 2;
if(Display.getInstance().isTablet()) {
 tl = new TableLayout(7, 2);
} else {
 tl = new TableLayout(14, 1);
 spanButton = 1;
}
tl.setGrowHorizontally(true);
hi.setLayout(tl);
              Button btnValider = new Button("ajout reward");
               
                     TextField tfName = new TextField("","rank");
        TextField tfdated= new TextField("", "trophee");
         TextField tfdatef= new TextField("", "prix");
          TextField type= new TextField("", "img");
        Button imgBtn = new Button("parcourir");
       // addStringValue("", imgBtn);

        
        imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    FileUploader fu = new FileUploader(Statics.URL_UPLOAD);

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if (v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String) v.getSource()).substring(7);
                            System.out.println(filePath);

                            try {
                                fileNameInServer = fu.upload(filePath);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
   
            }
        });
      tr = new ArrayList<>();
    
        
        tr = ServiceTournoi.getInstance().getAll();
        
        Picker typeReclamation = new Picker();
   typeReclamation.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi = ServiceTournoi.getInstance().fillintot();
        List<Tournoi> listtotal = ServiceTournoi.getInstance().getAll();
        String[] tournoisnames = listtournoi.toArray(new String [listtournoi.size()]);
    typeReclamation.setUIID("TextFieldBlack");
        
            typeReclamation.setStrings(tournoisnames);
        
        //typeReclamation.addString();
        typeReclamation.setSelectedStringIndex(0);
      //  addStringValue("tournoi", typeReclamation);
        
        //  for ( Tournoi ev : k) {
            
       // typeReclamation.setStrings(ev.getNomt());
       // typeReclamation.setUIID("TextFieldBlack");
        //typeReclamation.setSelectedString("sélectionnez un typeReclamation");
        
      // ComboBox   sp1 = new ComboBox();
             // sp1.setSelectedItem(ev.getNomt());
         //this.t=t.getNomt();
        //TextField flogo= new   TextField(  this.k.getNomt()+"");
            
          
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfName.getText().length()==0)||(tfdated.getText().length()==0)||(tfdatef.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 // Map<String,Object>obj;
               
                           int tournoi_id= typeReclamation.getSelectedStringIndex();
                          Tournoi trn = listtotal.get(tournoi_id);
                          
                 Tournoi tourno = new Tournoi(trn);
                      
                      // Rewards t = new Rewards(Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),type.getText().toString());
                       Rewards x  = new Rewards(Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),fileNameInServer,tourno);
                     
                    if( ServiceRewards.getInstance1().addRewards(x))    
                    {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                            new ListRewardsForm(this).show();
                        
                    }else{
                         Dialog.show("ERROR", "connn refuse", new Command("OK"));
                    }  
                    
                
                }
                } catch (NumberFormatException x) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
            }     
                
            
        });
        
         
        
           // addAll(tfName,tfdated,tfdatef,type,typeReclamation,btnValider);
            TableLayout.Constraint cn = tl.createConstraint();
cn.setHorizontalSpan(spanButton);
cn.setHorizontalAlign(Component.RIGHT);
            hi.add("Rank :").add(tfName).
 add("Trophe :").add(tfdated).
 add("Prix :").add(tfdatef).
 add("Image :").add(imgBtn).
 add("Tournoi Asociée :").add(typeReclamation).
 
add(cn, btnValider);
          addAll(hi);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
     }
          
          
    
          
    /*
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }*/

}
