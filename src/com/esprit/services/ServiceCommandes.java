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
import com.esprit.entities.Badge;
import com.esprit.entities.commande;
import com.esprit.entities.User;
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
public class ServiceCommandes {
    
            public ConnectionRequest req;
     public boolean resultOK;
    private static ServiceCommandes instance=null;
      public ArrayList<commande> tasks;
      
      
       private ServiceCommandes()
    {
        req=new ConnectionRequest();
    }
       
        public static ServiceCommandes getInstance() {
        if(instance==null)
                instance=new ServiceCommandes();
        return instance;
    }
        
         public boolean addcommande(commande t) {
        String url = Statics.BASE_URL + "addcom?user=" + t.getUser().getId() + "&produit=" + t.getProduit().getId()+"&prixc="+t.getPrix()+"&qtec="+t.getQtec()+"&etatc="+t.getEtat()+"&taillec="+t.getTaillec(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK= req.getResponseCode() == 200; //Code HTTP 200 OK
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
        
         
         
         
         
         
          public ArrayList<commande> parseTaskscom(){
      ArrayList<commande> result = new ArrayList<>();
         String url = Statics.BASE_URL+"getcom";
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
                   commande m = new commande(); 
                   
                   float id = Float.parseFloat(obj.get("id").toString());
                   String taille= obj.get("taille").toString();
                   Float prix= Float.parseFloat(obj.get("prixU").toString());
                   float etat= Float.parseFloat(obj.get("etat").toString());
                   float qtec= Float.parseFloat(obj.get("qte").toString());
                   
                   
                 
    Map<String,Object> u = (Map<String,Object>) obj.get("idU");               
                 float id_u = Float.parseFloat(u.get("id").toString());
               String name = u.get("username").toString();
               String mdp = u.get("mdp").toString();
               String email = u.get("email").toString();
               String role = u.get("role").toString();
               String img = u.get("img").toString();
               float nbp = Float.parseFloat(u.get("nbp").toString());
               int nbp2 = (int)nbp;
               Badge b = new Badge();
               
                User  user= new User((int)id_u,name,email,mdp,role,img,b,nbp2);
                   
                 Map<String,Object> p = (Map<String,Object>) obj.get("idP"); 
                 //System.out.println(p); 
             
              
                   float idp = Float.parseFloat(p.get("id").toString());
                   String nom= p.get("nomP").toString();
                   String desc=p.get("descr").toString();
                   String taille1=p.get("taille").toString();
                   String taille2=p.get("taille2").toString();
                   String color = p.get("couleur").toString();
                   String imgp = p.get("img").toString();
                   Float prixp= Float.parseFloat(p.get("prix").toString());
                   float qtep = Float.parseFloat(p.get("qte").toString());
                   
                    String str = p.get("categorie").toString();
                    ArrayList<String> test = catsplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                
                    categories  badge= new categories((int)badge_id,nomB);
                    
                    
                        String str2 = p.get("marquep").toString();
                    ArrayList<String> test2 = marsplit(str2);
                    float mar_id =  Float.parseFloat(test2.get(0));
                    String nomm =  test2.get(1);
                
                    marques  marquep= new marques((int)mar_id,nomm);
                    
                
                    produit  prod= new produit((int)idp,nom,taille1,taille2,desc,imgp,color,prixp,(int)qtep,badge,marquep);
                    
                    
                    
            
                  m.setId((int)id);
                  m.setTaillec(taille);
                  m.setPrix(prix);
                  m.setUser(user);
                  m.setEtat((int)etat);
                  m.setProduit(prod);
                  m.setQtec((int)qtec);
                  
                           
                   
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
