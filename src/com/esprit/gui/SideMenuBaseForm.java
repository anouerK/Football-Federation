

package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.main.MyApplication;
import com.esprit.utils.Statics;
import java.io.IOException;

/**
 * Common code that can setup the side menu
 *
 * @author Shai Almog
 */
public abstract class SideMenuBaseForm extends Form {
private Resources res=UIManager.initFirstTheme("/theme");
    public SideMenuBaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseForm(String title) {
        super(title);
    }

    public SideMenuBaseForm() {
    }

    public SideMenuBaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu() {
        
            Image imgs;
ImageViewer imgv;
String urli ;
EncodedImage enc = null;
  urli = Statics.BASE_URL + "uploads/img/"+MyApplication.u_c.getImg();
             try {
                 enc = EncodedImage.create("/error404.jpg");
             } catch (IOException ex) {

             }
              int mm = Display.getInstance().convertToPixels(6);
  EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(mm * 6, mm * 8, 0), false);
                    
                Image profilePic = URLImage.createToStorage(placeholder, urli, urli);
                 profilePic = profilePic.fill(450,350);
        
        Image mask = res.getImage("round-mask.png");
        mask = mask.scaledHeight(mask.getHeight() / 4 * 3);
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label("  "+MyApplication.u_c.getUsername(), profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
        getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_DASHBOARD,   e -> new BaseFront().show());
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_EXIT_TO_APP,  e -> new ProfileForm(res).show());
        getToolbar().addMaterialCommandToSideMenu("  Tournements", FontImage.MATERIAL_EVENT,  e -> new ListTasksForm(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Rewards", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListRewardsForm(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Games", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskGameF(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Stadiums", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskStadeF(this).show());
         getToolbar().addMaterialCommandToSideMenu("  referees", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskArbitreF(this).show());
         getToolbar().addMaterialCommandToSideMenu("  Clubs", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new Listclub(this).show());
         getToolbar().addMaterialCommandToSideMenu("  Players", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new Listjoueur(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Articles", FontImage.MATERIAL_TRENDING_UP,  e -> new MainArticle(res,this).show());
        getToolbar().addMaterialCommandToSideMenu("  Badges", FontImage.MATERIAL_ACCESS_TIME,  e -> new MainBadge(res,this).show());
 getToolbar().addMaterialCommandToSideMenu("  Produits", FontImage.MATERIAL_ACCESS_TIME,  e ->new ShowProduit(new ProfileForm(res)).show());
 getToolbar().addMaterialCommandToSideMenu("  Categories", FontImage.MATERIAL_ACCESS_TIME,  e ->new ShowCategorie(new ProfileForm(res)).show());
 getToolbar().addMaterialCommandToSideMenu("  Marque", FontImage.MATERIAL_ACCESS_TIME,  e ->new ShowForm(new ProfileForm(res)).show());
        getToolbar().addMaterialCommandToSideMenu("  Users", FontImage.MATERIAL_SETTINGS,  e ->new MainUser(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Contacts", FontImage.MATERIAL_CHAT,  e -> new ReclamationForm().show());
     //   getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> showOtherForm(res));
        
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
    }
    
    protected abstract void showOtherForm(Resources res);
}
