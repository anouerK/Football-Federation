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
public class MainArticle extends Form{
      
    public MainArticle(Resources res,Form previous) {
        setTitle("Home Page");
        setLayout(BoxLayout.y());
        Button btnAdd = new Button("add Article");
        Button btnShow = new Button("Show Articles");
         Button btnShowF = new Button("Show Articles Front");
          Button btnShowT = new Button("Show Interactions TEST");
        
 Toolbar tb = this.getToolbar();


this.show();
btnAdd.addActionListener((evt) ->{
      new AddArticle(this).show();
});
btnShow.addActionListener((evt) -> {
    ListArticle f = new ListArticle(this,res);
    f.show();
   // f.show();
});
btnShowF.addActionListener((evt) -> {
    ListArticleFront f = new ListArticleFront(this,res);
    f.show();
   // f.show();
});
btnShowT.addActionListener((evt) -> {
    InteractionTest f = new InteractionTest();
    f.show();
   // f.show();
});
addAll(btnAdd,btnShow);
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
