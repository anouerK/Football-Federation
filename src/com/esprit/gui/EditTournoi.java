/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceTournoi;
import java.util.ArrayList;
/**
 *
 * @author oumayma
 */
public class EditTournoi extends Form {
 
     public  EditTournoi(Form previous, Tournoi ev) {
     
    
     setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
      
         
  
        TextField tfName = new TextField(ev.getNomt());
        TextField tfdated= new TextField(ev.getDated());
         TextField tfdatef= new TextField(ev.getDatef());
          TextField type= new TextField(ev.getTypet());
         TextField nbrc= new TextField(String.valueOf(ev.getNbrc()));
         TextField logo= new TextField(ev.getLogo());
      
           tfName.getAllStyles().setFgColor(0x607D8B);
       tfdated.getAllStyles().setFgColor(0x607D8B);
         tfdatef.getAllStyles().setFgColor(0x607D8B);
           type.getAllStyles().setFgColor(0x607D8B);
           logo.getAllStyles().setFgColor(0x607D8B);
           nbrc.getAllStyles().setFgColor(0x607D8B);
             Button btnValider = new Button("update tournoi"); 
         
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfName.getText().length()==0)||(tfdated.getText().length()==0)||(tfdatef.getText().length()==0)||(type.getText().length()==0)||(nbrc.getText().length()==0)||(logo.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 
                        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                        Tournoi t = new Tournoi( ev.getId(),tfName.getText().toString(),tfdated.getText(),tfdatef.getText(),type.getText().toString(),Integer.parseInt(nbrc.getText().toString()),logo.getText().toString());
                    if( ServiceTournoi.getInstance().updateTournoi(t))
                    {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        
                    }else{
                         Dialog.show("ERROR", "connn refuse", new Command("OK"));
                    }  
                    
                }
                } catch (NumberFormatException x) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                new ListTasksForm(this).show();
            }
        });
        
         
        addAll(tfName,tfdated,tfdatef,type,nbrc,logo,btnValider);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}
