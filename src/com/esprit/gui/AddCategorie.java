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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.esprit.services.ServiceCategories;
import com.esprit.entities.categories;

/**
 *
 * @author Lord
 */
public class AddCategorie extends Form {
    
    public AddCategorie(Form previous) {
        
        setTitle("Add Categories");
        setLayout(BoxLayout.y());
        TextField tfName=new TextField(""," Categorie");
        
       
      
       Button btnValider = new Button("Add Categorie");
      
        
         btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (tfName.getText() == "")
        {
            Dialog.show("Error","Categorie Name not valid","OK",null);
        }
     
            else
            {
                
                categories badge = new categories(tfName.getText());
                
               if(ServiceCategories.getInstance().addcategorie(badge)){
                   Dialog.show("Success","Categorie Added","OK",null);
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
