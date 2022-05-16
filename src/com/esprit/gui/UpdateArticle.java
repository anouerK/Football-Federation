/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Article;
import com.esprit.entities.User;
import com.esprit.services.ServiceArticle;
import com.esprit.services.ServiceBadge;
import com.esprit.services.ServiceUser;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ksaay
 */
public class UpdateArticle extends Form {
    String filePath="";
    public UpdateArticle(Form previous,Article e)
    { ArrayList<User> list;
        list = ServiceUser.getInstance().getAll();
        
        List<String> listusername = ServiceArticle.getInstance().fillintou(list);
      
        Picker p = new Picker();
        
         String k[] = listusername.toArray(new String[listusername.size()]);
       
p.setStrings(k);
int index = 0;
for (User user : list) {
        if (user.getId() != e.getUser().getId()) {
           index++;
        }
        else
            break;
      
    }
p.setSelectedString(k[index].toString());
          setTitle("update Article");
setLayout(BoxLayout.y());
TextField titre = new TextField(e.getTitre(),"Article Title");
TextField descr = new TextField(e.getDescr(),"Article Descr");
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
Button btnSave = new Button("save");
btnSave.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (titre.getText() == "")
        {
            Dialog.show("Error","Badge Name not valid","OK",null);
        }
        else
        {
            if (descr.getText() == "")
        {
            Dialog.show("Error","Badge IMG not valid","OK",null);
        }
            
            else
            {
                
                int index = (int)p.getSelectedStringIndex();
                
               
                User u = new User(list.get(index));
              //public Article(int id, String titre, String descr, String datea, String img, User user) {
                Article article = new Article(e.getId(),titre.getText(),descr.getText(),e.getDatea(),filePath,u);
                if(ServiceArticle.getInstance().modifierArticle(article)){
                   Dialog.show("Success","Article updated","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            
        }
    }
});
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
addAll(titre,descr,p,btnupload,btnSave);
        
     
    
    }
}

