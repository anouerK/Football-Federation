/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ToastBar;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.esprit.utils.Statics;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceTournoi;
import rest.file.uploader.tn.FileUploader;
/**
 *
 * @author oumayma
 */
public class AjoutTournoi extends Form {
  private String fileNameInServer = "";
    public AjoutTournoi(Form previous) {
    
  
     setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
        
        TextField tfName = new TextField("","name");
        TextField tfdated= new TextField("", "date: yy-mm-dd");
        Picker dateReclamation = new Picker();
        dateReclamation.setType(Display.PICKER_TYPE_DATE);
        dateReclamation.setUIID("TextFieldBlack");
       // addStringValue("Date Reclamation", dateReclamation);
        Picker datef = new Picker();
        datef.setType(Display.PICKER_TYPE_DATE);
        datef.setUIID("TextFieldBlack");
         TextField tfdatef= new TextField("", "date: yy-mm-dd");
          TextField type= new TextField("", "type");
         TextField nbrc= new TextField("", "nombre");
    
        Button btnValider = new Button("Add tournoi");
              tfName.getAllStyles().setFgColor(0x607D8B);
       tfdated.getAllStyles().setFgColor(0x607D8B);
         tfdatef.getAllStyles().setFgColor(0x607D8B);
           type.getAllStyles().setFgColor(0x607D8B);
           
           nbrc.getAllStyles().setFgColor(0x607D8B);
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
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfName.getText().length()==0)||(type.getText().length()==0)||(nbrc.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 
                        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                        Tournoi t = new Tournoi( tfName.getText().toString(), format.format(dateReclamation.getDate()), format.format(datef.getDate()),type.getText().toString(),Integer.parseInt(nbrc.getText()),fileNameInServer );
                    if( ServiceTournoi.getInstance().addTournoi(t))
                    {
// alert.dispose();
                        ToastBar.getInstance().setPosition(TOP);
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("Tournoi ajoutée "+t.getNomt());
                        status.setExpires(10000);
                        status.show();

                        refreshTheme();
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
        
                 // TextField logo= new TextField("", fileNameInServer);
  
        addAll(tfName,dateReclamation,datef,type,nbrc,imgBtn,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }
     public void localNotificationReceived(String notificationId) {
 System.out.println("Received local notification "+notificationId);
 }

}
    
               
