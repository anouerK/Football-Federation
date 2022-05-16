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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.esprit.entities.Rewards;
import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceRewards;
import com.esprit.services.ServiceTournoi;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author oumayma
 */
public class EditReward extends Form{

/**
 *
 * @author oumayma
 */

  static    ArrayList<Tournoi>   tr;
     public EditReward(Form previous, Rewards ev) {
     
    
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
      
         
  
          Button btnValider = new Button("modifier reward");
               
                     TextField tfName = new TextField(""+ev.getRank());
        TextField tfdated= new TextField( ev.getTrophe());
         TextField tfdatef= new TextField(""+ev.getPrixR());
          TextField type= new TextField( ev.getImg());
        
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
     
          
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfName.getText().length()==0)||(tfdated.getText().length()==0)||(tfdatef.getText().length()==0)||(type.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 // Map<String,Object>obj;
               
                           int tournoi_id= typeReclamation.getSelectedStringIndex();
                          Tournoi trn = listtotal.get(tournoi_id);
                          
                 Tournoi tourno = new Tournoi(trn);
                      
                      // Rewards t = new Rewards(Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),type.getText().toString());
                       Rewards x  = new Rewards(ev.getId(),Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),type.getText().toString(),tourno);
                     
                    if( ServiceRewards.getInstance1().updateRewards(x))    
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
 add("Image :").add(type).
 add("Tournoi Asociée :").add(typeReclamation).
 
add(cn, btnValider);
         addAll(hi);
     
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
