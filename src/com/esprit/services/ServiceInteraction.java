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
import com.esprit.entities.Article;
import com.esprit.entities.Badge;

import com.esprit.entities.Interaction;
import com.esprit.entities.User;
import com.esprit.main.MyApplication;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ksaay
 */
public class ServiceInteraction {
      public ConnectionRequest req;
    private static ServiceInteraction instance;
    private boolean resultOK;   
 
  public ArrayList<Interaction> interactions;
    private ServiceInteraction() {
        req = new ConnectionRequest();
        
    }
     public boolean EditInteraction(Interaction interaction)
    {
        String url = Statics.BASE_URL+"editInteraction?id="+interaction.getIdi()+"&descr="+interaction.getDescrp()+"&type="+interaction.getType();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
           @Override
           public void actionPerformed(NetworkEvent evt){
                resultOK = req.getResponseCode() == 200;
               req.removeResponseListener(this);
             
           }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
                
    }
     public boolean deleteInteraction(int x) {
       
     
       String url = Statics.BASE_URL + "deleteintjs?id="+x;
      
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
     public boolean adlJson(Interaction i) {
       
   
       String url = Statics.BASE_URL + "adlJson?ida="+i.getArticle().getId()+"&idu="+MyApplication.u_c.getId();
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
     public boolean removeJL(Article i) {
       
   
       String url = Statics.BASE_URL + "removeJL?ida="+i.getId()+"&idu="+MyApplication.u_c.getId();
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
    public boolean addInteraction(Interaction i) {
       
   
       String url = Statics.BASE_URL + "addCommentJS?type="+i.getType()+"&user="+i.getUser().getId()+"&article="+i.getArticle().getId()+"&descr="+i.getDescrp();
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
    public static ServiceInteraction getInstance()
    {
        if(instance == null)
            instance = new ServiceInteraction();
        return instance;
    }
     public ArrayList<Interaction> getAll(int article_id,Article article){
      

         String url = Statics.BASE_URL+"viewintrj?id="+article_id;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              interactions=afficheinteraction(new String(req.getResponseData()),article);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return interactions;
        
    }
     public ArrayList<Interaction> afficheinteraction(String jsontext,Article article){
                try{ interactions=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
               
                 float idi = Float.parseFloat(obj.get("idi").toString());
String type = obj.get("type").toString();
String descrp = obj.get("descrp").toString();

Map<String,Object> interactionh = (Map<String,Object>) obj.get("user");
  String username_i = interactionh.get("username").toString();
                 String email_i = interactionh.get("email").toString();
String mdp_i = interactionh.get("mdp").toString();
String role_i = interactionh.get("role").toString();
String img_i = interactionh.get("img").toString();
//Badge badge = (Badge)obj.get("badge");
float nbp_i =  Float.parseFloat(interactionh.get("nbp").toString());
float id_i = Float.parseFloat(interactionh.get("id").toString());


Map<String,Object> badgeh = (Map<String,Object>) interactionh.get("badge");
 String nomb = badgeh.get("nomB").toString();
String logoB = badgeh.get("logoB").toString();
float nb =  Float.parseFloat(badgeh.get("nb").toString());
float id_b = Float.parseFloat(badgeh.get("id").toString());
//(int id, int nb, String nomB, String logoB)
Badge b = new Badge((int)id_b,(int)nb,nomb,logoB);
User user_i = new User((int)id_i,username_i,email_i,mdp_i,role_i,img_i,b,(int)nbp_i);
//int idi, String type, String descrp, User user, Article article
Interaction i = new Interaction((int)idi,type,descrp,user_i,article);
              
                      interactions.add(i);
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return interactions;
    }
}
