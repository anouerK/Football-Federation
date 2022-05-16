/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

/**
 *
 * @author Ahmed.A.Hsouna
 */
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Game;
import com.esprit.entities.Tournoi;
import com.esprit.gui.HomeForm;
import com.esprit.services.ServiceGame;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author oumayma
 */
public class ListTaskGame extends Form {

    public Tournoi t;
    Form current;
    public static String nomt;
    // String url = "http://localhost/Federation-de-football/public/uploads";

    public ListTaskGame(Form previous) {
        
        setTitle("List Game");
        Container c1 = new Container(BoxLayout.y());
        
         ArrayList<Game> list;
        list = new ArrayList<>();
        //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceGame.getInstance1().getAll();

        // k = ServiceTournoi.getInstance().getAll();
        for (Game ev : list) {
           String url = Statics.BASE_URL_K+"/uploads/"+ev.getClub1().getLogo();
            System.out.println(url);
             Container c2 = new Container(BoxLayout.x());
             Container club1_c = new Container(BoxLayout.y());
            
             Image placeholder = Image.createImage(350,350);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
         //   System.out.print("IMAGE = "+ev.getClub1().getLogo());
            URLImage urlim = URLImage.createToStorage(enc, ev.getClub1().getLogo(), url );
         
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
              SpanLabel sp9 = new SpanLabel("  " + ev.getClub1().getNomc());
              SpanLabel sp19 = new SpanLabel("  " );
               SpanLabel r1score = new SpanLabel("   "+ev.getR1());
              SpanLabel placer = new SpanLabel(" ---- ");
                SpanLabel r2score = new SpanLabel("   "+ev.getR2());
                Container club2_c = new Container(BoxLayout.y());
                Image placeholder1 = Image.createImage(350,350);
            EncodedImage enc1 = EncodedImage.createFromImage(placeholder1, false); 
                URLImage urlim1 = URLImage.createToStorage(enc1, ev.getClub2().getLogo(), url + "/" +ev.getClub2().getLogo());
            ImageViewer imgV1 = new ImageViewer();
            imgV1.setImage(urlim1);
             SpanLabel sp10 = new SpanLabel("  " + ev.getClub2().getNomc());
                
                
              club1_c.add(imgV).add(sp9);
              c2.add(club1_c);
              c2.add(r1score);
              c2.add(placer);
               c2.add(r2score);
               club2_c.add(imgV1).add(sp10).add(sp19);
               c2.add(club2_c);
               
             /* Button btnView = new Button();
              btnView.addActionListener(e-> {
                  new HomeForm().show();
              });
              c2.setLeadComponent(btnView); */
             c1.add(c2); 
        }
        this.add(c1);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e ->  previous.showBack());
        }


     
}

