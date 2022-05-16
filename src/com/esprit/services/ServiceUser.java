/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.codename1.components.InfiniteProgress;
import com.esprit.utils.Statics ;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import com.esprit.main.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ksaay
 */
public class ServiceUser {
     public ConnectionRequest req;
    private static ServiceUser instance;
    private boolean resultOK;   
 
  public ArrayList<User> users;
    private ServiceUser() {
        req = new ConnectionRequest();
        
    }
    public boolean deleteUser(int x) {
       
     
       String url = Statics.BASE_URL + "deleteUerJS?id="+x;
       
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
      public void updateUser(User u)
    {
        String url = Statics.BASE_URL+"updateuJs?id="+u.getId()+"&username="+u.getUsername()+"&mdp="+u.getMdp()+"&email="+u.getEmail();
        MultipartRequest cr = new MultipartRequest();
           cr.setUrl(url);
 
       
         try {
                    cr.addData("file", u.getImg(), "");
                } catch (IOException ex) {
                    Dialog.show("Error", ex.getMessage(), "OK", null);
                }
         cr.setFilename("file", "testt" + ".jpg");
          InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                 NetworkManager.getInstance().addToQueueAndWait(cr);
                  Dialog.show("Success", "Done", "OK", null);
            ConnectUser(MyApplication.u_c);
                
    }
    public boolean accesProfile(User user) {
        
     
       String url = Statics.BASE_URL + "userAcessProfile?id="+user.getId();
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
    public boolean Register(User user) {
        
     
       String url = Statics.BASE_URL + "UserRegistJ?email=" + user.getEmail()+"&username=" + user.getUsername()+ "&mdp=" + user.getMdp();
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
    public ArrayList<String> badgesplit(String s )
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
                  
                  if((word.contains("id=")) || (word.contains("nomB=")) || (word.contains("logoB=") || (word.contains("nb=")) ))
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
    public static ServiceUser getInstance()
    {
        if(instance == null)
            instance = new ServiceUser();
        return instance;
    }
 
    public ArrayList<User> affichTournoi(String jsontext){
   
           

         
                try{ users=new ArrayList<>();
            JSONParser j = new JSONParser();
              JSONParser j2 = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
              
                    String str = obj.get("badge").toString();
                    ArrayList<String> test = badgesplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                    String BadgeImg =  test.get(2);
                    Badge  badge= new Badge((int)badge_id,nomB,BadgeImg,(int)Float.parseFloat(test.get(3)));
           
              
               String username = obj.get("username").toString();
                 String email = obj.get("email").toString();
String mdp = obj.get("mdp").toString();
String role = obj.get("role").toString();
String img = obj.get("img").toString();
//Badge badge = (Badge)obj.get("badge");
float nbp =  Float.parseFloat(obj.get("nbp").toString());
float id = Float.parseFloat(obj.get("id").toString());
//Badge bad = new Badge();
User b = new User(username,email,mdp,role,img,badge,(int)nbp);
b.setId((int)id);
          //    System.out.println(b);
                      users.add(b); 
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return users;
    }
    
    public ArrayList<User> getAll(){
      

         String url = Statics.BASE_URL+"viewuj";
        req.setUrl(url);
       req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              users=affichTournoi(new String(req.getResponseData()));
          
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
        
    }
    public User ConnectUser(User u){
      

         String url = Statics.BASE_URL+"cnJson?username="+u.getUsername()+"&mdp="+u.getMdp();
        req.setUrl(url);
       req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    MyApplication.u_c = new User(getUser(new String(req.getResponseData())));
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                }
               
          
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return MyApplication.u_c;
        
    }
     public User getUser(String jsontext) throws IOException{
            if(jsontext.length()>4)
             {
               
                 JSONParser j = new JSONParser();
              JSONParser j2 = new JSONParser();
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
                 String str = obj.get("badge").toString();
                    ArrayList<String> test = badgesplit(str);
                    float badge_id =  Float.parseFloat(test.get(0));
                    String nomB =  test.get(1);
                    String BadgeImg =  test.get(2);
                    Badge  badge= new Badge((int)badge_id,nomB,BadgeImg,(int)Float.parseFloat(test.get(3)));
           
             
               String username = obj.get("username").toString();
                 String email = obj.get("email").toString();
String mdp = obj.get("mdp").toString();
String role = obj.get("role").toString();
String img = obj.get("img").toString();
//Badge badge = (Badge)obj.get("badge");
float nbp =  Float.parseFloat(obj.get("nbp").toString());
float id = Float.parseFloat(obj.get("id").toString());
//Badge bad = new Badge();
User b = new User(username,email,mdp,role,img,badge,(int)nbp);
b.setId((int)id);
              System.out.println(b);
          
                     return b;
             }
             else
             {
                 User uc = new User();
            
                  return uc;
             }
                    
                  

    }
   

   
}
