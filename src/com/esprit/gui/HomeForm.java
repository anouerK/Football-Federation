/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
//import com.turbo_devs.gui.SideMenuBaseForm;

/**
 *
 * @author Lord
 */

public class HomeForm extends SideMenuBaseForm{
    Form current;
    public HomeForm(){
        
        current = this;
        setTitle("home");
        setLayout(BoxLayout.y());
        //setupSideMenu(res);
       
        Button btnShowmarq=new Button("Marque");
        Button btnShowcat=new Button("Categorie");
        Button btnShowprod=new Button("Produit");
         Button btnStore=new Button("store");
        
        
         Button btn=new Button("test");
         
        Button btncart=new Button("cart");
        Button btncommande=new Button("Commandes");
       
       btnShowmarq.addActionListener((evt)->new ShowForm(current).show());
       btnShowcat.addActionListener((evt)->new ShowCategorie(current).show());
      ///  btnShowprod.addActionListener((evt)->new ShowProduit().show());
       btnStore.addActionListener((evt)->new store(current).show());
         btncart.addActionListener((evt)->new Cart(new BaseFront()).show());
          btncommande.addActionListener((evt)->new ShowCommande(this,0).show());
       
       //btn.addActionListener((evt)->new mail(this).show()); 

 //Form hi = new Form("ComboBox", new BoxLayout(BoxLayout.Y_AXIS));
 


       
       
        
    addAll(btnShowmarq,btnShowcat,btn,btnShowprod,btnStore,btncart,btncommande);
}

    @Override
    protected void showOtherForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



 



}
