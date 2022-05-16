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
public class ServiceRewards {
     public ArrayList<Rewards> rewards;
    
    public static ServiceRewards instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceRewards() {
         req = new ConnectionRequest();
    }
    public ArrayList<Rewards> RechercheReward(int t){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/searchReward?&id="+t;
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichRewards(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
    }
    public ArrayList<Rewards> TriRewardD(){

        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/listRDjson";
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichRewards(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
    }
        public ArrayList<Rewards> TriRewardA(){

        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/listRAjson";
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichRewards(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
    }

    public static ServiceRewards getInstance1() {
        if (instance == null) {
            instance = new ServiceRewards();
        }
        return instance;
    }
     public boolean addRewards(Rewards t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/addj?&rank=" + t.getRank()+"&trophe=" + t.getTrophe() + "&prix=" + t.getPrixR()+"&img=" + t.getImg()+"&id=" + t.getT().getId();
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
     public boolean updateRewards(Rewards t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/updateReward?&idR="+t.getId()+"&rank=" + t.getRank()+"&trophe=" + t.getTrophe() + "&prix=" + t.getPrixR()+"&img=" + t.getImg()+"&idt=" + t.getT().getId();
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
      public boolean deleteRewards(int t) {
        System.out.println(t);
        System.out.println("********");
     
       String url = Statics.BASE_URL_K + "/deletej?id="+t;
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
      public ArrayList<Rewards> affichRewards(String jsontext){
   
           

         
                try{
                                rewards=new ArrayList<>();

                     JSONParser  jsonp=new JSONParser();
              Map<String,Object> mapRewards =jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
               List<Map<String,Object>> list = (List<Map<String,Object>>) mapRewards.get("root");
               for(Map<String,Object>obj : list){
                  //  System.out.print(obj);
                   Rewards to =new Rewards();
                   float id=Float.parseFloat(obj.get("idR").toString());
                      float rank=Float.parseFloat(obj.get("rank").toString());
                       float prix=Float.parseFloat(obj.get("prixR").toString());
                     Map<String,Object> tournoih = (Map<String,Object>) obj.get("tournoi");
                   float tournoi_id=Float.parseFloat(tournoih.get("id").toString());
                   
                                        to.setId((int)id);
                                        to.setRank((int)rank);
                                        to.setTrophe(obj.get("trophe").toString());
                                        to.setPrixR(prix);
                                
                                        Tournoi us = new Tournoi((int)tournoi_id,tournoih.get("nomt").toString());
                                        to.setT(us);
                                        to.setImg(obj.get("img").toString());
              
                      rewards.add(to);
                  
               }
            }
                catch(Exception ex){
            
            }
           
    
            
       
            
        return rewards;
    }
    public ArrayList<Rewards> getAll(){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/viewrewardsj";
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichRewards(new String(req.getResponseData()));
             
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
    }
    public List<String>fillintot()
    {
        List<String> tournoiss = new ArrayList<String>();
        rewards = getAll();
        for(Rewards tr:rewards)
        {
            tournoiss.add(tr.getImg());
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return tournoiss;
    }
}
