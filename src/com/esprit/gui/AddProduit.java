/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;


import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.services.ServiceCategories;
import com.esprit.services.ServiceMarques;
import com.esprit.services.ServiceProduits;
import com.esprit.utils.Statics;
import com.esprit.entities.categories;
import com.esprit.entities.marques;
import com.esprit.entities.produit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lord
 */
public class AddProduit extends Form  {
    String filePath;
            String ch;
            String ch2;
            String ch3;
             ArrayList<categories> list=ServiceCategories.getInstance().parsecat();
              ArrayList<marques> listm=ServiceMarques.getInstance().parseTasks();
            
        String[] characters1={"s","m","l"};
         String[] characters2={"s","m","l"};
          String[] characters3={"noire","rouge","blanc"};
          
          String co1="";
          String taille1="";
          String taille2="";
        
String[] actors = { "Peter Dinklage", "Nikolaj Coster-Waldau", "Lena Headey"};
int size = Display.getInstance().convertToPixels(7);

categories categorie;
marques marque;
            
    public AddProduit(Form previous)
            

    {
         
          TextField tfName=new TextField("","Nom");
          TextField tfdesc=new TextField("","Description");
          TextField tfqte=new TextField("","Quantite");
          TextField tfprix=new TextField("","Prix"); 
         // CheckBox  cbcolor= new CheckBox("noire");
          // CheckBox  cbtaille1= new CheckBox("S");
           // CheckBox  cbtaille2= new CheckBox("M");
            //TextField tfcategorie=new TextField("","cat");
           // TextField tfmarque=new TextField("","marque");
             Button btnUpload = new Button("Upload Image");
             Button btnAdd = new Button("Add Produit");
             
             
             
             MultiButton b = new MultiButton("Categories");
              MultiButton b2 = new MultiButton("Marques");
               MultiButton b3 = new MultiButton("Taille1");
                MultiButton b4 = new MultiButton("Taille2");
                 MultiButton b5 = new MultiButton("Couleur");
                 
                 
                 
                 b3.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < characters1.length ; iter++) {
        MultiButton mb = new MultiButton(characters1[iter]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b3.setTextLine1(mb.getTextLine1());
           taille1=mb.getTextLine1();
           System.out.println(taille1);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b3.revalidate();
        });
    }
    d.showPopupDialog(b3);
});
                 
               
                 
                 
                   
                 b4.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(int iter = 0 ; iter < characters1.length ; iter++) {
        MultiButton mb = new MultiButton(characters1[iter]);
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b4.setTextLine1(mb.getTextLine1());
            taille2=mb.getTextLine1();
             System.out.println(taille2);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b4.revalidate();
        });
    }
    d.showPopupDialog(b4);
});
              
                 
                   
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
            co1=mb.getTextLine1();
            System.out.println(co1);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b5.revalidate();
        });
    }
    d.showPopupDialog(b5);
});
                 
              
             
b.addActionListener(e -> {
    Dialog d = new Dialog();
    d.setLayout(BoxLayout.y());
    d.getContentPane().setScrollableY(true);
    for(categories c : list)
             { 
             
        MultiButton mb = new MultiButton(c.getTypec());
       
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d.add(mb);
        mb.addActionListener(ee -> {
            b.setTextLine1(mb.getTextLine1());
            int id = c.getId();
            categorie = ServiceCategories.getInstance().parsecatadd(id);
               System.out.println("not null"+categorie);
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d.dispose();
            b.revalidate();
        });
    }
    
    d.showPopupDialog(b);
});




b2.addActionListener(e -> {
    Dialog d2 = new Dialog();
    d2.setLayout(BoxLayout.y());
    d2.getContentPane().setScrollableY(true);
    for(marques m : listm)
             { 
             
        MultiButton mb2 = new MultiButton(m.getNomM());
        //mb.setTextLine2(actors[iter]);
       // mb.setIcon(pictures[iter]);
        d2.add(mb2);
        mb2.addActionListener(ee -> {
            b2.setTextLine1(mb2.getTextLine1());
           int id =m.getId();
           marque =ServiceMarques.getInstance().parseTasksid(id);
            System.out.println(marque);
           
           // b.setTextLine2(mb.getTextLine2());
           // b.setIcon(mb.getIcon());
            d2.dispose();
            b2.revalidate();
        });
    }
    d2.showPopupDialog(b2);
});

             
               Container c = new Container(BoxLayout.y());
         Container c2 = new Container(BoxLayout.y());
        
        Label l = new Label("Taille:");
        Label l2 = new Label("Couleur:");
        Label cat=new Label("");
        Label mar=new Label("");
         Label t1=new Label("");
          Label t2=new Label("");
           Label c1=new Label("");
            Label up=new Label("");
             Label ad=new Label("");
        
        c.addAll(cat,b,mar,b2);
        c2.addAll(t1,b3,t2,b4,c1,b5,up,btnUpload,ad,btnAdd);
       // c2.addAll(mar,b2);
       
              
             
             btnUpload.addActionListener((evt) -> {
                 filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
             });
             
                  btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if (tfName.getText() == "")
        {
            Dialog.show("Error","produit Name not valid","OK",null);
        }
            if (tfdesc.getText() == "")
        {
            Dialog.show("Error","produit Name not valid","OK",null);
        }
            if (tfqte.getText() == "")
        {
            Dialog.show("Error","produit Name not valid","OK",null);
        }
            if (tfprix.getText() == "")
        {
            Dialog.show("Error","produit Name not valid","OK",null);
        }
            
            
          else
            {
                float prix  = Float.parseFloat(tfprix.getText());
                int qte = Integer.parseInt(tfqte.getText());
               // int cat = Integer.parseInt(tfcategorie.getText());
               //// int mar = Integer.parseInt(tfmarque.getText());
               // if(cbtaille1.isSelected())ch+="s";
               //// if(cbtaille2.isSelected())ch2+="m";
              //  if(cbcolor.isSelected())ch3+="noire";
                
                
               produit badge = new produit(tfName.getText(),taille1,taille2,tfdesc.getText(),filePath,co1,prix,qte,categorie,marque);
              if(ServiceProduits.getInstance().addP(badge)){
                   Dialog.show("Success","Badge Added","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            }
             
        
    });
                  
                              getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});

    
                  addAll(tfName);
                  add(c);
                  add(tfdesc);
                 add(tfprix);
                 add(tfqte);
                 
                 
              /*   add(cat);
                 add(b);
                  add(mar);
                add(b2);*/
                  
                  add(c2); 
                  
                 
                 //add( btnUpload);
                 //add(btnAdd);
}

}