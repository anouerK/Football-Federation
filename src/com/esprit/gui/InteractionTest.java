/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.entities.Article;
import com.esprit.entities.Interaction;
import com.esprit.services.ServiceInteraction;
import com.esprit.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ksaay
 */
public class InteractionTest extends Form{
    public InteractionTest()
    {
          super("List Interaction");
          Article article = new Article();
     ArrayList<Interaction> listp=ServiceInteraction.getInstance().getAll(1,article);
     Container list = new Container(BoxLayout.y());
     list.setScrollableY(true);
  

for(Interaction c : listp) {
    if((c.getType().contains("comment")) || (c.getType().contains("reply?")));
    list.add(createContactContainer(c));
   
}
  add(list);
          
    }
    private Container createContactContainer(Interaction i) {
    SpanLabel name = new SpanLabel(i.getIdi()+" ["+i.getUser().getUsername()+"] "+i.getDescrp()+" "+i.getArticle().getTitre());
  
   
  
    
    
    Container cnt = new Container(new BoxLayout(BoxLayout.Y_AXIS));
    
    cnt.add(name);
    
   
   
  //  pic.setIcon(person.getImg());
    return BorderLayout.center(cnt);
}
  
    
}
