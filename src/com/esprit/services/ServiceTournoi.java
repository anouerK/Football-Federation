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
import com.esprit.entities.Rewards;
import com.esprit.entities.Tournoi;
import com.esprit.utils.Statics;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
/**
 *
 * @author oumayma
 */
public class ServiceTournoi {
     public ArrayList<Tournoi> tournois;
    
    public static ServiceTournoi instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTournoi() {
         req = new ConnectionRequest();
    }

    public static ServiceTournoi getInstance() {
        if (instance == null) {
            instance = new ServiceTournoi();
        }
        return instance;
    }
    
    public boolean deleteTournoi(int t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/deletebj?id="+t;
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
    public boolean addTournoi(Tournoi t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/addTournoij?nomt=" + t.getNomt()+"&logo=" + t.getLogo() + "&dated=" + t.getDated()+"&datef=" + t.getDatef()+"&typet=" + t.getTypet()+"&nbrc=" + t.getNbrc();
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
    public boolean updateTournoi(Tournoi t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/updateTournoij?&id="+t.getId()+"&nomt=" + t.getNomt()+"&logo=" + t.getLogo() + "&dated=" + t.getDated()+"&datef=" + t.getDatef()+"&typet=" + t.getTypet()+"&nbrc=" + t.getNbrc();
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
    public List<String>fillintot()
    {
        List<String> tournoiss = new ArrayList<String>();
        tournois = getAll();
        for(Tournoi tr:tournois)
        {
            
            tournoiss.add(tr.getNomt());
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return tournoiss;
    }
     public List<Tournoi>fillinto()
    {
        List<Tournoi> tournoiss = new ArrayList<Tournoi>();
        tournois = getAll();
        for(Tournoi tr:tournois)
        {
              tr.getId();
           // tournoiss.add(tr.getNomt());
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return tournoiss;
    }
    public ArrayList<Tournoi> affichTournoi(String jsontext){
   
           

         
                try{
                                tournois=new ArrayList<>();

                     JSONParser  jsonp=new JSONParser();
              Map<String,Object> mapTournoi =jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
               List<Map<String,Object>> list = (List<Map<String,Object>>) mapTournoi.get("root");
               for(Map<String,Object>obj : list){
                   Tournoi to =new Tournoi();
                   float id=Float.parseFloat(obj.get("id").toString());
                      float nbrc=Float.parseFloat(obj.get("nbrc").toString());
                                        to.setId((int)id);
                                        to.setNomt(obj.get("nomt").toString());
                                        to.setDated(obj.get("dated").toString());
                                        to.setDatef(obj.get("datef").toString());
                                        to.setTypet(obj.get("typet").toString());
                                        to.setNbrc((int)nbrc);
                                        to.setLogo(obj.get("logo").toString());
              
                      tournois.add(to);
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return tournois;
    }
       public ArrayList<Tournoi> getI(int t){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/viewbj"+t;
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              tournois=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournois;
    }
    public ArrayList<Tournoi> getAll(){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/viewbj";
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              tournois=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournois;
    }
     public ArrayList<Tournoi> RechercheT(String t){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/search/"+t;
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              tournois=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournois;
    }
    
public Tournoi getN(int t) {
        String url = Statics.BASE_URL_K + "/viewbj?nom=" + t;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tournois = affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tournois.get(0);
    }
 
}
