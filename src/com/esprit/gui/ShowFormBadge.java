/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Badge;
import com.esprit.services.ServiceBadge;
import java.util.ArrayList;

/**
 *
 * @author ksaay
 */
public class ShowFormBadge extends Form {
    public ShowFormBadge(Form previous)
    {
        ArrayList<Badge> badges;
        setTitle("list Badges");
setLayout(BoxLayout.y());
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
/*
badges = ServiceBadge.getInstance().getAll();
for(Badge b : badges){
CheckBox cbBadge = new CheckBox(b.getNomB());
cbBadge.setEnabled(false);
cbBadge.setSelected(true);
add(b.getNomB());

}*/

    }
}
