/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import com.esprit.services.ServiceBadge;
import com.esprit.services.ServiceUser;
import java.io.IOException;


/**
 *
 * @author ksaay
 */
public class RegisterUser extends Form {
    public RegisterUser(Form previous,Resources res)
    {
         super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
          setUIID("LoginForm");
    setTitle("Register");
  Container welcome = FlowLayout.encloseCenter(
                new Label("Welcome, ", "WelcomeWhite"),
                new Label("Sir", "WelcomeBlue")
        );
  getTitleArea().setUIID("Container");
TextField email = new TextField("","email");
TextField username = new TextField("","username");
TextField mdp = new TextField("","password");
Button btnAdd = new Button("Register");
Button btnLogin = new Button("Login");
btnAdd.setUIID("LoginButton");
btnLogin.setUIID("LoginButton");
btnLogin.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        Resources res=UIManager.initFirstTheme("/theme");
      new LoginForm(res).show();
    }
  });
btnAdd.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (email.getText() == "" || username.getText() == "" || mdp.getText() == "" )
        {
            Dialog.show("Error","Error","OK",null);
        }
        else
        {
            
                
                User user = new User(email.getText(),username.getText(),mdp.getText());
                  
                       ServiceUser.getInstance().Register(user);
                  
                 
            
            
        }
    }
});
/*
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});*/
//Image profilePic = res.getImage("user-picture.jpg");
  Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
       
       
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 10);
      
  Container by = BoxLayout.encloseY(
           BorderLayout.center(loginIcon).
                        add(BorderLayout.WEST, welcome),
         
                BorderLayout.center(email),
                BorderLayout.center(username),
                         BorderLayout.center(mdp),
                btnAdd,
                btnLogin
            //    createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        
        
    }
}
