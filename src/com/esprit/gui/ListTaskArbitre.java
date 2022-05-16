/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Arbitre;
import com.esprit.gui.HomeForm;

import com.esprit.services.ServiceArbitre;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ListTaskArbitre extends Form {
  //  String url = "http://localhost/Federation-de-football/public/uploads";
 
    public ListTaskArbitre(Form previous) {

        setTitle("List Arbitre");
       
       Container c1 = new Container(BoxLayout.y());
        
         ArrayList<Arbitre> list;
        list = new ArrayList<>();
        //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceArbitre.getInstance().getAll();

        // k = ServiceTournoi.getInstance().getAll();
        for (Arbitre ev : list) {
           String url = Statics.BASE_URL_K+"/uploads/"+ev.getImage();
            System.out.println(url);
             Container club1_c = new Container(BoxLayout.y());
            Container clubm = new Container(BoxLayout.x());
            Image placeholder = Image.createImage(500,500);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getImage(), url );
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
            
            SpanLabel sp13 = new SpanLabel("   ");
           SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
            SpanLabel spl = new SpanLabel("Arbitre name: " + "  " + ev.getNomA());

            SpanLabel sp7 = new SpanLabel("nombre d'experience: " + "  " + ev.getNbe());
            SpanLabel sp9 = new SpanLabel("description: " + "  " + ev.getDescrp());
            
            SpanLabel sp1l = new SpanLabel("   ");
                
                
                
              clubm.add(imgV);
             
              club1_c.add(sp13).add(spl).add(sp7).add(sp9).add(sp1l);
           clubm.add(club1_c);
              
               
              Button btnView = new Button();
              btnView.addActionListener(e-> {
                  new HomeForm().show();
              });
                SpanLabel sp12 = new SpanLabel("   ");
              club1_c.setLeadComponent(btnView);
             c1.add(clubm).add(sp12);
        }
        this.add(c1);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new HomeForm().show());
    }

}
