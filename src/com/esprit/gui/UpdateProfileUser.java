/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import com.esprit.main.MyApplication;
import com.esprit.services.ServiceBadge;
import com.esprit.services.ServiceUser;
import java.io.IOException;

/**
 *
 * @author ksaay
 */
public class UpdateProfileUser extends Form{
    String filePath;
    public  UpdateProfileUser(Form previous)
            {
                setLayout(BoxLayout.y());
                setUIID("LoginForm");
                 getTitleArea().setUIID("Container");
                  this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                  TextField username = new TextField(MyApplication.u_c.getUsername(),"username ");
                 
                   TextField newmdp = new TextField("","new mdp");
                   Button btnupload = new Button("Upload");
                   Button btnupdate = new Button("update");
                   btnupload.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
     
}
});
                   btnupdate.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (username.getText() == "" )
        {
            Dialog.show("Error"," Name not valid","OK",null);
        }
        else
        {
            
            if (filePath == "")
        {
            Dialog.show("Error"," IMG not valid","OK",null);
        }
            else
            {
               if (newmdp.getText()== "")
        {
            Dialog.show("Error","set mdp","OK",null);
        }
            else
            {
                MyApplication.u_c.setUsername(username.getText().toString());
                MyApplication.u_c.setMdp(newmdp.getText().toString());
                MyApplication.u_c.setImg(filePath);
                User  u = new User(MyApplication.u_c);
                   
                       ServiceUser.getInstance().updateUser(u);
                
                 
            }
            }
        }
    }
});
                   addAll(username,newmdp,btnupload,btnupdate);
            }
           
    
}
