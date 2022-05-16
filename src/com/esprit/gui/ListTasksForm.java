/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;


import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Tournoi;

import com.esprit.utils.Statics;

import com.esprit.services.ServiceTournoi;
import java.util.ArrayList;



/**
 *
 * @author bhk
 */
public class ListTasksForm extends Form {



private Resources res=UIManager.initFirstTheme("/theme");
    public ListTasksForm(Form previous) {
         super("List Tournois",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
     
          Button add = new Button("ADD");
          add(add);
             add.addActionListener(( e)-> new AjoutTournoi(this).show() 
        
                
            
        );     
          
          
         ArrayList<Tournoi> list;
        list = new ArrayList<>();
        list = ServiceTournoi.getInstance().getAll();
         for ( Tournoi ev : list) {
             
              String url = Statics.BASE_URL_K+"/uploads/"+ev.getLogo();
              System.out.println("T ID = "+ev.getId()+" URL = "+url);
              Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getLogo(), url );
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("Tournoi name: " + "  " + ev.getNomt());
                SpanLabel sp5 = new SpanLabel("date debut: " + "  " + ev.getDated());
                 SpanLabel sp8 = new SpanLabel("date fin: " + "  " + ev.getDatef());
                   SpanLabel sp7 = new SpanLabel("type: " + "  " + ev.getTypet());
                     SpanLabel sp9 = new SpanLabel("nombre: " + "  " + ev.getNbrc());
                  SpanLabel sp6 = new SpanLabel("logo: " + "  " + ev.getLogo());
                  Button btnValide = new Button("supp tournoi");
                   Button btnValid = new Button("update tournoi");
                     TextField tfName = new TextField("","name");
        TextField tfdated= new TextField("", "date: yy-mm-dd");
         TextField tfdatef= new TextField("", "date: yy-mm-dd");
          TextField type= new TextField("", "type");
         TextField nbrc= new TextField("", "nombre");
         TextField logo= new TextField("", "logo");
                              btnValide.addActionListener(( e)-> {
            {  
               
             
                     ServiceTournoi.getInstance().deleteTournoi(ev.getId());
                  
                   new ListTasksForm(this).show();
                    
                
            
                        } 
                
            
        });     
                               btnValid.addActionListener(( e)-> new EditTournoi(this,ev).show() 
        
                
            
        );     
         addAll(sp0,spl,sp5,sp8,sp7,sp9,imgV,btnValide,btnValid);
         }
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->new ShowForm(new ProfileForm(res)).show());
        }
 private void addTab(Tabs swipe, Label spacer , String string, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
       
 }
}
