/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.esprit.services.ServiceCommandes;
import com.esprit.entities.commande;
import java.util.ArrayList;
import com.esprit.main.MyApplication;
import com.esprit.entities.User;
import com.esprit.entities.panier;

/**
 *
 * @author Lord
 */
public class Verif extends Form  {
     LocalNotification n = new LocalNotification();
     
    public Verif(String x,User u ) 
            {
                
               
                setTitle("Verif");
                Button valide = new Button("Validate");
                TextField  tf = new TextField("","Enter Your Code");
                int y = Integer.parseInt(x);
                
                
                valide.addActionListener((evt)->{
                    int z = Integer.parseInt(tf.getText());
                    //int i=0;
                    if(y==z)
                    {
                         // ArrayList<commande> list=ServiceCommandes.getInstance().parseTaskscom();
                       for(panier p : MyApplication.pan)
                       {
                           
                           commande c  = new commande(u,p.getProd(),p.getPrix(),p.getQte(),1,p.getTaille());
                           
                              if(ServiceCommandes.getInstance().addcommande(c)){
                   Dialog.show("Success","Badge Added","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
                           
                           
                           
                           
                       }
                       
                       
                       MyApplication.pan.clear();
 
                   }
                    
                });
              
                
                System.out.println(y);
                
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->new Cart(new BaseFront()).show());
addAll(tf,valide);
                
            }
           

}
