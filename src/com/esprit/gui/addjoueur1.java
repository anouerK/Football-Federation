package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.NetworkManager;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.joueur;
import com.esprit.services.servicejoueur;
import java.util.ArrayList;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.esprit.utils.Statics;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author oumayma
 */
public class addjoueur1 extends Form {

    Form previous;
    private String fileNameInServer = "";

    public addjoueur1() {

        setTitle("Add a new joueur");
        setLayout(BoxLayout.y());

        TextField nom = new TextField("", "nom");
        TextField poste = new TextField("", "poste");
        TextField nba = new TextField("", "nba");

        TextField nbm = new TextField("", "nbm");
        TextField nationalite = new TextField("", "nationalite");
        TextField age = new TextField("", "age");
        TextField prenom = new TextField("", "prenom");
        TextField numt = new TextField("", "numt");
        Button btnValider = new Button("Add joueur");
        Font materialFontAjout = FontImage.getMaterialDesignFont();
        int size = Display.getInstance().convertToPixels(5, true);
        materialFontAjout = materialFontAjout.derive(size, Font.STYLE_PLAIN);
        Button Delete = new Button("Supprimer");
        Delete.setIcon(FontImage.create("\uea4c", Delete.getUnselectedStyle(), materialFontAjout));
        //MyApplication

        Button imgBtn = new Button("parcourir");
        //addStringValue("", imgBtn);

        imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    FileUploader fu = new FileUploader(Statics.URL_UPLOAD);

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if (v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String) v.getSource()).substring(7);
                            System.out.println(filePath);

                            try {
                                fileNameInServer = fu.upload(filePath);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnValider.addActionListener((e) -> {
            //joueur r = new joueur( nomc.getText().toString(),descr.getText(),President.getText().toString());
            //servicejoueur c = new servicejoueur();
            joueur r = new joueur(Integer.parseInt(age.getText()), Integer.parseInt(nbm.getText()), Integer.parseInt(nba.getText()), Integer.parseInt(numt.getText()), nom.getText(), prenom.getText(), poste.getText(), nationalite.getText(), fileNameInServer);
            System.out.println("data evenement ==" + r);
            servicejoueur.getInstance().addjoueur1(r);

            //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
        });
        addAll(age, nbm, nba,numt, nom,prenom,poste,nationalite,imgBtn, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Listjoueur(this).show());

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));

    }
}
