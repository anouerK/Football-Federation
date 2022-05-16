/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;


import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Rewards;

import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceRewards;
import com.esprit.utils.Statics;
import java.util.ArrayList;
/**
 *
 * @author oumayma
 */
public class ListRewardsForm extends Form{
    public Tournoi t;
     Form current;
      public static String nomt;
       private Resources res=UIManager.initFirstTheme("/theme");
     public ListRewardsForm(Form previous) {
       

     Container lis = new Container(BoxLayout.y());
 Container c = new Container(BoxLayout.x());
     String[] characters = { "Prix Croissant", "Prix Decroissant"};
 setTitle("List Rewards");
            setLayout(BoxLayout.y());
             Button add = new Button("ADD");
          add(add);
             add.addActionListener(( e)-> new addRewards(this).show() 
        
                
            
        );   
           
   
  //SpanLabel sp = new SpanLabel();
         ArrayList<Rewards> list;
        list = new ArrayList<>();
      //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceRewards.getInstance1().getAll();
        
   MultiButton b = new MultiButton("Tri");
   add(b);
       // k = ServiceTournoi.getInstance().getAll();
         for ( Rewards ev : list) {
              String url = Statics.BASE_URL_K+"/uploads/"+ev.getImg();
              System.out.println(url);
              Image placeholder = Image.createImage(350, 350);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getImg(), url );
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("Rank: " + "  " + ev.getRank());
                SpanLabel sp5 = new SpanLabel("Trophe: " + "  " + ev.getTrophe());
                 SpanLabel sp8 = new SpanLabel("Prix: " + "  " + ev.getPrixR());
                   SpanLabel sp7 = new SpanLabel("Image: " + "  " + ev.getImg());
                     SpanLabel sp9 = new SpanLabel("Tournoi nom: " + "  " + ev.getT().getNomt());
                 
                  Button btnValide = new Button("supp ");
                   Button btnValid = new Button("update ");
             
      
b.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < characters.length ; iter++) {
        MultiButton mb = new MultiButton(characters[iter]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            if(mb.getTextLine1()=="Prix Croissant")
            {
                System.out.println("cccccc");
                removeAll();
                
               ArrayList<Rewards>  listp=ServiceRewards.getInstance1().TriRewardA();
                 
                 for(Rewards t : listp) {
                URLImage urli = URLImage.createToStorage(enc, t.getImg(), url );
            ImageViewer img = new ImageViewer();
            img.setImage(urli);
             SpanLabel sp = new SpanLabel("id: " + "  " + t.getId());
              SpanLabel spl0 = new SpanLabel("Rank: " + "  " + t.getRank());
                SpanLabel sp51 = new SpanLabel("Trophe: " + "  " + t.getTrophe());
                 SpanLabel sp80 = new SpanLabel("Prix: " + "  " + t.getPrixR());
                   SpanLabel sp75 = new SpanLabel("Image: " + "  " + t.getImg());
                     SpanLabel sp97 = new SpanLabel("Tournoi nom: " + "  " + t.getT().getNomt());
                  addAll(sp,spl0,sp51,sp80,sp97,img);
                  
                      }
                
                
                
            }
            else if (mb.getTextLine1()=="Prix Decroissant")
            {
                System.out.println("ddddd");
               removeAll();
                
               ArrayList<Rewards>   listp=ServiceRewards.getInstance1().TriRewardD();
                 
                  for(Rewards t : listp) {
                    URLImage urli = URLImage.createToStorage(enc, t.getImg(), url );
            ImageViewer img = new ImageViewer();
            img.setImage(urli);
             SpanLabel sp = new SpanLabel("id: " + "  " + t.getId());
              SpanLabel spl0 = new SpanLabel("Rank: " + "  " + t.getRank());
                SpanLabel sp51 = new SpanLabel("Trophe: " + "  " + t.getTrophe());
                 SpanLabel sp80 = new SpanLabel("Prix: " + "  " + t.getPrixR());
                   SpanLabel sp75 = new SpanLabel("Image: " + "  " + t.getImg());
                     SpanLabel sp97 = new SpanLabel("Tournoi nom: " + "  " + t.getT().getNomt());
                  addAll(sp,spl0,sp51,sp80,sp97,img);
                  
                      }
                
            }
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    d.showPopupDialog(b);
});

   
                              btnValide.addActionListener(( e)-> {
            {  
               
             
                     ServiceRewards.getInstance1().deleteRewards(ev.getId());
                  
                  
                     new ListRewardsForm(this).show();
                
            
                        } 
                
            
        });     
                               btnValid.addActionListener(( e)->   new EditReward(this,ev).show() );     
                    //  Rewards t = new Rewards(Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),type.getText().toString(),t.getT().getNomt());
                     // t.setId(ev.getId());
                     // ServiceRewards.getInstance().updateTournoi(t);
                  
          
            
        
       addAll(sp0,spl,sp5,sp8,sp9,imgV,btnValide,btnValid);
         }
        
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show());
        }
   
    
}