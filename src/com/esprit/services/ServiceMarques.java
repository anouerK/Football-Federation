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
import com.esprit.entities.marques;
import com.esprit.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lord
 */
public class ServiceMarques {
    public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceMarques instance=null;
      public ArrayList<marques> tasks;
      marques badge;
      
    private ServiceMarques()
    {
        req=new ConnectionRequest();
    }

    public static ServiceMarques getInstance() {
        if(instance==null)
                instance=new ServiceMarques();
        return instance;
    }
    
  public boolean addTask(marques t) {
        String url = Statics.BASE_URL + "addm?nomM=" + t.getNomM(); //création de l'URL
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
  
  //afichage 
   
  public ArrayList<marques> parseTasks(){
      ArrayList<marques> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getm";
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
                   marques m = new marques(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomM").toString();
                   
                   System.out.println(nom);
                   m.setId((int)id);
                   m.setNomM(nom);
                   
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
  
  
  public marques parseTasksid(int idm){
      ArrayList<marques> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getm";
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
                   marques m = new marques(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomM").toString();
                   if((int)id==idm)
                   {
                   System.out.println(nom);
                   m.setId((int)id);
                   m.setNomM(nom);
                    badge= new marques((int)id,nom);
                   result.add(m);
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
  
  
  
  public boolean deletemarque(int x) {


       String url = Statics.BASE_URL + "deletem?id="+x;
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
public boolean modifiermarque(marques badge)
    {
        //String url = Statics.BASE_URL+"/addBadgej?nomB="badge.getNomB()"&nb="badge.getNb()"&logoB="badge.getLogoB();
        String url = Statics.BASE_URL+"updatem?nomM="+badge.getNomM()+"&id="+badge.getId();
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
