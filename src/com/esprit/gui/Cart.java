/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.esprit.main.MyApplication;
import com.esprit.utils.Statics;
import com.esprit.entities.panier;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import com.esprit.entities.User;
import com.esprit.services.ServiceUser;
import com.esprit.main.MyApplication;


/**
 *
 * @author Lord
 */
public class Cart extends Form {
    Container t1; 
    
      EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;
     Float total =0.0f;
     User uuu;
   public Cart(Form previous)
   {
       Button add= new Button("Passer Commande");
       Button remove= new Button ("Drop");
    setTitle("Cart");
    Label l = new Label("Votre Produits:");
    Label l2 = new Label("Panier Vide");
   Container c2 = new Container(BoxLayout.y());
   
    Container c= new Container (new TableLayout(5,5));
     
    
    
    if(!MyApplication.pan.isEmpty())
 {
     c2.add(l);
        c.add(new Label("Produit")).add(new Label("Nom")).add(new Label("Taille"))
            .add(new Label("Qunatite")).add(new Label("Prix"));
    
   //  c.add(new Label("c")).add(new Label("d"));
     
 

   for(panier p : MyApplication.pan)
   {
      urli = Statics.BASE_URL + "/uploads/img/"+p.getProd().getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(140,220);
              Label pic = new Label(i, "ProduitPicture");
              String qte = String.valueOf(p.getQte());
              String pr = String.valueOf(p.getPrix());
             
              total=total+p.getPrix();
              c.add(pic).add(p.getProd().getNom()).add(p.getTaille()).add(qte).add(pr+"DT");
                
             
              remove.addActionListener((evt)->{
              c.removeAll();
              
              MyApplication.pan.remove(p);
                  System.out.println( MyApplication.pan );
              
               
              });
              
   }
     
     c.add("").add("").add("").add("").add("Total:"+total +"DT" );
     c.add("").add("").add("").add("").add(add);
      System.out.println(MyApplication.pan);
      
      add.addActionListener((evt)->{
        ArrayList<User> list=ServiceUser.getInstance().getAll();
        int x=-1;
       for(User use : list)
       {
            if(MyApplication.u_c.getId()==use.getId())
            {
                x=use.getId();
                uuu=use;
                break;
               
            }  
            else 
               x=0;
       }
            if(x!=0)
            {
                
            
        Message m = new Message("Body of message");

  Random r= new  Random();
  String  y =""+ r.nextInt(99999);
m.sendMessage(new String[] {"yaga77328@gmail.com"},y, m);
new Verif(y,uuu).show();
            
            }
            
            
     
       
       });
      
 }
else
 c2.add(l2);
              
    getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->new store(this).show());

    
   // t1 = TableLayout.encloseIn(2, new Label("First"),new Label("Second") );
         
           
    addAll(c2,c);
    
    
    
   
   
   }   
}
