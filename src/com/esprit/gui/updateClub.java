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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.club;
import com.esprit.services.serviceclub;

/**
 *
 * @author oumaymacherif
 */
public class updateClub extends Form {

    Form previous;

    public updateClub(club ev) {
        super("Newsfeed", BoxLayout.y());

        setTitle("Modifier Votre club");

        TextField Description = new TextField(ev.getNomc(), "nomc", 20, TextField.ANY);
        Description.setUIID("TextFieldBlack");

        TextField Image = new TextField(ev.getDescr(), "descr", 20, TextField.ANY);
        Image.setUIID("TextFieldBlack");

        TextField DateAvis = new TextField(ev.getLogo(), "logo ", 20, TextField.ANY);
        DateAvis.setUIID("TextFieldBlack");

        TextField Nom = new TextField(ev.getPresident(), "president", 20, TextField.ANY);
        Nom.setUIID("TextFieldBlack");

        Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");

        //OnClick Button
        btnModifier.addPointerPressedListener(l -> {

            ev.setNom(Description.getText());
            ev.setDescr(Image.getText());
            ev.setLogo(DateAvis.getText());
            ev.setPresident(Nom.getText());

            //Appel a la methode UPDATE
            if (serviceclub.getInstance().modifierClub(ev)) {
                //If True
                 new Listclub(this).show();

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
                new FloatingHint(DateAvis),
                new FloatingHint(Nom),
                btnModifier
             
        );

        add(content);
        show();
 Resources res=UIManager.initFirstTheme("/theme");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Listclub(new ProfileForm(res) ).show());

    }

}
