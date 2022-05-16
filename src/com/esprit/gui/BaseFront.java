/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Game;
import com.esprit.entities.produit;
import com.esprit.main.MyApplication;
import com.esprit.services.ServiceGame;
import com.esprit.services.ServiceProduits;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ksaay
 */
public class BaseFront extends SideMenuBaseFrontForm {
    EncodedImage enc = null;
Image imgs;
ImageViewer imgv;
String urli ;
       Container list = new Container(BoxLayout.x());
    Container c = new Container(BoxLayout.x());
    public BaseFront()
    {
       // super("HOME");
      //   super();
        
         super("home", BoxLayout.y());
        Label titreform = new Label("Fédération tunisienne de football ");
        titreform.getAllStyles().setFgColor(0x607D8B);
        
        add(titreform);
         Toolbar tb = getToolbar();
        tb.setTitleCentered(false);
     
        Button menuButton = new Button("");
        menuButton.setUIID("Title");
        FontImage.setMaterialIcon(menuButton, FontImage.MATERIAL_MENU);
        menuButton.addActionListener(e -> getToolbar().openSideMenu());
        
        Container titleCmp = BoxLayout.encloseY(
                        FlowLayout.encloseIn(menuButton)
                
                );
        
        FloatingActionButton fab = FloatingActionButton.createFAB('.');
      //  fab.getAllStyles().setMarginUnit(Style.UNIT_TYPE_PIXELS);
     //   fab.getAllStyles().setMargin(BOTTOM, completedTasks.getPreferredH() - fab.getPreferredH() / 2);
        tb.setTitleComponent(fab.bindFabToContainer(titleCmp, CENTER, BOTTOM));
                setupSideMenu(res);
                games_container();
                date_prod();
    }
    protected void showOtherForm(Resources res) {
        new StatsForm(res).show();
    }
    
    private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }
    public void date_prod()
{


Label prod = new Label("New Product");
       c.add(prod);
    ArrayList<produit> listp=ServiceProduits.getInstance().date();
for(produit p : listp) {

    list.add(createContactContainer(p));

}

 list.setScrollableX(true);
 add(c).add(list);

}
private Container createContactContainer(produit person) {
    Label name = new Label("");
    Label email = new Label("");
    //Label pic = new Label("");
    Label pic2 = new Label("");



    String prix=String.valueOf(person.getPrix());

    urli = Statics.BASE_URL + "/uploads/img/"+person.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {

             }

                Image i = URLImage.createToStorage(enc, urli, urli);
              i = i.fill(180,180);
              Label pic = new Label(i, "ProduitPicture");

    Button view = new Button("view");
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    name.getAllStyles().setBgTransparency(0);
    name.getAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
    email.getAllStyles().setBgTransparency(0);

     view.addActionListener((evtt)->new product(person).show());

    cnt.add(name);
    cnt.add(email);
    cnt.add(view);
    cnt.add(pic2);
    name.setText(person.getNom());
    email.setText(prix+"DT");
  //  pic.setIcon(person.getImg());
    return BorderLayout.center(cnt).
        add(BorderLayout.WEST, pic);
}

private void games_container()
{
     Container c1 = new Container(BoxLayout.x());
        c1.setScrollableX(true);
         ArrayList<Game> list;
        list = new ArrayList<>();
        //ArrayList<Tournoi>    k = new ArrayList<>();
        list = ServiceGame.getInstance1().getAll();

        // k = ServiceTournoi.getInstance().getAll();
        for (Game ev : list) {
             Container cx = new Container(BoxLayout.x());
             Label sep = new Label("                    ");
             cx.add(sep);
             c1.add(cx);
           String url = Statics.BASE_URL_K+"/uploads/"+ev.getClub1().getLogo();
          //  System.out.println(url);
             Container c2 = new Container(BoxLayout.x());
             c2.setScrollableX(true);
             Container club1_c = new Container(BoxLayout.y());
            
             Image placeholder = Image.createImage(200,200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
         //   System.out.print("IMAGE = "+ev.getClub1().getLogo());
            Image urlim = URLImage.createToStorage(enc, ev.getClub1().getLogo(), url );
         urlim = urlim.fill(200,200);
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
              SpanLabel sp9 = new SpanLabel("  " + ev.getClub1().getNomc());
              SpanLabel sp19 = new SpanLabel("  " );
               SpanLabel r1score = new SpanLabel("   "+ev.getR1());
              SpanLabel placer = new SpanLabel(" ---- ");
                SpanLabel r2score = new SpanLabel("   "+ev.getR2());
                Container club2_c = new Container(BoxLayout.y());
                Image placeholder1 = Image.createImage(200,200);
            EncodedImage enc1 = EncodedImage.createFromImage(placeholder1, false); 
                Image urlim1 = URLImage.createToStorage(enc1, ev.getClub2().getLogo(), url + "/" +ev.getClub2().getLogo());
            urlim1 = urlim1.fill(200,200);
                ImageViewer imgV1 = new ImageViewer();
            imgV1.setImage(urlim1);
             SpanLabel sp10 = new SpanLabel("  " + ev.getClub2().getNomc());
                
                
              club1_c.add(imgV).add(sp9);
              c2.add(club1_c);
              c2.add(r1score);
              c2.add(placer);
               c2.add(r2score);
               club2_c.add(imgV1).add(sp10).add(sp19);
               c2.add(club2_c);
               
             /* Button btnView = new Button();
              btnView.addActionListener(e-> {
                  new HomeForm().show();
              });
              c2.setLeadComponent(btnView); */
             c1.add(c2); 
        }
        this.add(c1);
}

    
}
