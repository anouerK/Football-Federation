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
import com.esprit.entities.club;
import com.esprit.entities.Game;
import com.esprit.entities.Tournoi;
import com.esprit.utils.Statics;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
/**
 *
 * @author oumayma
 */
public class ServiceGame {
     public ArrayList<Game> rewards;
    
    public static ServiceGame instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceGame() {
         req = new ConnectionRequest();
    }

    public static ServiceGame getInstance1() {
        if (instance == null) {
            instance = new ServiceGame();
        }
        return instance;
    }
     public boolean addGame(Game t) {
       // System.out.println(t);
     //   System.out.println("******");
     
       String url = Statics.BASE_URL_K + "/addGame?idt=" + t.getTournoi().getId()+"&idc1=" + t.getClub1().getId()+"&r1=" + t.getR1() + "&r2=" + t.getR2()+"&idc2=" + t.getClub2().getId()+"&idar=" + t.getArbitre().getId()+"&ids=" + t.getStade().getId()+"&dated=" + t.getDeted();
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
     
     
     
      public boolean deleteGame(int t) {
       // System.out.println(t);
       // System.out.println("******");
     
       String url = Statics.BASE_URL_K + "/deleteG?id="+t;
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
     
    
    public ArrayList<Game> affichGame(String jsontext){

                try{
                                rewards=new ArrayList<>();

                     JSONParser  jsonp=new JSONParser();
              Map<String,Object> mapRewards =jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
               List<Map<String,Object>> list = (List<Map<String,Object>>) mapRewards.get("root");
              
               for(Map<String,Object>obj : list){
                //   System.out.println("hne");
                    
                  //  System.out.print(obj);
                   Game to =new Game();
                   float id=Float.parseFloat(obj.get("id").toString());
                      float rank=Float.parseFloat(obj.get("r1").toString());
                       float prix=Float.parseFloat(obj.get("r2").toString());
                       
                                    String date =obj.get("dated").toString();
                                 //  System.out.println("hne0");
                       Map<String,Object> tournoih = (Map<String,Object>) obj.get("tournoi");
                   float tournoi_id=Float.parseFloat(tournoih.get("id").toString());
                
                   Map<String,Object> club1h = (Map<String,Object>) obj.get("club1");
                   float club1_id=Float.parseFloat(club1h.get("id").toString());
                     //  System.out.println("hne1");
                   Map<String,Object> club2h = (Map<String,Object>) obj.get("club2");
                   float club2_id=Float.parseFloat(club2h.get("id").toString());
                    // System.out.println("hne6");
                   Map<String,Object> arbitreh = (Map<String,Object>) obj.get("arbitre");
                   float arbitre_id=Float.parseFloat(arbitreh.get("id").toString());
                  //  System.out.println("hne2");
                   Map<String,Object> stadeh = (Map<String,Object>) obj.get("stade");
                   float stade_id=Float.parseFloat(stadeh.get("id").toString());
                  
                                        to.setId((int)id);
                                        to.setR1((int)rank);
                                        to.setR2((int)prix);
                                      //  to.setDeted(obj.get("deted").toString());
                                        to.setDeted(date);
                                
                                        Tournoi us = new Tournoi((int)tournoi_id,tournoih.get("nomt").toString());
                                        to.setTournoi(us);
                                       
                                        club us1 = new club((int)club1_id,club1h.get("nomc").toString());
                                        us1.setLogo(club1h.get("logo").toString());
                                        to.setClub1(us1);
                                        
                                        club us2 = new club((int)club2_id,club2h.get("nomc").toString());
                                        us2.setLogo(club2h.get("logo").toString());
                                        to.setClub2(us2);
                                     //   System.out.println("aaaa");
                                        Arbitre us3 = new Arbitre((int)arbitre_id,arbitreh.get("nomA").toString());
                                        to.setArbitre(us3);
                                       
                                        Stade us4 = new Stade((int)stade_id,stadeh.get("noms").toString());
                                        to.setStade(us4);
                                  
                                       
                                      //  to.setImg(obj.get("img").toString());
             
                      rewards.add(to);
                 
               }
                
            }
                catch(Exception ex){
            
            }
           
    
            
       
            
        return rewards;
    }
    public ArrayList<Game> getAll(){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/viewG";
        //Tournoi t=new Tournoi();
        req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichGame(new String(req.getResponseData()));
             
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
    }
    
    
    public boolean ModifierG(Game t) {

           String url = Statics.BASE_URL_K +"/updateGameeJSON/"+t.getId()+"?idt=" + t.getTournoi().getId()+"&idc1=" + t.getClub1().getId()+"&r1=" + t.getR1() + "&r2=" + t.getR2()+"&idc2=" + t.getClub2().getId()+"&idar=" + t.getArbitre().getId()+"&ids=" + t.getStade().getId()+"&dated=" + t.getDeted();
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