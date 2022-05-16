
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Arbitre;

import com.esprit.services.ServiceArbitre;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ListTaskArbitreF extends Form {
   // String url = "http://localhost/Federation-de-football/public/uploads";
//String url = Statics.BASE_URL+"uploads/";
    private Resources res=UIManager.initFirstTheme("/theme");
    public ListTaskArbitreF(Form previous) {

        setTitle("List Arbitre");
        setLayout(BoxLayout.y());
        Button add = new Button("ADD");
          add(add);
             add.addActionListener(( e)-> new AjoutArbitre(this).show() 
        
                
            
        );
        SpanLabel sp = new SpanLabel();
        /*ArrayList<Arbitre> list;
        list = new ArrayList<>();
        list = ServiceArbitre.getInstance().getAll(); */
        ServiceArbitre es = new ServiceArbitre();
        ArrayList<Arbitre> list = es.getAll();
        for (Arbitre ev : list) {
            String url = Statics.BASE_URL_K+"/uploads/"+ev.getImage();
            Container c3 = new Container(BoxLayout.y());

            SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
            SpanLabel spl = new SpanLabel("Arbitre name: " + "  " + ev.getNomA());

            SpanLabel sp7 = new SpanLabel("nombre d'experience: " + "  " + ev.getNbe());
            SpanLabel sp9 = new SpanLabel("description: " + "  " + ev.getDescrp());
            //SpanLabel sp6 = new SpanLabel("image: " + "  " + ev.getImage());
            
             Image placeholder = Image.createImage(500,500);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getImage(), url );
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);

            addAll(sp0, spl, sp7, sp9, imgV);

           
            
          Button Delete = new Button("Delete");
            c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            
            Delete.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        es.Delete((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("ARBITRE SUPPRIME AVEC SUCCES");
                        status.setExpires(10000);
                        status.show();

                        refreshTheme();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });
            
            Button modifier =new Button("Modifier");
         c3.add(modifier);
            modifier.getAllStyles().setBgColor(0xF36B08);
            modifier.addPointerPressedListener((ActionEvent l)->{
               new ModifierArbitre(ev).show();
            });
           /* Button modifier = new Button("Update");
            c3.add(modifier);
            modifier.getAllStyles().setBgColor(0xF36B08);
            
            modifier.addPointerPressedListener(l ->
            System.out.println("hello world")); */

            System.out.println("");

            add(c3);
        }
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show());
    }

}
