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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Stade;
import com.esprit.gui.HomeForm;
import com.esprit.services.ServiceStade;
import com.esprit.utils.Statics;
import java.util.ArrayList;
 
/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ListTaskStade extends Form{
    
    Form current;
    Resources res;
      

        public ListTaskStade(Form previous) {
        
    
    
     setTitle("List Stade");
      Container c1 = new Container(BoxLayout.y());
       
        
         ArrayList<Stade> list;
        list = new ArrayList<>();
        //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceStade.getInstance().getAll();

        // k = ServiceTournoi.getInstance().getAll();
        for (Stade ev : list) {
           
                String url = Statics.BASE_URL_K+"/uploads/"+ev.getPhoto();
                System.out.println(url);
             Container club1_c = new Container(BoxLayout.y());
            Container clubm = new Container(BoxLayout.x());
             Image placeholder = Image.createImage(500,500);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getPhoto(), url);
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
            
            SpanLabel sp13 = new SpanLabel("   ");
            SpanLabel spl = new SpanLabel("Stade name: " + "  " + ev.getNoms());
            SpanLabel sp7 = new SpanLabel("nombre de capacité: " + "  " + ev.getNbrP());
            SpanLabel sp9 = new SpanLabel("Lieu: " + "  " + ev.getLieu());
            SpanLabel sp10 = new SpanLabel("Etat: " + "  " + ev.getEtat());
            SpanLabel sp1l = new SpanLabel("   ");
                
                
                
              clubm.add(imgV);
             
              club1_c.add(sp13).add(spl).add(sp7).add(sp9).add(sp10).add(sp1l);
           clubm.add(club1_c);
              
         
             c1.add(clubm);
        }
        this.add(c1);
        Resources res=UIManager.initFirstTheme("/theme");
      this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new ProfileForm(res).show());
      
    }
    
}
