/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.joueur;
import com.esprit.services.servicejoueur;

/**
 *
 * @author oumaymacherif
 */
public class updateJoueur extends Form {

    Form previous;

    public updateJoueur(joueur ev) {
        super("Newsfeed", BoxLayout.y());

        setTitle("Modifier Votre joueur");

        TextField Description = new TextField(ev.getNom(), "nomc", 20, TextField.ANY);
        Description.setUIID("TextFieldBlack");

        TextField Image = new TextField(ev.getposte(), "descr", 20, TextField.ANY);
        Image.setUIID("TextFieldBlack");

         TextField nba = new TextField(""+ev.getnba());

        TextField nbm = new TextField(""+ev.getnbm());
        TextField nationalite = new TextField(""+ev.getnationalite());
        TextField age = new TextField(""+ev.getage());
        TextField prenom = new TextField(""+ev.getprenom());
        TextField numt = new TextField(""+ev.getnumT());

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //OnClick Button
        btnModifier.addPointerPressedListener(l -> {

           ev.setNom(Description.getText());
           ev.setprenom(prenom.getText());
           ev.setNumT(Integer.parseInt(numt.getText()));
           ev.setnba(Integer.parseInt(nba.getText()));
            ev.setnbm(Integer.parseInt(nbm.getText()));
            ev.setage(Integer.parseInt(age.getText()));
            //Appel a la methode UPDATE
            if (servicejoueur.getInstance().modifierJoueur(ev)) {
                //If True
                new Listjoueur(this).show();

            }
        });

        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
             
                
                new FloatingHint(Description),
                new FloatingHint(Image),
                btnModifier
             
        );
addAll(nba,nbm,nationalite,age,prenom,numt,content);
     
        show();

    }

}
