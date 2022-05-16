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
import com.esprit.entities.Stade;
import com.esprit.utils.Statics;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Ahmed.A.Hsouna
 */
public class ServiceStade{
    
        public ArrayList<Stade> stades;

    public static ServiceStade instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceStade() {
        req = new ConnectionRequest();
    }
    
    
    public static ServiceStade getInstance() {
        if (instance == null) {
            instance = new ServiceStade();
        }
        return instance;
    }

    public boolean addStade(Stade t) {
        System.out.println(t);
        System.out.println("********");
        System.out.println(t.getPhoto());
        String url = Statics.BASE_URL_K + "/addStadesj?noms=" + t.getNoms() + "&nbrP=" + t.getNbrP() + "&lieu=" + t.getLieu() + "&etat=" + t.getEtat()+ "&photo=" + t.getPhoto();
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

    public ArrayList<Stade> affichStade(String jsontext) {

        try {
            stades = new ArrayList<>();

            JSONParser jsonp = new JSONParser();
            Map<String, Object> mapStade = jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) mapStade.get("root");
            for(Map<String, Object> obj : list) {
                Stade to = new Stade();
                float id = Float.parseFloat(obj.get("id").toString());
                float nbe = Float.parseFloat(obj.get("nbrP").toString());
                to.setId((int) id);
                to.setNoms(obj.get("noms").toString());
                to.setNbrP((int) nbe);
                to.setLieu(obj.get("lieu").toString());
                to.setEtat(obj.get("etat").toString());
                to.setPhoto(obj.get("photo").toString());

                stades.add(to);

            }
        } catch (Exception ex) {

        }

        return stades;

    }

    
    
     public ArrayList<Stade> getAll(){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/viewSj";
        Stade t=new Stade();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              stades=affichStade(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stades;
    }
     
     
      public boolean  Delete(int id){
       String url = Statics.BASE_URL_K + "/deleteStadee/" +id;

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
      
      public List<String>fillintot()
    {
        List<String> tournoiss = new ArrayList<String>();
        stades = getAll();
        for(Stade tr:stades)
        {
            tournoiss.add(tr.getNoms());
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return tournoiss;
    }
      
       public boolean  ModifierS(Stade arbitre){
                    String url = Statics.BASE_URL_K +"/updateGameJSON/"+arbitre.getId()+"?noms="+arbitre.getNoms()+"&nbrP="+arbitre.getNbrP()+"&lieu="+arbitre.getLieu()+"&etat="+arbitre.getEtat()+"&photo="+arbitre.getPhoto();
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
