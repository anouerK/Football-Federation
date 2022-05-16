/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

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
public abstract class SideMenuBaseFrontForm extends Form {
    public Resources res=UIManager.initFirstTheme("/theme");

    public SideMenuBaseFrontForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public SideMenuBaseFrontForm(String title) {
        super(title);
    }

    public SideMenuBaseFrontForm() {
    }

    public SideMenuBaseFrontForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }
    
    public void setupSideMenu(Resources res) {
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
         String User_connected ;
         if(MyApplication.u_c.getId()!= 0)
        User_connected= MyApplication.u_c.getUsername();
         else
             User_connected = "Visitor";
        
        Label profilePicLabel = new Label("  "+User_connected, profilePic, "SideMenuTitle");
        profilePicLabel.setMask(mask.createMask());

        Container sidemenuTop = BorderLayout.center(profilePicLabel);
        sidemenuTop.setUIID("SidemenuTop");
        
        getToolbar().addComponentToSideMenu(sidemenuTop);
              getToolbar().addMaterialCommandToSideMenu("  Home", FontImage.MATERIAL_DASHBOARD,   e -> new BaseFront().show());
        getToolbar().addMaterialCommandToSideMenu("  Tournements", FontImage.MATERIAL_EVENT,  e -> new TournoiFront(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Games", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskGame(this).show());
        getToolbar().addMaterialCommandToSideMenu("  Stadiums", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskStade(this).show());
         getToolbar().addMaterialCommandToSideMenu("  referees", FontImage.MATERIAL_PLAYLIST_PLAY,  e -> new ListTaskArbitre(this).show());
         getToolbar().addMaterialCommandToSideMenu("  Articles", FontImage.MATERIAL_CHAT_BUBBLE,  e -> new ListArticleFront(this,res).show());
        
        getToolbar().addMaterialCommandToSideMenu("  Store", FontImage.MATERIAL_SHOP,  e -> new store(this).show());
         if(MyApplication.u_c.getId()!= 0)
        getToolbar().addMaterialCommandToSideMenu("  Cart", FontImage.MATERIAL_SHOP,  e -> new Cart(this).show()); 
        if(MyApplication.u_c.getId()!= 0)
        getToolbar().addMaterialCommandToSideMenu("  Contact Us", FontImage.MATERIAL_CHAT,  e -> new ReclamationAddForm(this).show());
        if(MyApplication.u_c.getId()!= 0 && MyApplication.u_c.getRole().equals("admin"))
        getToolbar().addMaterialCommandToSideMenu("  Dashboard", FontImage.MATERIAL_EXIT_TO_APP,  e -> new ProfileForm(res).show());
       if(MyApplication.u_c.getId()!= 0)
        getToolbar().addMaterialCommandToSideMenu("  Account Settings", FontImage.MATERIAL_SETTINGS,  e -> new UserProfile(this,res).show());
          getToolbar().addMaterialCommandToSideMenu("  Position", FontImage.MATERIAL_POWER,  e -> new MapForm());
        if(MyApplication.u_c.getId()!= 0)
        getToolbar().addMaterialCommandToSideMenu("  Logout", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
        else
            getToolbar().addMaterialCommandToSideMenu("  Login", FontImage.MATERIAL_EXIT_TO_APP,  e -> new LoginForm(res).show());
            
    }
    
    protected abstract void showOtherForm(Resources res);
}
