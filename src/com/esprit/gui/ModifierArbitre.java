
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Arbitre;
import com.esprit.entities.Stade;
import com.esprit.services.ServiceArbitre;
import com.esprit.services.ServiceStade;

/**
 *
 * @author emnah
 */
public class ModifierArbitre extends Form{
    Form current;
   

  
        
        
         ModifierArbitre(Arbitre d) {
        setTitle("update theme");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(d.getId()), "id");
        //tfID.setVisible(false);
        TextField nom = new TextField(d.getNomA(), "Nom", 20, TextArea.ANY);
       TextField nombre = new TextField(String.valueOf(d.getNbe()), "Nombre d'experience", 20, TextArea.ANY);
       TextField descriptionEvent = new TextField(d.getDescrp(), "description", 20, TextArea.ANY);
        //TextField lieuEvent = new TextField(d.getEtat(), "Etat", 20, TextArea.ANY);
        TextField imageEvent = new TextField(String.valueOf(d.getImage()), "Image", 20, TextArea.ANY);

     

        
        
        

        Button btnSubmit = new Button("Modifier");
        Button cancel = new Button("Annuler");
        btnSubmit.addActionListener(new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent evt) {
               if ((nom.getText().length() == 0) || (nombre.getText().length() == 0)||(descriptionEvent.getText().length() == 0) ||  (imageEvent.getText().length() == 0) ) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Arbitre d = new Arbitre(Integer.parseInt((tfID.getText())),nom.getText(), Integer.parseInt((nombre.getText())), descriptionEvent.getText(),imageEvent.getText());
                    if (ServiceArbitre.getInstance().ModifierA(d)) {
                          Dialog.show("Success", "Arbitre modifié ", new Command("OK"));
                           
                       iDialog.dispose();
                        //refreshTheme();
                        
                        
                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme"+ d.getNomA()+ "has been added! Check it out");
                      //  ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 , LocalNotification.REPEAT_NONE);
                        
                        
                        Display.getInstance().scheduleLocalNotification(
    ln,  
    System.currentTimeMillis(), 
    LocalNotification.REPEAT_MINUTE
 );
                        
                        //load after submuitting
                        new ListTaskArbitreF(current).show();
                       

                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));

                    }

                }
            }
        });
       /* cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Cancel");

            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new ListTasksForm(current);
            };

        }); */
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListTaskStade(current).show();

        });

        addAll(tfID,nom,nombre,descriptionEvent,imageEvent, btnSubmit,btnAnnuler);
    }

}

