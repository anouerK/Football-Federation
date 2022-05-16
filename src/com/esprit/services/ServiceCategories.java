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
import com.esprit.entities.categories;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Lord
 */
public class ServiceCategories {
    
        public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceCategories instance=null;
      public ArrayList<categories> tasks;
      categories badge;
      
      
       private ServiceCategories()
    {
        req=new ConnectionRequest();
    }
       
        public static ServiceCategories getInstance() {
        if(instance==null)
                instance=new ServiceCategories();
        return instance;
    }
        
         public boolean addcategorie(categories t) {
        String url = Statics.BASE_URL + "addc?typeC=" + t.getTypec(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
         
         
         
          public ArrayList<categories> parsecat(){
      ArrayList<categories> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getc";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   categories m = new categories(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("typeC").toString();
                   
                   //System.out.println(nom);
                   m.setId((int)id);
                   m.setTypec(nom);
                   
                   result.add(m);
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
  }

          
          
          public categories parsecatadd(int idc){
        ArrayList<categories> result = new ArrayList<>();
        String val=String.valueOf(idc);
         String url = Statics.BASE_URL+"getc";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   categories m = new categories(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("typeC").toString();
                   if((int) id == idc)
                   {  
               //    System.out.println(nom);
                   m.setId((int)id);
                   m.setTypec(nom);
                   
                   result.add(m);
                   
                     badge= new categories((int)id,nom);
                   }
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return badge;
  }
          
          
          
    /*      
           public ArrayList<categories> parsecat2(String val ){
      ArrayList<categories> result = new ArrayList<>();
         String url = Statics.BASE_URL+"/searchcat/" + val;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj: listofMaps){
                   categories m = new categories(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("typeC").toString();
                   
                   System.out.println(nom);
                   m.setId((int)id);
                   m.setTypec(nom);
                   
                   result.add(m);
               }
           }
          catch(Exception ex){
              ex.printStackTrace();
           }
               
            
        }
  });
       
         NetworkManager.getInstance().addToQueueAndWait(req);
         return result;
  }
          
    */      
          
          
          
          
          
          
          public boolean deletecat(int x) {


       String url = Statics.BASE_URL + "deletec?id="+x;
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
public boolean modifiercat(categories badge)
    {
        //String url = Statics.BASE_URL+"/addBadgej?nomB="badge.getNomB()"&nb="badge.getNb()"&logoB="badge.getLogoB();
        String url = Statics.BASE_URL+"updatec?typeC="+badge.getTypec()+"&id="+badge.getId();
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
       
}