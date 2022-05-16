/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.MultiButton;
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
import com.esprit.entities.Stade;
import com.esprit.services.ServiceStade;
import com.esprit.utils.Statics;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class AjoutStade extends Form {
     String path;

    private  String fileNameInServer =""; 
      String col;

    public AjoutStade(Form previous) {
        
        String[] characters3={"complet","en travaux","Olympique"};
      
        

        setTitle("Add a new Stade");
        setLayout(BoxLayout.y());

        TextField tfName = new TextField("", "name");
       //  tfName.setUIID("TextFieldBlack"); 
        TextField nbra = new TextField("", "nombre de capacité");
         //nbra.setUIID("TextFieldBlack"); 
        TextField lieu = new TextField("", "Lieu");
         //lieu.setUIID("TextFieldBlack"); 
     //   TextField etat = new TextField("", "Etat");
     MultiButton b5 = new MultiButton("Etat");
     b5.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < characters3.length ; iter++) {
        MultiButton mb = new MultiButton(characters3[iter]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b5.setTextLine1(mb.getTextLine1());
           
            col=mb.getTextLine1();
          
            System.out.println(col);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b5.revalidate();
        });
    }
    d.showPopupDialog(b5);
});
         //etat.setUIID("TextFieldBlack"); 
      //  TextField photo = new TextField("", "image");
         //photo.setUIID("TextFieldBlack"); 
        Button btnValider = new Button("Add Stade");
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
                                System.out.println("az"+ fileNameInServer);
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

        btnValider.addActionListener((e) -> {
            {
                try {
                    if ((tfName.getText().length() == 0) || (nbra.getText().length() == 0) || (lieu.getText().length() == 0) || (b5.getText().length() == 0)) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    } else {

                      //  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Stade t = new Stade(tfName.getText().toString(), Integer.parseInt(nbra.getText()), lieu.getText().toString(), col, fileNameInServer);
                        if (ServiceStade.getInstance().addStade(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                             new ListTaskStadeF(previous).show();    

                        } else {
                            Dialog.show("ERROR", "connn refuse", new Command("OK"));
                        }

                    }
                } catch (NumberFormatException x) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }

            }
        });

        addAll(tfName, nbra, lieu, b5, imgBtn, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

   

}
