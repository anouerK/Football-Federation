/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author ksaay
 */
public class MainBadge extends Form {
   
    public MainBadge(Resources res,Form previous) {
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button("add Badgeee");
        Button btnShow = new Button("Show Badges");
        Button statb = new Button("Stat Badge");
        
 Toolbar tb = this.getToolbar();
/*
ComboBox<Map<String, Object>> combo = new ComboBox<> (
        
      
          createListEntry("A Game of Thrones", "1996"),
          createListEntry("A Clash Of Kings", "1998"),
          createListEntry("A Storm Of Swords", "2000"),
          createListEntry("A Feast For Crows", "2005"),
          createListEntry("A Dance With Dragons", "2011"),
          createListEntry("The Winds of Winter", "2016 (please, please, please)"),
          createListEntry("A Dream of Spring", "Ugh"));
  combo.addActionListener((evt) -> {
      System.out.println(combo.getSelectedIndex());
  });
  combo.setRenderer(new GenericListCellRenderer<>(new MultiButton(), new MultiButton()));
  System.out.println("Width : "+combo.getWidth());
  combo.setWidth(combo.getWidth() / 2);
   System.out.println("Width : "+combo.getWidth());
this.add(combo);
*/

this.show();
btnAdd.addActionListener((evt) ->{
      new AddBadge(this,res).show();
});
btnShow.addActionListener((evt) -> {
    ListBadge f = new ListBadge(this,res);
    f.show();
   // f.show();
});
statb.addActionListener((evt) -> {
    StatBadge f = new StatBadge(this);
    f.show();
   // f.show();
});
addAll(btnAdd,btnShow,statb);
 getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
previous.showBack();
});
    }
    private Map<String, Object> createListEntry(String name, String date) {
    Map<String, Object> entry = new HashMap<>();
    entry.put("Line1", name);
    entry.put("Line2", date);
    return entry;
}
    
}
