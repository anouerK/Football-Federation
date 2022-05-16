/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.utils.Statics;
import com.esprit.entities.produit;
import java.io.IOException;

import com.esprit.main.MyApplication;
import com.esprit.entities.panier;
import java.util.ArrayList;

/**
 *
 * @author Lord
 */
public class product extends Form {
  EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;
  Container list = new Container(BoxLayout.y());
  String taille="";
  panier p;
   //String[] characters = { "", "Prix Decroissant"};
public product(produit p)
    {
         setTitle("home");
       // setLayout(BoxLayout.y());
     
     
       
       
      //  list.setScrollableY(true);
  
   
    


    list.add(createContactContainer(p));
    


        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->new store(this).show());

  //add(c);
  add(list);
    }
private Container createContactContainer(produit person) {
    Label name = new Label("");
     Label qtee = new Label("Quantite:");
     Label t=new Label("Taille:");
    Label prixl=new Label("Prix:");
    Label desc=new Label("");
    Label dd = new Label("Description:");
    Label email = new Label("");
     Label color = new Label("Couleur:");
      Label colors = new Label("");
    TextField qte= new TextField("","Quantite");
   
    
    //Label pic = new Label("");
    Label pic2 = new Label("______________________________");
    Button add  = new Button("Add To Cart");
    
   
     MultiButton b3 = new MultiButton("Taille");
                 
             String [] tailles= {person.getTaille(),person.getTaille2()};    
                 
                 b3.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int i=0 ;i<tailles.length;i++)
    {
        MultiButton mb = new MultiButton(tailles[i]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b3.setTextLine1(mb.getTextLine1());
           taille=mb.getTextLine1();
           System.out.println(taille);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b3.revalidate();
        });
    }
    d.showPopupDialog(b3);
});
                 
    
    
    String prix=String.valueOf(person.getPrix());
    
    urli = Statics.BASE_URL + "/uploads/img/"+person.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(400,1500);
              Label pic = new Label(i, "ProduitPicture");
    
   // Button view = new Button("view");
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    name.getAllStyles().setBgTransparency(0);
    name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    email.getAllStyles().setBgTransparency(0);
    
    // view.addActionListener((evtt)->new product(person).show());
    
    
    add.addActionListener((evt)->{
     int qte2= Integer.parseInt(qte.getText());
     
    p = new panier(taille,qte2,person.getPrix()*qte2,person);
    System.out.print("Panier = "+p);
    MyApplication.pan.add(p);
    
    });
    
    cnt.add(name);
    cnt.add(dd);
     cnt.add(desc);
     cnt.add(color);
     cnt.add(colors);
     cnt.add(t);
     cnt.add(b3);
     cnt.add(qtee);
     cnt.add(qte);
     cnt.add(prixl);
    cnt.add(email);
    cnt.add(add);
   
   // cnt.add(view);
    cnt.add(pic2);
    name.setText(person.getNom());
    email.setText(prix+"DT");
    colors.setText(person.getCouleur());
    desc.setText(person.getDesc());
  //  pic.setIcon(person.getImg());
    return BorderLayout.center(cnt).
        add(BorderLayout.WEST, pic);
}
        
    
}
