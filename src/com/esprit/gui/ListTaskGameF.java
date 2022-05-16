
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
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;

import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Game;
import com.esprit.entities.Tournoi;
import com.esprit.services.ServiceGame;
import com.esprit.services.ServiceTournoi;
import java.util.ArrayList;

/**
 *
 * @author oumayma
 */
public class ListTaskGameF extends Form {

    public Tournoi t;
    Form current;
    public static String nomt;

    public ListTaskGameF(Form previous) {

        setTitle("List Game");
        setLayout(BoxLayout.y());

         Button add = new Button("ADD");
          add(add);
             add.addActionListener(( e)-> new AjoutGame(this).show() 
        
                
            
        );   
             
        //SpanLabel sp = new SpanLabel();
        ServiceGame es = new ServiceGame();
        
        ArrayList<Game> list;
        list = new ArrayList<>();
        //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceGame.getInstance1().getAll();

        // k = ServiceTournoi.getInstance().getAll();
       
        for (Game ev : list) {

            SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
            SpanLabel sp9 = new SpanLabel("Club1: " + "  " + ev.getClub1().getNomc());
            SpanLabel spl = new SpanLabel("r1: " + "  " + ev.getR1());
            SpanLabel sp5 = new SpanLabel("r2: " + "  " + ev.getR2());
            SpanLabel sp10 = new SpanLabel("Club2: " + "  " + ev.getClub2().getNomc());
            SpanLabel sp8 = new SpanLabel("date: " + "  " + ev.getDeted());
            SpanLabel sp11 = new SpanLabel("Stade: " + "  " + ev.getStade().getNoms());
            SpanLabel sp12 = new SpanLabel("Arbitre: " + "  " + ev.getArbitre().getNomA());

            
            
            
           
           
            //  t =ev.getT();
            //this.t=t.getNomt();
            // TextField flogo= new   TextField(this.t+"");

            
            addAll(sp0,sp9, spl, sp5,sp10, sp8, sp11, sp12);
            Button update = new Button("update");
            add(update);
            Button Delete = new Button("Delete");
            add(Delete);
            
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
                     
                     
                        es.deleteGame((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("GAME SUPPRIME AVEC SUCCES");
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
            
           
        }
        Resources res=UIManager.initFirstTheme("/theme");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show()); // Revenir vers l'interface précédente
    }
}
