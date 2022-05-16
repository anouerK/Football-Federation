/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.codename1.l10n.SimpleDateFormat;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;


import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Arbitre;
import com.esprit.services.ServiceArbitre;
import com.esprit.utils.Statics;
import rest.file.uploader.tn.FileUploader;


/**
 *
 * @author Ahmed.A.Hsouna
 */
public class AjoutArbitre extends Form{
      String path;

    private  String fileNameInServer =""; 
    
     public AjoutArbitre(Form previous) {
         
         
    
    
     setTitle("Add a new Arbitre");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","name");
        TextField nbra= new TextField("", "nombre experience");
          TextField descrp= new TextField("", "description");
         
      //   TextField image= new TextField("", "image");
        
         Button btnValider = new Button("Add Arbitre");
        Button imgBtn = new Button("Add Image");
        
        imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                                try{
                  
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if(v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String)v.getSource()).substring(7);
                            System.out.println(filePath);
                            
                            try {
                                  FileUploader fu = new FileUploader(Statics.URL_UPLOAD);
                                fileNameInServer = fu.upload(filePath);
                                System.out.println("aaa"+  fileNameInServer);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }, Display.GALLERY_IMAGE);
                    
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                            
                         }
        });
        
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfName.getText().length()==0)||(nbra.getText().length()==0)||(descrp.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 
                        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                        Arbitre t = new Arbitre( tfName.getText().toString(),Integer.parseInt(nbra.getText()),descrp.getText().toString(), fileNameInServer);
                    if( ServiceArbitre.getInstance().addArbitre(t))
                    {
                         //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme has been added! Check it out");
                      //  ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10 , LocalNotification.REPEAT_NONE);
                        
                        
                        Display.getInstance().scheduleLocalNotification(
    ln,  
    System.currentTimeMillis(), 
    LocalNotification.REPEAT_MINUTE
 );
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                           new ListTaskArbitreF(previous).show();
                        
                    }else{
                         Dialog.show("ERROR", "connn refuse", new Command("OK"));
                    }  
                    
                }
                } catch (NumberFormatException x) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                
            }
        });
        
        
        addAll(tfName,nbra,descrp,imgBtn,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }

    
}
