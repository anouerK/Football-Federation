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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.esprit.projet.entities.Reclamation;
import com.esprit.services.ServiceReclamation;




/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class ReclamationForm extends com.codename1.ui.Form {
    private Resources res=UIManager.initFirstTheme("/theme");
    public ReclamationForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources()); 
                setLayout(new BoxLayout(BoxLayout.Y_AXIS));
                FloatingActionButton fab = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
		fab.getAllStyles().setBgColor(0x5ed84f);
		fab.addActionListener((ActionListener) (ActionEvent evt1) -> {
    

              new ReclamationAddForm(this).show();


            }); 
		fab.bindFabToContainer(this.getContentPane());
        ServiceReclamation sr = new ServiceReclamation();
        for (Reclamation T :  sr.getAllReclamation()) {
                  addItem(T);
        }
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                        e -> new ProfileForm(res).show());
    }
    
    public ReclamationForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
       // getToolbar().addCommandToLeftBar("", mat, e -> new SignInForm().show());
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



    
    public void addItem(Reclamation T) {

        
        Container C = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Label Objet = new Label("Objet : "+T.getObjet());
		Label desc = new Label("Description : "+T.getdescR());
        Label status = new Label("Status : "+T.getStatus());
		Button delete = new Button("delete");
                Button edite = new Button("edit");
		                                                        
        delete.setTextPosition(LEFT);
		 edite.setTextPosition(LEFT);

        
 //Graphic

  Font f = Font.createSystemFont(Font.FACE_PROPORTIONAL, Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
        Objet.getAllStyles().setFgColor(0x607D8B);
        Objet.getAllStyles().setFont(f);
        status.getAllStyles().setFgColor(0xFFFFFF);
        desc.getAllStyles().setFgColor(0xFFFFFF);
        delete.getAllStyles().setFgColor(0xfa626b);
        delete.getAllStyles().setFont(f);     
        
        C.getAllStyles().setBgColor(0x28afd0);
        C.getAllStyles().setBgTransparency(255);
		        edite.getAllStyles().setFgColor(0x6967ce);
				
        C.getAllStyles().setPaddingUnit(Style.UNIT_TYPE_PIXELS);
        C.getAllStyles().setPadding(30,30,30,30);
        C.getAllStyles().setBorder(RoundRectBorder.create()
                .strokeColor(0xFFFFFF)
                .cornerRadius(2)
                .topOnlyMode(true).stroke(3,false));
		
	FontImage.setMaterialIcon(delete, FontImage.MATERIAL_DELETE);
	        FontImage.setMaterialIcon(edite, FontImage.MATERIAL_EDIT);

	
	       ServiceReclamation s = new ServiceReclamation();
        delete.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
     s.deleteReclamation(T.getId());
        Dialog.show("Success", "Reclamation Deleted", "OK", null);
            new ReclamationUpdateForm(T).show();
}
});
        edite.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
    new ReclamationUpdateForm(T).show();
}
});
         /*      
        delete.((ActionListener) (ActionEvent evt) -> {
        s.deleteReclamation(T.getId());
        Dialog.show("Success", "Reclamation Deleted", "OK", null);
		    new ReclamationForm().show();
        });*//*
		 edit.addPointerPressedListener((ActionListener) (ActionEvent evt) -> {
			new ReclamationUpdateForm(T).show();
        });*/
		 
        C1.add(edite);
		C1.add(delete);
		
		
        
		C.add(Objet);
                C.add(desc);
        C.add(status);
		C.add(C1);
        add(C);
    

    } 

}
