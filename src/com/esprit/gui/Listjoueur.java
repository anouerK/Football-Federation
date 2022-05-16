package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
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
import com.esprit.entities.joueur;
import com.esprit.services.servicejoueur;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author cherif
 */
public class Listjoueur extends Form {

    public Listjoueur(Form previous) {

        setTitle("Liste des joueur");

        servicejoueur es = new servicejoueur();
        ArrayList<joueur> list = es.getAlljoueur();

        {
            Button ajoutjoueur = new Button("Ajouter");

            ajoutjoueur.addPointerPressedListener(l -> {
                new addjoueur1().show();
            });

            Container chercherContianer = new Container();
            chercherContianer.setLayout(BoxLayout.y());
            chercherContianer.addAll(ajoutjoueur);
            this.add(chercherContianer);

            for (joueur r : list) {

                Container c3 = new Container(BoxLayout.y());
                String url = Statics.BASE_URL_K + "/" + "uploads/" + r.getphoto();
                SpanLabel cat = new SpanLabel("Nom du joueur  :" + r.getNom());
                SpanLabel cat1 = new SpanLabel("photo :" + r.getphoto());

                SpanLabel cat2 = new SpanLabel("prenom :" + r.getprenom());
                SpanLabel cat3 = new SpanLabel("age :" + r.getage());
                SpanLabel cat4 = new SpanLabel("poste   :" + r.getposte());
                SpanLabel cat5 = new SpanLabel("nationalite  :" + r.getnationalite());
                SpanLabel cat6 = new SpanLabel("nba :" + r.getnba());
                SpanLabel cat7 = new SpanLabel("nbm :" + r.getnbm());
                
                 Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            System.out.println(url);
            URLImage urlim = URLImage.createToStorage(enc, r.getphoto(), url);
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
                 c3.add(imgV);
                c3.add(cat);
               
                c3.add(cat2);

                c3.add(cat3);
                c3.add(cat4);
                c3.add(cat5);
                c3.add(cat6);
                c3.add(cat7);

                Button Delete = new Button("Delete");
                c3.add(Delete);

                Button Modifier = new Button("Modifier");
                Modifier.addPointerPressedListener(l -> {
                    new updateJoueur(r).show();
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
                            es.Delete(r.getcin());

                            alert.dispose();
                            refreshTheme();
                        }

                    }
                    );

                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                    new Listjoueur(previous).show();

                });
                ajoutjoueur.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {

                        refreshTheme();
                    }

                });
                System.out.println("");

                add(c3);
 Resources res=UIManager.initFirstTheme("/theme");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show()); // Revenir vers l'interface précédente

            }

        }

    }

}
