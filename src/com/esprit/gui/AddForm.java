/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.marques;
import com.esprit.services.ServiceMarques;

/**
 *
 * @author Lord
 */
public class AddForm extends Form{

    public AddForm(Form previous) {
        setTitle("Add Marque");
        setLayout(BoxLayout.y());
        TextField tfName=new TextField("","Marque name");
      
        
       
      
       Button btnValider = new Button("Add marque");
      
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (tfName.getText() == "")
        {
            Dialog.show("Error","Marque Name not valid","OK",null);
        }
     
            else
            {
                
                marques badge = new marques(tfName.getText());
               if(ServiceMarques.getInstance().addTask(badge)){
                   Dialog.show("Success","Marque Added","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            }
        
    
});
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
addAll(tfName,btnValider);
        
        
        
    }
    
}
