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
import com.esprit.entities.club;
import com.esprit.services.serviceclub;
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
 * @author 
 */
public class addclub extends Form {

    Form previous;

    private String fileNameInServer = "";

    public addclub(Form previous) {

        setTitle("Add a new club");
        setLayout(BoxLayout.y());

        TextField Nomc = new TextField("", "name");

        TextField descr = new TextField("", "description");
        TextField President = new TextField("", "president");
        Button btnValider = new Button("Add club");
        // addStringValue("", btnAjouter);

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
            //serviceclub c = new serviceclub();
            club r = new club(Nomc.getText().toString(), descr.getText(), President.getText().toString(), fileNameInServer);
            System.out.println("data evenement ==" + r);
            serviceclub.getInstance().addTournoi(r);

        });
        addAll(Nomc, imgBtn, descr, President, btnValider);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new Listclub(this).show());

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }
}
