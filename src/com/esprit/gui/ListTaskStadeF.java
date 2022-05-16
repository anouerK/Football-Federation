
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
import com.esprit.entities.Stade;
import com.esprit.gui.HomeForm;
import com.esprit.services.ServiceStade;
import com.esprit.utils.Statics;
import java.util.ArrayList;
 
/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ListTaskStadeF extends Form{
    
    Form current;
    Resources res;
       
  
        public ListTaskStadeF(Form previous) {
        
        
        setTitle("List Stade");
         setLayout(BoxLayout.y());
         Button add = new Button("ADD");
          add(add);
             add.addActionListener(( e)-> new AjoutStade(this).show() 
        
                
            
        );   
             
              Button stat = new Button("STAT");
          add(stat);
             stat.addActionListener(( e)-> new StatistiquePieForm().createPieChartForm().show()); 
        
                
            
          
        SpanLabel sp = new SpanLabel();
        /*ArrayList<Arbitre> list;
        list = new ArrayList<>();
        list = ServiceArbitre.getInstance().getAll(); */
        ServiceStade es = new ServiceStade();
                ArrayList<Stade> list = es.getAll() ;
        for (Stade ev : list) {
            Container cx = new Container(BoxLayout.x());
              String url = Statics.BASE_URL_K+"/uploads/"+ev.getPhoto();
             Container c3 = new Container(BoxLayout.y());
             
            SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
            SpanLabel spl = new SpanLabel("Stade name: " + "  " + ev.getNoms());
            SpanLabel sp7 = new SpanLabel("nombre de capacité: " + "  " + ev.getNbrP());
            SpanLabel sp9 = new SpanLabel("Lieu: " + "  " + ev.getLieu());
            SpanLabel sp10 = new SpanLabel("Etat: " + "  " + ev.getEtat());
           // SpanLabel sp6 = new SpanLabel("image: " + "  " + ev.getPhoto());

           Image placeholder = Image.createImage(300,250);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            Image urlim = URLImage.createToStorage(enc, ev.getPhoto(), url );
            urlim = urlim.fill(300,250);
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
            
            
            
        //    addAll(sp0, spl, sp7, sp9, sp10, sp6);
                        c3.add(sp0);
                        c3.add(spl);
                        c3.add(sp7);
                        c3.add(sp9);
                        c3.add(sp10);
                       
                      
            Button Delete = new Button("Delete");
            c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            
            Delete.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet Stade?\nCette action est irréversible!");
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
                        status.setMessage("STADE SUPPRIME AVEC SUCCES");
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
               new ModifierStade(ev).show();
            });
   
             
                       
                        
           System.out.println("");
            cx.add(imgV);
              cx.add(c3);
              add(cx);
 
        }
        Resources res=UIManager.initFirstTheme("/theme");
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show()); // Revenir vers l'interface précédente
    }
    
}
