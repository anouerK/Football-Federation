/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

/**
 *
 * @author Ahmed.A.Hsouna
 */
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.esprit.entities.Arbitre;
import com.esprit.entities.Game;
import com.esprit.entities.Stade;
import com.esprit.entities.Tournoi;
import com.esprit.entities.club;
import com.esprit.services.ServiceGame;
import com.esprit.services.ServiceTournoi;
import com.esprit.services.serviceclub;
import com.esprit.services.ServiceStade;
import com.esprit.services.ServiceArbitre;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oumayma
 */
public class AjoutGame extends Form {

    static ArrayList<Tournoi> tr;
    static ArrayList<club> tr1;
    static ArrayList<club> tr2;
    static ArrayList<Arbitre> tr3;
    static ArrayList<Stade> tr4;
    static ArrayList<Tournoi> trall;

    public AjoutGame(Form previous) {

        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));

        setTitle("Add a new Game");
        setLayout(BoxLayout.y());

        Button btnValider = new Button("ajout Game");

        TextField tfName = new TextField("", "r1");
        TextField tfdated = new TextField("", "r2");
       // TextField tfdatef = new TextField("", "date");
        
        Picker dateReclamation = new Picker();
        dateReclamation.setType(Display.PICKER_TYPE_DATE);
        dateReclamation.setUIID("TextFieldBlack");
      //  addStringValue("Date Reclamation", dateReclamation);

        tr = new ArrayList<>();
        tr1 = new ArrayList<>();
        tr2 = new ArrayList<>();
        tr3 = new ArrayList<>();
        tr4 = new ArrayList<>();

     
      
        tr = ServiceTournoi.getInstance().getAll();
        tr1 = serviceclub.getInstance().getAllclub();
        tr2 = serviceclub.getInstance().getAllclub();
        tr3 = ServiceArbitre.getInstance().getAll();
        tr4 = ServiceStade.getInstance().getAll();

       
        
        
        Picker typeReclamation = new Picker();
        typeReclamation.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi = ServiceTournoi.getInstance().fillintot();
        String[] tournoisnames = listtournoi.toArray(new String[listtournoi.size()]);
        typeReclamation.setUIID("TextFieldBlack");
        typeReclamation.setStrings(tournoisnames);
        //typeReclamation.addString();
        typeReclamation.setSelectedStringIndex(0);
        
        
        
        
        
        
        Picker typeReclamation1 = new Picker();
        typeReclamation1.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi1 = serviceclub.getInstance().fillintot();
        String[] tournoisnames1 = listtournoi1.toArray(new String[listtournoi1.size()]);
        typeReclamation1.setUIID("TextFieldBlack");
        typeReclamation1.setStrings(tournoisnames1);
        //typeReclamation.addString();
        typeReclamation1.setSelectedStringIndex(0);
        
        
        
        
        Picker typeReclamation2 = new Picker();
        typeReclamation2.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi2 = serviceclub.getInstance().fillintot();
        String[] tournoisnames2 = listtournoi2.toArray(new String[listtournoi2.size()]);
        typeReclamation2.setUIID("TextFieldBlack");
        typeReclamation2.setStrings(tournoisnames2);
        //typeReclamation.addString();
        typeReclamation2.setSelectedStringIndex(0);
        
        
        
        
        

          Picker typeReclamation3 = new Picker();
        typeReclamation3.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi3 = ServiceArbitre.getInstance().fillintot();
        String[] tournoisnames3 = listtournoi3.toArray(new String[listtournoi3.size()]);
        typeReclamation3.setUIID("TextFieldBlack");
        typeReclamation3.setStrings(tournoisnames3);
        //typeReclamation.addString();
        typeReclamation3.setSelectedStringIndex(0);

        
        
        
         Picker typeReclamation4 = new Picker();
        typeReclamation4.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listtournoi4 = ServiceStade.getInstance().fillintot();
        String[] tournoisnames4 = listtournoi4.toArray(new String[listtournoi4.size()]);
        typeReclamation4.setUIID("TextFieldBlack");
        typeReclamation4.setStrings(tournoisnames4);
        //typeReclamation.addString();
        typeReclamation4.setSelectedStringIndex(0);
        
        
        
        
        //  addStringValue("tournoi", typeReclamation);
        //  for ( Tournoi ev : k) {
        // typeReclamation.setStrings(ev.getNomt());
        // typeReclamation.setUIID("TextFieldBlack");
        //typeReclamation.setSelectedString("sélectionnez un typeReclamation");
        // ComboBox   sp1 = new ComboBox();
        // sp1.setSelectedItem(ev.getNomt());
        //this.t=t.getNomt();
        //TextField flogo= new   TextField(  this.k.getNomt()+"");
        btnValider.addActionListener((e) -> {
            {
                try {
                    if ((tfName.getText().length() == 0) || (tfdated.getText().length() == 0) || (dateReclamation.getText().length() == 0) ) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    } else {
                        // Map<String,Object>obj;
                        DateFormat format = new SimpleDateFormat("dd-MM-yyyy");

                        int tournoi_index = typeReclamation.getSelectedStringIndex();
                        Tournoi tourno = new Tournoi(tr.get(tournoi_index));
                        
                        
                        int tournoi_id1 = typeReclamation1.getSelectedStringIndex();
                        club tourno1 = new club(tr1.get(tournoi_id1));

                        
                        int tournoi_id2 = typeReclamation2.getSelectedStringIndex();
                        club tourno2 = new club(tr2.get(tournoi_id2));
                        
                        
                        int tournoi_id3 = typeReclamation3.getSelectedStringIndex();
                        Arbitre tourno3 = new Arbitre(tr3.get(tournoi_id3));
                      
                        
                        int tournoi_id4 = typeReclamation4.getSelectedStringIndex();
                        Stade tourno4 = new Stade(tr4.get(tournoi_id4));

                        // Rewards t = new Rewards(Integer.parseInt(tfName.getText()),tfdated.getText(),Integer.parseInt(tfdatef.getText()),type.getText().toString());
                      
                        Game x = new Game(tourno,tourno1,Integer.parseInt(tfName.getText()),Integer.parseInt(tfdated.getText()),tourno2, tourno3, tourno4,format.format(dateReclamation.getDate()));

                        if (ServiceGame.getInstance1().addGame(x)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                             new ListTaskGameF(previous).show();

                        } else {
                            Dialog.show("ERROR", "connn refuse", new Command("OK"));
                        }

                    }
                } catch (NumberFormatException x) {
                    Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                }
            }

        });

        addAll(typeReclamation,typeReclamation1,tfName, tfdated,  typeReclamation2,typeReclamation3,typeReclamation4,dateReclamation, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    /*
     private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
    }*/
}
