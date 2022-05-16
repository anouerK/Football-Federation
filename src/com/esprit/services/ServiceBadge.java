/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.components.InfiniteProgress;
import com.esprit.entities.Badge;

import com.esprit.utils.Statics ;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ksaay
 */
public class ServiceBadge {
    public ConnectionRequest req;
    private static ServiceBadge instance;
    private boolean resultOK;   
 
  public ArrayList<Badge> badges;
    private ServiceBadge() {
        req = new ConnectionRequest();
        
    }
    public static ServiceBadge getInstance()
    {
        if(instance == null)
            instance = new ServiceBadge();
        return instance;
    }

public boolean deleteBadge(int x) {
       
     
       String url = Statics.BASE_URL + "deletebaj?id="+x;
       
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
    public  void addBadge(Badge badge) throws IOException
    {
       
        String url = Statics.BASE_URL+"addBadgej?nomB="+badge.getNomB()+"&nb="+badge.getNb();
         MultipartRequest cr = new MultipartRequest();
           cr.setUrl(url);
 
       
         try {
                    cr.addData("file", badge.getLogoB(), "");
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
         cr.setFilename("file", "testt" + ".jpg");
          InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                 NetworkManager.getInstance().addToQueueAndWait(cr);
                  Dialog.show("Success", "Done", "OK", null);
            

                
    }
     public boolean modifierBadge(Badge badge)
    {
        String url = Statics.BASE_URL+"updateBadgeja?nomB="+badge.getNomB()+"&nb="+badge.getNb()+"&logoB="+badge.getLogoB()+"&id="+badge.getId();
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
     
   
    public ArrayList<Badge> affichTournoi(String jsontext){
   
           

         
                try{ badges=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
                String nomb = obj.get("nomB").toString();
String logoB = obj.get("logoB").toString();
float nb =  Float.parseFloat(obj.get("nb").toString());
float id = Float.parseFloat(obj.get("id").toString());
Badge b = new Badge(nomb,logoB,(int)nb);
b.setId((int)id);
              
                      badges.add(b);
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return badges;
    }
    
    public ArrayList<Badge> afficheStatb(String jsontext){
   
           

         
                try{ badges=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
                String nomb = obj.get("nomB").toString();
String logoB = obj.get("logoB").toString();
float count =  Float.parseFloat(obj.get("count").toString());
float id = Float.parseFloat(obj.get("id").toString());
Badge b = new Badge(nomb,logoB,(int)count);
b.setId((int)id);
              
                      badges.add(b);
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return badges;
    }
    
    public ArrayList<Badge> getAll(int order){
      

         String url = Statics.BASE_URL+"viewbaj?nb="+order;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              badges=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return badges;
        
    }
     public ArrayList<Badge> getAllStat(int order){
      

         String url = Statics.BASE_URL+"viewbaj?nb="+order;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              badges=afficheStatb(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return badges;
        
    }
    public ArrayList<Badge> getAllS(String searched){
        

        String url = Statics.BASE_URL+"searchbadge?str="+searched;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              badges=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return badges;
        
    }
    
}
