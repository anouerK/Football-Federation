/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.esprit.entities.Rewards;
import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceRewards;
import com.esprit.services.ServiceTournoi;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author oumayma
 */
public class TournoiFront  extends Form{
   String fileNameInServer="";
       // Form hi = new Form("MultiList", new BorderLayout());
        static    ArrayList<Rewards>   l;
        Tournoi r;
     public TournoiFront (Form previous) {
           super("List Tournois",BoxLayout.y());
     ArrayList<Tournoi> list;
        list = new ArrayList<>();
        list = ServiceTournoi.getInstance().getAll();

/*Toolbar.setGlobalToolbar(true);
Style mi = UIManager.getInstance().getComponentStyle("Title");

  Form hio = new Form("Toolbar", new BoxLayout(BoxLayout.Y_AXIS));
 TextField searchField = new TextField( "Toolbar Search");
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);
          
          
FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH,mi
 );
*/

           /*  hio.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
Tournoi y=new Tournoi(ev.getId(),ev.getNomt());
  ServiceTournoi.getInstance().RechercheT(y);
});*/
         
 
          // Form hi =new Form( new BorderLayout()); 

   Container lis = new Container(BoxLayout.y());
         revalidate();
 getToolbar().addSearchCommand(e -> {
     

String text = (String)e.getSource();
 if( text.length() == 0) {
 // clear search
   text="";
                lis.removeAll();
                ArrayList<Tournoi> l;
     //   l = new ArrayList<>();
 //       l = ServiceTournoi.getInstance().getAll();
          ArrayList<Tournoi> t=ServiceTournoi.getInstance().getAll();
        for(Tournoi r :t)
        {
 Tournoi y=new Tournoi(r.getId(),r.getNomt());

    ArrayList<Map<String, Object>> dat = new ArrayList<>();
    SpanLabel spl = new SpanLabel(  r.getNomt());
                SpanLabel sp5 = new SpanLabel( r.getDated());
                 ImageViewer imgV = new ImageViewer();
       dat.add(createListEntry(spl.getText(),sp5.getText(), imgV));
               DefaultListModel<Map<String, Object>> mode = new DefaultListModel<>(dat);
MultiList m = new MultiList(mode);
m.getUnselectedButton().setIconName("icon_URLImage");
m.getSelectedButton().setIconName("icon_URLImage");

m.getUnselectedButton().setIcon(imgV.getImage());
m.getSelectedButton().setIcon(imgV.getImage());
      
   lis.add( m);
        }
                
 } else {
 lis.removeAll();
 text = text.toLowerCase();
      System.out.print(text);
   ArrayList<Tournoi> l;
     //   l = new ArrayList<>();
 //       l = ServiceTournoi.getInstance().getAll();
          ArrayList<Tournoi> t=ServiceTournoi.getInstance().RechercheT(text);
        for(Tournoi r :t)
        {
 Tournoi y=new Tournoi(r.getId(),r.getNomt());

    ArrayList<Map<String, Object>> dat = new ArrayList<>();
    SpanLabel spl = new SpanLabel(  r.getNomt());
                SpanLabel sp5 = new SpanLabel( r.getDated());
                 ImageViewer imgV = new ImageViewer();
       dat.add(createListEntry(spl.getText(),sp5.getText(), imgV));
               DefaultListModel<Map<String, Object>> mode = new DefaultListModel<>(dat);
MultiList m = new MultiList(mode);
m.getUnselectedButton().setIconName("icon_URLImage");
m.getSelectedButton().setIconName("icon_URLImage");

m.getUnselectedButton().setIcon(imgV.getImage());
m.getSelectedButton().setIcon(imgV.getImage());
      
   lis.add( m);

        }
          // lis.add( t);

 }
 getContentPane().animateLayout(250);
}, 0);
// h1.getToolbar().addSearchCommand(callback);
//lis.add(h1);
add(lis);
           Form hi = new Form("Capture", new BorderLayout());
              Container li = new Container(new BorderLayout());

hi.setToolbar(new Toolbar());
Style s = UIManager.getInstance().getComponentStyle("Title");
FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, s);
ImageViewer iv = new ImageViewer(icon);
getToolbar().addCommandToSideMenu("", icon, (ev) -> {

     String filePath = Capture.capturePhoto();                 
 if(filePath != null) {

     
 


         try {
             DefaultListModel<Image> m =(DefaultListModel<Image>)iv.getImageList();
             Image  img = Image.createImage(filePath);
         
        
 if(m == null) {
 m = new DefaultListModel<>(img);
 iv.setImageList(m);
 iv.setImage(img);
 } else {
 m.addItem(img);
 }
 m.setSelectedIndex(m.getSize() - 1);
  
 } catch (IOException ex) {
            Log.e( ex);
         }
 }


   
});
  hi.add(BorderLayout.CENTER,iv);
add(hi);


