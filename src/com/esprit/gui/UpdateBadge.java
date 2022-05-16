/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Badge;
import com.esprit.services.ServiceBadge;

/**
 *
 * @author ksaay
 */
public class UpdateBadge extends Form{
    public UpdateBadge(Form previous,Badge e)
    {
         setTitle("update Badge");
setLayout(BoxLayout.y());
TextField tfName = new TextField(e.getNomB(),"Badge Name");
TextField tfImg = new TextField(e.getLogoB(),"Badge Img");
TextField tfNb = new TextField(String.valueOf(e.getNb()),"Badge nb");
tfName.getAllStyles().setFgColor(0x607D8B);
       tfNb.getAllStyles().setFgColor(0x607D8B);
Button btnAdd = new Button("update");
btnAdd.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (tfName.getText() == "")
        {
            Dialog.show("Error","Badge Name not valid","OK",null);
        }
        else
        {
            if (tfImg.getText() == "")
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
                
                Badge badge = new Badge(tfName.getText(),tfImg.getText(),Integer.parseInt(tfNb.getText()));
                badge.setId(e.getId());
               if(ServiceBadge.getInstance().modifierBadge(badge)){
                   Dialog.show("Success","Badge updated","OK",null);
               }
               else
               {
                   Dialog.show("Error","Request Error","OK",null);
               }
            }
            }
        }
    }
});
getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
addAll(tfName,tfImg,tfNb,btnAdd);
        
     
    
    }
}
