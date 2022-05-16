package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
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
import com.esprit.entities.club;
import com.esprit.services.serviceclub;
import java.util.ArrayList;
import com.esprit.utils.Statics;

/**
 *
 * @author cherif
 */
public class Listclub extends Form {

    public Listclub(Form previous) {

        setTitle("Liste des clubs");

        serviceclub es = new serviceclub();
        ArrayList<club> list = es.getAllclub();

        

            Button ajoutclub = new Button("Ajouter");

            ajoutclub.addPointerPressedListener(l -> {
                new addclub(this).show();
            });

            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
            chercherContianer.addAll(ajoutclub);
            this.add(chercherContianer);

            for (club r : list) {

                Container c3 = new Container(BoxLayout.y());
        String url = Statics.BASE_URL_K +"/"+"uploads/"+r.getLogo();

                SpanLabel cat = new SpanLabel("Nom du club  :" + r.getNomc());
                SpanLabel cat0 = new SpanLabel("Logo  :" + r.getLogo());
                SpanLabel cat1 = new SpanLabel("description  :" + r.getDescr());
                SpanLabel cat2 = new SpanLabel("President :" + r.getPresident());
   Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, r.getLogo(), url +"/"  + r.getLogo());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
                c3.add(cat);
                //c3.add(cat0);
                c3.add(cat1);
                c3.add(cat2);
                c3.add(imgV);

                Button Delete = new Button("Delete");
                c3.add(Delete);
                
                Button Modifier = new Button("Modifier");
                Modifier.addPointerPressedListener(l -> {
                    new updateClub(r).show();
                });
                c3.add(Modifier);

                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce club ?\nCette action est irréversible!");
                    alert.add(message);
                    Button ok = new Button("Confirmer");
                    Button cancel = new Button(new Command("Annuler"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            es.Delete(r.getId());

                            alert.dispose();
                            refreshTheme();
                        }

                    }
                    );

                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                    new Listclub(previous).show();

                });
                ajoutclub.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {

                        refreshTheme();
                    }

                });
                System.out.println("add club");

                addAll(c3);
} Resources res=UIManager.initFirstTheme("/theme");
 getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show());
           // Revenir vers l'interface précédente

            

        }

    

}