         for ( Tournoi ev : list) {

  Container c = new Container(BoxLayout.x());
  

              String url = Statics.BASE_URL_K+"uploads/"+ev.getLogo();
            // Image placeholder = Image.createImage(400, 400);


        Style s1 = UIManager.getInstance().getComponentStyle("Button");
           FontImage p = FontImage.createMaterial(FontImage.MATERIAL_PORTRAIT, s1);
             EncodedImage placeholder =EncodedImage.createFromImage(p.scaled(p.getWidth() * 3, p.getHeight()* 4), false);
              
          Image roundMask = Image.createImage(placeholder.getWidth(),
 placeholder.getHeight(), 0xff000000);
Graphics gr = roundMask.getGraphics();
gr.setColor(0xffffff);
gr.fillArc(0, 0, placeholder.getWidth(), placeholder.getHeight(), 0, 360);
URLImage.ImageAdapter ada = URLImage.createMaskAdapter(roundMask);
        
          EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
           
            URLImage urlim = URLImage.createToStorage(enc, ev.getLogo(), url +"/"  + ev.getLogo(),ada);
         
          
    
        ImageViewer imgV = new ImageViewer();
     SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel(  ev.getNomt());
                SpanLabel sp5 = new SpanLabel( ev.getDated());
                 SpanLabel sp8 = new SpanLabel("date fin: " + "  " + ev.getDatef());
                   SpanLabel sp7 = new SpanLabel("type: " + "  " + ev.getTypet());
                     SpanLabel sp9 = new SpanLabel("nombre: " + "  " + ev.getNbrc());
                  SpanLabel sp6 = new SpanLabel("logo: " + "  " + ev.getLogo());
             ArrayList<Map<String, Object>> data = new ArrayList<>();
     data.add(createListEntry(spl.getText(),sp5.getText(), imgV));
               DefaultListModel<Map<String, Object>> model = new DefaultListModel<>(data);
MultiList ml = new MultiList(model);
               ml.setScrollToSelected(false);
         imgV.setImage(urlim);
 // Container c3 = new Container(BoxLayout.y());
             
                    
                  Button b = new Button("view");

      
ml.getUnselectedButton().setIconName("icon_URLImage");
ml.getSelectedButton().setIconName("icon_URLImage");

ml.getUnselectedButton().setIcon(imgV.getImage());
ml.getSelectedButton().setIcon(imgV.getImage());
    Form h = new Form( new BoxLayout(BoxLayout.Y_AXIS));
        ml.addActionListener((e)->{ 
                ArrayList<Map<String, Object>> dat = new ArrayList<>();
       dat.add(createListEntry1(spl.getText(),sp5.getText(),sp8.getText(),sp7.getText(),sp9.getText(), imgV));
               DefaultListModel<Map<String, Object>> mode = new DefaultListModel<>(dat);
MultiList m = new MultiList(mode);
m.getUnselectedButton().setIconName("icon_URLImage");
m.getSelectedButton().setIconName("icon_URLImage");

m.getUnselectedButton().setIcon(imgV.getImage());
m.getSelectedButton().setIcon(imgV.getImage());
      
 
     b.addActionListener((ex)->{ 
         new ListClassment(this,ev);
     });
       h.add( m);
   h.add(b);


   
          ArrayList<Rewards> q;
                 q =ServiceRewards.getInstance1().RechercheReward(ev.getId());
          for( Rewards rw : q)
          {
             String ur = Statics.BASE_URL_K+"uploads/";
 Image placehold = Image.createImage(300, 300);
            EncodedImage en = EncodedImage.createFromImage(placehold, false);
            Image urli = URLImage.createToStorage(en,rw.getImg() , ur +"/"  +rw.getImg());
            urli = urli.fill(300,300);
            ImageViewer img = new ImageViewer();
            img.setImage(urli);
             SpanLabel spk = new SpanLabel("                          "+  rw.getTrophe());
            h.add(img).add(spk);
          }
            h.show();
        }); 
         
  
                h.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e -> new TournoiFront(this).showBack());
 
 c.add( ml);

// h1.add(hi);
lis.add(c);

 //i(c);
         }
 



    

         
    

 
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        }
private Map<String, Object> createListEntry(String name,  String date,
ImageViewer coverURL) {
 Map<String, Object> entry = new HashMap<>();
 entry.put("Line1", name);
 entry.put("Line2", date);
 entry.put("icon_URLImage", coverURL);
 entry.put("icon_URLImageName", name);
 return entry;
}
private Map<String, Object> createListEntry1(String name,  String date,String dat,String da,String d,
ImageViewer coverURL) {
 Map<String, Object> entry = new HashMap<>();
 entry.put("Line1", name);
 entry.put("Line2", date);
  entry.put("Line3", dat);
   entry.put("Line4", da);
    entry.put("Line5", d);
 entry.put("icon_URLImage", coverURL);
 entry.put("icon_URLImageName", name);
 return entry;
}
}