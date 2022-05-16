/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.esprit.gui;


import com.codename1.components.FloatingActionButton;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;

import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.projet.entities.Reclamation;
import com.esprit.projet.entities.Reclamation;
import com.esprit.services.ServiceReclamation;


/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class ReclamationUpdateForm extends com.codename1.ui.Form {

    public ReclamationUpdateForm(Reclamation r) {
        this(com.codename1.ui.util.Resources.getGlobalResources()); 
                setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label lbO = new Label("  "+" Objet : ");
            TextField tfO = new TextField(r.getObjet(), " Objet");
            
            Label lbD = new Label("  "+" Description : ");
            TextField tfD = new TextField(r.getdescR(), " Description");
			
			Label lbS = new Label("  "+" Status : ");
            TextField tfS = new TextField(r.getStatus(), " Status");
            
 Picker p = new Picker();
   
       //  String k[] = toArray(new String[listusername.size()]);
       
p.setStrings("traited","untraited");
			Button btn = new Button("Valider");
             
             c.add(lbO);
             c.add(tfO);
			 c.add(lbD);
             c.add(tfD);
             c.add(lbS);
             c.add(p);
             
             c.add(btn);	
             add(c);
            //addAll(name,owner,end_date,end_date,description,btn);

            show();
            btn.addActionListener((ActionListener) (ActionEvent evt1) -> {
          
           
         
                Reclamation T=new Reclamation(r.getId(), tfO.getText(), tfD.getText(), p.getSelectedString());
                System.out.println("Reclamation = "+T);
                ServiceReclamation sp=new ServiceReclamation();
                 
            
                if(!tfO.getText().equals("") && !tfD.getText().equals("")  ){
                    
					
					sp.updateReclamation(T, r.getId());
					new ReclamationForm().showBack();
					
                }
                else{
                 Dialog.show("WARNING"," verifié vos parametre ", "OK",null);
                }

 

            });
        
    }
    
    public ReclamationUpdateForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        //getToolbar().addCommandToLeftBar("", mat, e -> new SignInForm().show());
        getContentPane().setUIID("SignInForm");
        
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
    }

    class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
           
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            
        }

        public void dataChanged(int type, int index) {
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Reclamation");
        setName("ReclamationForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
    }// </editor-fold>



}