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
import com.codename1.ui.util.Resources;
import com.esprit.entities.Badge;
import com.esprit.services.ServiceBadge;
import java.io.IOException;


/**
 *
 * @author ksaay
 */
public class AddBadge extends Form{
    String filePath;
    public AddBadge(Form previous,Resources res)
    {
        setTitle("add Badge");
setLayout(BoxLayout.y());
TextField tfName = new TextField("","Badge Name");
//TextField tfImg = new TextField("","Badge Img");
TextField tfNb = new TextField("","Badge nb");
tfName.getAllStyles().setFgColor(0x607D8B);
       tfNb.getAllStyles().setFgColor(0x607D8B);
Button btnAdd = new Button("Add");
Button btnupload = new Button("Upload");
btnupload.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      filePath = Capture.capturePhoto(Display.getInstance().getDisplayWidth(),-1);
      System.out.print(filePath);
}
});
btnAdd.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (tfName.getText() == "")
        {
            Dialog.show("Error","Badge Name not valid","OK",null);
        }
        else
        {
            
            if (filePath == "")
        {
            Dialog.show("Error","Badge IMG not valid","OK",null);
        }
            else
            {
               if (tfNb.getText() == "")
        {
            Dialog.show("Error","Badge nb not valid","OK",null);
        }
            else
            {
                
                Badge badge = new Badge(tfName.getText(),filePath,Integer.parseInt(tfNb.getText()));
                   try {
                       ServiceBadge.getInstance().addBadge(badge);
                   } catch (IOException ex) {
                      Dialog.show("Error","Badge nb not valid","OK",null);
                   }
                 
            }
            }
        }
    }
});
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
new ListBadge(new MainBadge(res, new BaseFront()),res).showBack();
});
addAll(tfName,btnupload,tfNb,btnAdd);
        
        
        
    }
    
}
