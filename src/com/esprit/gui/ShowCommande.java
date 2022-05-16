/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
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

import com.esprit.entities.User;
import com.esprit.services.ServiceUser;
import com.esprit.entities.commande;
import com.esprit.services.ServiceCommandes;
import java.util.ArrayList;
import com.esprit.main.MyApplication;
import com.esprit.utils.Statics;
import java.io.IOException;

/**
 *
 * @author Lord
 */
public class ShowCommande extends Form  {
    
     EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;

int i=0;
int f=0;
int d=0;
int y=0;

   Button next = new Button("Next");
                        Button previouss = new Button("Previous");

  ArrayList<commande> listc=ServiceCommandes.getInstance().parseTaskscom();
       

Button pag=new Button("");
Container paginator = new Container(BoxLayout.x());

 Container c2 = new Container(BoxLayout.y());
  Container c = new Container (new TableLayout(5,5));
   
   
    public ShowCommande(Form previous , int aa){
   setTitle("Commandes");
   
   int x = -1;
   String nom="";
   ArrayList<User> list=ServiceUser.getInstance().getAll();
   
   
   if(aa!=0)
        {
        d=aa*3;
        f=aa*3-3;
            System.out.println(d+f);
        }
        else if (aa==0)
        {
         d=3;
         f=0;
                     System.out.println(d+f);

        }   
     
    
    
    
    
     
       
       if( listc.size() % 3 !=0)
{
    y=(listc.size() / 3)+1;
    
}

else if( listc.size() % 3 ==0)
{
     y=(listc.size() / 3);
}
       
       
       Label l1= new Label(" ");
        Label l2= new Label("       ");
                Label l3= new Label("       ");
                        Label l4= new Label("       ");
        
                     
                        
        paginator.addAll(l1,l2,l3);
       
       for(i=0 ;i<y;i++)
{
    String i2= String.valueOf(i+1); //1/2/3
     pag= new Button(i2);
    paginator.add(pag);
    
    paginate(i+1);
    
  
}     
       
     
          
    
    
    
    Label l = new Label("Les Commandes de " + nom +":");
    
    c2.add(l);
    add(c2);
    
     commande_paginate(f,d);
     
        add(c);

    add(paginator);
           
    
    
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (e)->new BaseFront().show());

    }
    
    
    private void commande_paginate(int a,int b)
    {
          
        System.out.println("aaa"+a);
        System.out.println("bbb"+b);
        
      
      
       
        if(b>listc.size())
            b=listc.size();
         System.out.println("bbb222"+b);
         
         c.add(new Label("Produit")).add(new Label("Nom")).add(new Label("Taille"))
            .add(new Label("Qunatite")).add(new Label("Prix"));
        
        for(int j = a ; j<b; j++)
       {
           
           System.out.println(listc.get(j).getUser().getId());
           System.out.println("commande id:"+listc.get(j).getId());
        if(listc.get(j).getUser().getId()==MyApplication.u_c.getId()){
            
             urli = Statics.BASE_URL + "/uploads/img/"+listc.get(j).getProduit().getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {
                
             }
                
                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(140,220);
              Label pic = new Label(i, "ProduitPicture");
              String qte = String.valueOf(listc.get(j).getQtec());
              String pr = String.valueOf(listc.get(j).getPrix());
             
              c.add(pic).add(listc.get(j).getProduit().getNom()).add(listc.get(j).getTaillec()).add(qte).add(pr+"DT");
        }
            
       
      
       }
        
       
         
         
       
    }
    private void paginate(int cc)
            {
                
                
              
                  pag.addActionListener(
                   
                       (evt)->new ShowCommande(this,cc).show());
                  
                 

        
                  
                  
             
            }
}