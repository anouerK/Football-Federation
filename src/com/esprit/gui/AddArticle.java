/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.capture.Capture;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Article;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceBadge;
import com.esprit.services.ServiceUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ksaay
 */
public class AddArticle extends Form {
    String filePath="";
    
    public AddArticle(Form previous)
    {
        ArrayList<User> list;
        list = ServiceUser.getInstance().getAll();
        
        List<String> listusername = ServiceArticle.getInstance().fillintou(list);
        List<String> listemails = ServiceArticle.getInstance().fillintoue(list);
      
        Picker p = new Picker();
        p.setType(Display.PICKER_TYPE_STRINGS);
         String k[] = listusername.toArray(new String[listusername.size()]);
       
p.setStrings(k);
p.setSelectedString(k[0].toString());

        setTitle("add Article");
setLayout(BoxLayout.y());
TextField titre = new TextField("","Article Title");
TextField descr = new TextField("","Article Descr");
 titre.getAllStyles().setFgColor(0x607D8B);
       descr.getAllStyles().setFgColor(0x607D8B);
       
           
  Button btnupload = new Button("Upload");
btnupload.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
      System.out.println(filePath);
}
});
Button btnAdd = new Button("Add");
btnAdd.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (titre.getText() == "")
        {
            Dialog.show("Error","Article Name not valid","OK",null);
        }
        else
        {
            if (descr.getText() == "")
        {
            Dialog.show("Error","Article descr not valid","OK",null);
        }
            else
            {
                int index = (int)p.getSelectedStringIndex();
                
               
                User u = new User(list.get(index));
              
                Article article = new Article(titre.getText(),descr.getText(),filePath,u);
                ServiceArticle.getInstance().addArticle(article); 
               Message m = new Message("New Article [Turbo Devs]");
String emails[]=listemails.toArray(new String[listemails.size()]);
Display.getInstance().sendMessage(emails , "New Article has been added  to our  website do not forget to read it", m);
                /*
               Message m = new Message("omkkk");
m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
boolean success = m.sendMessageViaCloudSync("Codename One", "ksaay2000@gmail.com", "Name Of User", "Message Subject","Check out Codename One at https://www.codenameone.com/");
            System.out.print(success);*/
            }
        }
    }
});
//getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
//previous.showBack();
//});
addAll(titre,descr,btnAdd,p,btnupload);
      getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
        
        
    }
    
}
