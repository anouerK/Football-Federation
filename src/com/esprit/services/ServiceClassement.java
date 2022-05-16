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
import com.esprit.entities.Tournoi;
import com.esprit.entities.classement;
import com.esprit.entities.club;
import com.esprit.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author oumayma
 */
public class ServiceClassement {
      public ArrayList<classement> rewards;
    
    public static ServiceClassement instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceClassement() {
         req = new ConnectionRequest();
    }

    public static ServiceClassement getInstance() {
        if (instance == null) {
            instance = new ServiceClassement();
        }
        return instance;
    }
     public ArrayList<classement> affichClassement(Tournoi x,String jsontext){
   
           
       
         
                try{
                    System.out.println("JSON="+jsontext);
                                rewards=new ArrayList<>();

                     JSONParser  jsonp=new JSONParser();
              Map<String,Object> mapCl =jsonp.parseJSON(new CharArrayReader(jsontext.toCharArray()));
               List<Map<String,Object>> list = (List<Map<String,Object>>) mapCl.get("root");
                System.out.println(list);
               for(Map<String,Object>obj : list){
                  //  System.out.print(obj);
                   classement to =new classement();
                    System.out.println("7");
                 // float id=Float.parseFloat(obj.get("id").toString());
                   System.out.println("1");
                   
                    System.out.println("1");
                     Map<String,Object> club = (Map<String,Object>) obj.get("club");
                      System.out.println("1");
                   float club_id=Float.parseFloat(club.get("id").toString());
                    System.out.println("1");
                                        to.setId((int)0);
                                       //Map<String,Object> cl = (Map<String,Object>) obj.get("pts");
                                        float pts=Float.parseFloat(obj.get("pts").toString());
                                        //  classement t=new classement((int)pts);
                                       // to.setId((int)pts);
                                to.setPts((int)pts);
                                     
                                         System.out.println("1");
                                        to.setTournoi(x);
                                        club c=new club((int)club_id,club.get("nomc").toString(),club.get("logo").toString());
                                         System.out.println("1");
                                     to.setClub(c);
              
                      rewards.add(to);
                  
               }
            }
                catch(Exception ex){
            
            }
           
    
            
       
            
        return rewards;
    }
    public ArrayList<classement> getAl(int t,Tournoi x){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL_K +"/affclas?id="+t;
        //Tournoi t=new Tournoi();
        //////
          req.setUrl(url);
     req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              rewards=affichClassement(x,new String(req.getResponseData()));
             
                req.removeResponseListener(this);
            }
        });
 
       
        NetworkManager.getInstance().addToQueueAndWait(req);
        return rewards;
        
        
        ///
      
    
    }
    public List<String>fillintot()
    {
        List<String> tournoiss = new ArrayList<String>();
      //  rewards = getAl();
        for(classement tr: rewards)
        {
          //  tournoiss.add(tr.getImg());
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return tournoiss;
    }
}
