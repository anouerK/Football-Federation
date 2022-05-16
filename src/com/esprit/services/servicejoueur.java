/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.joueur;
import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oumayma cherif
 */
public class servicejoueur {

    public ArrayList<joueur> joueur;

    public static servicejoueur instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public servicejoueur() {
        req = new ConnectionRequest();
    }

    public static servicejoueur getInstance() {
        if (instance == null) {
            instance = new servicejoueur();
        }
        return instance;
    }

    public ArrayList<joueur> parseCat(String jsonText) {
        try {
            joueur = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> joueurListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) joueurListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                joueur e = new joueur();

                try {
                    float cin = Float.parseFloat(obj.get("cin").toString());

                    e.setcin((int) cin);
                } catch (Exception e1) {
                    System.out.println("cin");
                }
try {
                    e.setphoto(obj.get("photo").toString());
                } catch (Exception e2) {
                    System.out.println("photo");
                }
                try {
                    e.setNom(obj.get("nom").toString());
                } catch (Exception e2) {
                    System.out.println("nom");
                }
                try {
                    e.setprenom(obj.get("prenom").toString());
                } catch (Exception e4) {
                    System.out.println("prenom");
                }
                try {
                    e.setnationalite(obj.get("nationalite").toString());
                } catch (Exception e4) {
                    System.out.println("nationalite");
                }

                try {
                    e.setposte(obj.get("poste").toString());
                } catch (Exception e11) {
                    System.out.println("poste");
                }
                try {
                    float age = Float.parseFloat(obj.get("age").toString());

                    e.setage((int) age);
                } catch (Exception e6) {
                    System.out.println("age");
                }

                try {
                    float nbm = Float.parseFloat(obj.get("nbm").toString());

                    e.setnbm((int) nbm);
                } catch (Exception e9) {
                    System.out.println("nbm");
                }

                try {
                    float nba = Float.parseFloat(obj.get("nba").toString());

                    e.setnba((int) nba);
                } catch (Exception e8) {
                    System.out.println("nba");
                }
                try {
                    joueur.add(e);
                } catch (Exception e7) {
                    System.out.println("club");
                }
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return joueur;
    }

    public ArrayList<joueur> getAlljoueur() {
        String url = Statics.BASE_URL_K + "/viewj";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                joueur = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return joueur;
    }

    public boolean Delete(int cin) {
        String url = Statics.BASE_URL_K + "/deletejoueur?&cin=" + cin;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    

    public void addjoueur1(joueur r) {
        String url = Statics.BASE_URL_K + "/addJoueur1?&nom=" + r.getNom() +"&poste="+ r.getposte()+ "&prenom="+ r.getprenom()+ "&age=" + r.getage() +"&nbm="+ r.getnbm() +"&nba=" + r.getnba() +"&natio=" + r.getnationalite()+"&numt="+r.getnumT()+"&photo="+r.getphoto();
    

        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean modifierJoueur(joueur r) {
        String url = Statics.BASE_URL_K + "/updateJoueur/" + r.getcin() + "?nom=" + r.getNom() +"&poste="+ r.getposte()+ "&prenom="+ r.getprenom()+ "&age=" + r.getage() +"&nbm="+ r.getnbm() +"&nba=" + r.getnba() +"&natio=" + r.getnationalite()+"&numt="+r.getnumT()+"&photo="+r.getphoto();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

}
