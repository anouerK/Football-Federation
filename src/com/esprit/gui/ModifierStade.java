/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Stade;
import com.esprit.services.ServiceStade;

/**
 *
 * @author emnah
 */
public class ModifierStade extends Form {

    Form current;

    String col;

    ModifierStade(Stade d) {
        String[] characters3 = {"complet", "en travaux", "Olympique"};
        setTitle("update theme");
        setLayout(BoxLayout.y());

        TextField tfID = new TextField(String.valueOf(d.getId()), "id");
        //tfID.setVisible(false);
        TextField nom = new TextField(d.getNoms(), "Nom", 20, TextArea.ANY);
        TextField nombre = new TextField(String.valueOf(d.getNbrP()), "Nombre", 20, TextArea.ANY);
        TextField descriptionEvent = new TextField(d.getLieu(), "Lieu", 20, TextArea.ANY);
        //  TextField lieuEvent = new TextField(d.getEtat(), "Etat", 20, TextArea.ANY);
        col=d.getEtat();
        MultiButton b5 = new MultiButton(d.getEtat());
        b5.addActionListener(e -> {
            Dialog dd = new Dialog();
            dd.setLayout(BoxLayout.y());
            dd.getContentPane().setScrollableY(true);
            for (int iter = 0; iter < characters3.length; iter++) {
                MultiButton mb = new MultiButton(characters3[iter]);
                //mb.setTextLine2(actors[iter]);
                // mb.setIcon(pictures[iter]);
                dd.add(mb);
                mb.addActionListener(ee -> {
                    b5.setTextLine1(mb.getTextLine1());

                    col = mb.getTextLine1();

                    System.out.println(col);
                    // b.setTextLine2(mb.getTextLine2());
                    // b.setIcon(mb.getIcon());
                    dd.dispose();
                    b5.revalidate();
                });
            }
            dd.showPopupDialog(b5);
        });
        TextField imageEvent = new TextField(String.valueOf(d.getPhoto()), "Image", 20, TextArea.ANY);

        Button btnSubmit = new Button("Modifier");
        Button cancel = new Button("Annuler");
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((nom.getText().length() == 0) || (nombre.getText().length() == 0) || (descriptionEvent.getText().length() == 0) || (b5.getText().length() == 0) || (imageEvent.getText().length() == 0)) {
                    Dialog.show("Alert", "Veuillez remplir tous les champs ", new Command("OK"));
                } else {

                    InfiniteProgress ip = new InfiniteProgress();
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    Stade d = new Stade(Integer.parseInt((tfID.getText())), nom.getText(), Integer.parseInt((nombre.getText())), descriptionEvent.getText(), col, imageEvent.getText());
                    if (ServiceStade.getInstance().ModifierS(d)) {
                        Dialog.show("Success", "Stade modifié ", new Command("OK"));

                        iDialog.dispose();
                        //refreshTheme();

                        //local notification api
                        LocalNotification ln = new LocalNotification();
                        ln.setId("LnMessage");
                        ln.setAlertTitle("Notice!");
                        ln.setAlertBody("A new Theme" + d.getNoms() + "has been added! Check it out");
                        //  ln.setAlertSound("/notification_sound_notif.mp3");
                        Display.getInstance().scheduleLocalNotification(ln, System.currentTimeMillis() + 10, LocalNotification.REPEAT_NONE);

                        Display.getInstance().scheduleLocalNotification(
                                ln,
                                System.currentTimeMillis(),
                                LocalNotification.REPEAT_MINUTE
                        );

                        //load after submuitting
                        new ListTaskStadeF(current).show();

                    } else {
                        Dialog.show("ERROR", "Connection Failed", new Command("OK"));

                    }

                }
            }
        });
        /* cancel.addPointerPressedListener(l -> {

            Dialog dig = new Dialog("Cancel");

            if (dig.show("Cancel", "Would you like to exit without submitting?, some data maybe lost", "Cancel", "OK")) {
                dig.dispose();
            } else {
                dig.dispose();
                new ListTasksForm(current);
            };

        }); */
        Button btnAnnuler = new Button("Annuler");
        btnAnnuler.addActionListener(e -> {
            new ListTaskStadeF(current).show();

        });

        addAll(tfID, nom, nombre, descriptionEvent, b5, imageEvent, btnSubmit, btnAnnuler);
    }

}
