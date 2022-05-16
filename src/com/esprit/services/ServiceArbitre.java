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
import com.esprit.entities.Arbitre;
import com.esprit.entities.Tournoi;
import com.esprit.utils.Statics;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ServiceArbitre {

    public ArrayList<Arbitre> arbitres;

    public static ServiceArbitre instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceArbitre() {
        req = new ConnectionRequest();
    }

    public static ServiceArbitre getInstance() {
        if (instance == null) {
            instance = new ServiceArbitre();
        }
        return instance;
    }

    public boolean addArbitre(Arbitre t) {
        System.out.println(t);
        System.out.println("********");
        System.out.println(t.getImage());
        String url = Statics.BASE_URL_K + "/addArbitrej?nomA=" + t.getNomA() + "&image=" + t.getImage() + "&nbe=" + t.getNbe() + "&descrp=" + t.getDescrp();
        //String url = Statics.BASE_URL + "addTournoij";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public List<String> fillintot() {
        List<String> tournoiss = new ArrayList<String>();
        arbitres = getAll();
        for (Arbitre tr : arbitres) {
            tournoiss.add(tr.getNomA());
            //  tournoiss.add("");
        }

        //  System.out.println(tournoiss);
        return tournoiss;
    }

    public ArrayList<Arbitre> affichArbitre(String jsontext) {

        try {
            arbitres = new ArrayList<>();

            JSONParser jsonp = new JSONParser();
            Map<String, Object> mapArbitre = jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) mapArbitre.get("root");
            for (Map<String, Object> obj : list) {
                Arbitre to = new Arbitre();
                float id = Float.parseFloat(obj.get("id").toString());
                float nbe = Float.parseFloat(obj.get("nbe").toString());
                to.setId((int) id);
                to.setNomA(obj.get("nomA").toString());
                to.setNbe((int) nbe);
                to.setDescrp(obj.get("descrp").toString());

                to.setImage(obj.get("image").toString());

                arbitres.add(to);

            }
        } catch (Exception ex) {

        }

        return arbitres;

    }

    public ArrayList<Arbitre> getAll() {

        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K + "/viewAj";
        Arbitre t = new Arbitre();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                arbitres = affichArbitre(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return arbitres;
    }

    public boolean Delete(int id) {
        String url = Statics.BASE_URL_K + "/deleteArbitree/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public boolean ModifierA(Arbitre arbitre) {

           String url = Statics.BASE_URL_K +"/updateArbitreJSON/"+arbitre.getId()+"?nomA="+arbitre.getNomA()+"&nbe="+arbitre.getNbe()+"&descrp="+arbitre.getDescrp()+"&image="+arbitre.getImage();
     //   String url = Statics.BASE_URL + "/update/json?nomA=" + arbitre.getNomA() + "&nbe=" + arbitre.getNbe() + "&descrp=" + arbitre.getDescrp() + "&image" + arbitre.getImage();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
}
