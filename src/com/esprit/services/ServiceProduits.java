/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.utils.Statics;
import com.esprit.entities.categories;
import com.esprit.entities.marques;
import com.esprit.entities.produit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lord
 */
public class ServiceProduits {
    
    
            public ConnectionRequest req;
     public boolean result;
    private static ServiceProduits instance=null;
      public ArrayList<produit> tasks;
      
      
       private ServiceProduits()
    {
        req=new ConnectionRequest();
    }
       
        public static ServiceProduits getInstance() {
        if(instance==null)
                instance=new ServiceProduits();
        return instance;
    }
    
        
        public boolean addP(produit t) {
 String url = Statics.BASE_URL + "addp?nomP=" + t.getNom()+"&taille1="+t.getTaille()+"&taille2="+t.getTaille2()+"&couleurP="+t.getCouleur()+"&prixP="+t.getPrix()+"&descrP="+t.getDesc()+"&quantiteP="+t.getQte()+"&categorieP="+t.getCat().getId()+"&marqueP="+t.getMar().getId();//création de l'URL
       // req.setUrl(url);// Insertion de l'URL de notre demande de connexion
       
          MultipartRequest cr = new MultipartRequest();
          cr.setUrl(url);
 

         try {
                    cr.addData("file", t.getImg(), "");
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
         cr.setFilename("file", "testt" + ".jpg");//any unique name you want
          InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                 NetworkManager.getInstance().addToQueueAndWait(cr);
                  Dialog.show("Success", "Done", "OK", null); 
        
        cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
             
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
        
        public ArrayList<String> catsplit(String s )
    {
        ArrayList<String> test=new ArrayList();
        String word="" ; 
          char[] chars = s.toCharArray();
        //  System.out.println(chars[0]);
        for (char ch: chars)
        {
            if ((ch != ',' ) )
            {
                  if( (ch != '{' )  && (ch != '}' ) && (ch!= '[') && (ch!= ']') )
                {
                 word = word + ch;
                }
                  
                  if((word.contains("id=")) || (word.contains("typeC=")) )
                  {
                      word="";
                  }
            }
            else
            {
              
                     test.add(word);
                word="";
                
               
            }
           
        }
         test.add(word);
                word="";
      return test;
    }
        
        
        
         public ArrayList<String> marsplit(String s )
    {
        ArrayList<String> test=new ArrayList();
        String word="" ; 
          char[] chars = s.toCharArray();
        //  System.out.println(chars[0]);
        for (char ch: chars)
        {
            if ((ch != ',' ) )
            {
                  if( (ch != '{' )  && (ch != '}' ) && (ch!= '[') && (ch!= ']') )
                {
                 word = word + ch;
                }
                  
                  if((word.contains("id=")) || (word.contains("nomM=")) )
                  {
                      word="";
                  }
            }
            else
            {
              
                     test.add(word);
                word="";
                
               
            }
           
        }
         test.add(word);
                word="";
      return test;
    }
        
        
        
     public ArrayList<produit> parseTasksP(){
      ArrayList<produit> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getp";
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
                   produit m = new produit(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomP").toString();
                   String desc=obj.get("descr").toString();
                   String taille1=obj.get("taille").toString();
                   String taille2=obj.get("taille2").toString();
                   String color = obj.get("couleur").toString();
                   String img = obj.get("img").toString();
                   Float prix= Float.parseFloat(obj.get("prix").toString());
                   float qte = Float.parseFloat(obj.get("qte").toString());
                   
                    String str = obj.get("categorie").toString();
                    ArrayList<String> test = catsplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                
                    categories  badge= new categories((int)badge_id,nomB);
                    
                    
                        String str2 = obj.get("marquep").toString();
                        
                        //System.out.println("marksssssssssssssss::::::"+str2);
                    ArrayList<String> test2 = marsplit(str2);
                    float mar_id =  Float.parseFloat(test2.get(0));
                    String nomm =  test2.get(1);
                
                    marques  marque= new marques((int)mar_id,nomm);
                    
                    
                
                   m.setId((int)id);
                   m.setNom(nom);
                   m.setDesc(desc);
                   m.setTaille(taille1);
                   m.setTaille2(taille2);
                   m.setImg(img);
                   m.setCouleur(color);
                   m.setCat(badge);
                   m.setMar(marque);
                   m.setPrix(prix);
                   m.setQte((int)qte);
                           
                   
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
     
     
       public ArrayList<produit> parseprod2(String val ){
      ArrayList<produit> result = new ArrayList<>();
         String url = Statics.BASE_URL+"searchprod/" + val;
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
                   produit m = new produit(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomP").toString();
                   String desc=obj.get("descr").toString();
                   String taille1=obj.get("taille").toString();
                   String taille2=obj.get("taille2").toString();
                   String color = obj.get("couleur").toString();
                   String img = obj.get("img").toString();
                   Float prix= Float.parseFloat(obj.get("prix").toString());
                   
                    String str = obj.get("categorie").toString();
                    ArrayList<String> test = catsplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                
                    categories  badge= new categories((int)badge_id,nomB);
                    
                    
                        String str2 = obj.get("marquep").toString();
                    ArrayList<String> test2 = marsplit(str);
                    float mar_id =  Float.parseFloat(test2.get(0));
                    String nomm =  test2.get(1);
                
                    marques  marque= new marques((int)mar_id,nomm);
                    
                    
                
                   m.setId((int)id);
                   m.setNom(nom);
                   m.setDesc(desc);
                   m.setTaille(taille1);
                   m.setTaille2(taille2);
                   m.setImg(img);
                   m.setCouleur(color);
                   m.setCat(badge);
                   m.setMar(marque);
                   m.setPrix(prix);
                           
                   
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
     
     
     public ArrayList<produit> parseTasksPtri(String tri){
      ArrayList<produit> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getptri?tri="+tri;
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
                   produit m = new produit(); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomP").toString();
                   String desc=obj.get("descr").toString();
                   String taille1=obj.get("taille").toString();
                   String taille2=obj.get("taille2").toString();
                   String color = obj.get("couleur").toString();
                   String img = obj.get("img").toString();
                   Float prix= Float.parseFloat(obj.get("prix").toString());
                   
                    String str = obj.get("categorie").toString();
                    ArrayList<String> test = catsplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                
                    categories  badge= new categories((int)badge_id,nomB);
                    
                    
                        String str2 = obj.get("marquep").toString();
                    ArrayList<String> test2 = marsplit(str);
                    float mar_id =  Float.parseFloat(test2.get(0));
                    String nomm =  test2.get(1);
                
                    marques  marque= new marques((int)mar_id,nomm);
                    
                    
                
                   m.setId((int)id);
                   m.setNom(nom);
                   m.setDesc(desc);
                   m.setTaille(taille1);
                   m.setTaille2(taille2);
                   m.setImg(img);
                   m.setCouleur(color);
                   m.setCat(badge);
                   m.setMar(marque);
                   m.setPrix(prix);
                           
                   
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
     
     
     
     
     
     
     
     
       public boolean deletep(int x) {


       String url = Statics.BASE_URL + "deletep?id="+x;
       //String url = Statics.BASE_URL + "addTournoij";
       req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
      return result;
    }
public boolean updatep(produit t)
    {
        //String url = Statics.BASE_URL+"/addBadgej?nomB="badge.getNomB()"&nb="badge.getNb()"&logoB="badge.getLogoB();
 String url = Statics.BASE_URL + "updatep?nomP="+t.getNom()+"&file="+t.getImg()+ "&id="+ t.getId()+"&taille1="+t.getTaille()+"&taille2="+t.getTaille2()+"&couleurP="+t.getCouleur()+"&prixP="+t.getPrix()+"&descrP="+t.getDesc()+"&quantiteP="+t.getQte()+"&categorieP="+t.getCat().getId()+"&marqueP="+t.getMar().getId();//création de l'URL
        MultipartRequest cr = new MultipartRequest();
          cr.setUrl(url);
 
if(t.getImg()!="")
{
         try {
                    cr.addData("file", t.getImg(), "");
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
         cr.setFilename("file", "testt" + ".jpg");//any unique name you want
          InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                 NetworkManager.getInstance().addToQueueAndWait(cr);
                  Dialog.show("Success", "Done", "OK", null); 
}

         cr.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                result = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
             
             
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;

    }
 public ArrayList<produit> date(){
      ArrayList<produit> result = new ArrayList<>();
         String url = Statics.BASE_URL+"produit_date";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>(){
       @Override
       public void actionPerformed(NetworkEvent evt){
           JSONParser jsonp;
           jsonp =new JSONParser();
           try{
               Map<String,Object> mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
          List<Map<String,Object>> listofMaps= (List<Map<String,Object>>)mapReclamations.get("root");
               for(Map<String,Object> obj2: listofMaps){
                   produit m = new produit(); 
                     Map<String,Object> obj = (Map<String,Object>) obj2.get("product"); 
                   float id = Float.parseFloat(obj.get("id").toString());
                   String nom= obj.get("nomP").toString();
                   String desc=obj.get("descr").toString();
                   String taille1=obj.get("taille").toString();
                   String taille2=obj.get("taille2").toString();
                   String color = obj.get("couleur").toString();
                   String img = obj.get("img").toString();
                   Float prix= Float.parseFloat(obj.get("prix").toString());
                   float qte = Float.parseFloat(obj.get("qte").toString());
                   
                    String str = obj.get("categorie").toString();
                    ArrayList<String> test = catsplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                
                    categories  badge= new categories((int)badge_id,nomB);
                    
                    
                        String str2 = obj.get("marquep").toString();
                        
                       
                    ArrayList<String> test2 = marsplit(str2);
                    float mar_id =  Float.parseFloat(test2.get(0));
                    String nomm =  test2.get(1);
                
                    marques  marque= new marques((int)mar_id,nomm);
                   
                    
                
                   m.setId((int)id);
                   m.setNom(nom);
                   m.setDesc(desc);
                   m.setTaille(taille1);
                   m.setTaille2(taille2);
                   m.setImg(img);
                   m.setCouleur(color);
                   m.setCat(badge);
                   m.setMar(marque);
                   m.setPrix(prix);
                   m.setQte((int)qte);
                           
                   
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
    
}
