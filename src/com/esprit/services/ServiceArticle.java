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
import com.esprit.entities.Article;
import com.esprit.entities.Badge;
import com.esprit.entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author ksaay
 */
public class ServiceArticle {
    public ConnectionRequest req;
    private static ServiceArticle instance;
    private boolean resultOK;   
 
  public ArrayList<Article> articles;
    private ServiceArticle() {
        req = new ConnectionRequest();
        
    }
    public static ServiceArticle getInstance()
    {
        if(instance == null)
            instance = new ServiceArticle();
        return instance;
    }
    public boolean deleteArticle(int x) {
       
     
       String url = Statics.BASE_URL + "deleteartj?id="+x;
      
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
     public ArrayList<Article> getAllS(String searched){
      
         String url = Statics.BASE_URL+"searchArticle?str="+searched;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              articles=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       // NetworkManager.getInstance().addToQueueAsync(req);
        return articles;
        
    }
     public boolean modifierArticle(Article article)
    {
        String url = Statics.BASE_URL+"updateArticleja?id="+article.getId()+"&titre="+article.getTitre()+"&descr="+article.getDescr()+"&user="+article.getUser().getId();
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
   
    public ArrayList<String> usersplit(String s )
    {
        ArrayList<String> test=new ArrayList();
        String word="" ; 
          char[] chars = s.toCharArray();
      
        for (char ch: chars)
        {
            if ((ch != ',' ) )
            {
                  if( (ch != '{' )  && (ch != '}' ) && (ch!= '[') && (ch!= ']') )
                {
                 word = word + ch;
                }
                  
                  if((word.contains("badge=")) ||(word.contains("img=")) ||(word.contains("role=")) ||(word.contains("email=")) ||(word.contains("id=")) || (word.contains("username=")) || (word.contains("mdp=") || (word.contains("nbp=")) ))
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
    
    public void addArticle(Article article)
    {
       
        String url = Statics.BASE_URL+"addArticlej?titre="+article.getTitre()+"&descr="+article.getDescr()+"&user="+article.getUser().getId();
           MultipartRequest cr = new MultipartRequest();
           cr.setUrl(url);
 
       
        try {
            cr.addData("file", article.getImg(), "");
        } catch (IOException ex) {
            //Logger.getLogger(ServiceArticle.class.getName()).log(Level.SEVERE, null, ex);
        }
         cr.setFilename("file", "testt" + ".jpg");//any unique name you want
          InfiniteProgress prog = new InfiniteProgress();
                Dialog dlg = prog.showInifiniteBlocking();
                cr.setDisposeOnCompletion(dlg);
                 NetworkManager.getInstance().addToQueueAndWait(cr);
                  Dialog.show("Success", "Done", "OK", null);
            
                
    }
    public static List<String> fillintou(ArrayList<User> l)
    {
        //String[] users={};
        List<String> users = new ArrayList<String>();
        for(User u:l)
        {
            users.add(u.getUsername());
        }
        return users;
    }
    public static List<String> fillintoue(ArrayList<User> l)
    {
        //String[] users={};
        List<String> users = new ArrayList<String>();
        for(User u:l)
        {
            users.add(u.getEmail());
        }
        return users;
    }
    public ArrayList<Article> getAll(int order){
      

         String url = Statics.BASE_URL+"viewartj?nb="+order;
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              articles=affichTournoi(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return articles;
        
    }
     public ArrayList<Article> affichTournoi(String jsontext){
   
           

         
                try{ articles=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
                String descr = obj.get("descr").toString();
String titre = obj.get("titre").toString();
String datea = obj.get("datea").toString();
String img = obj.get("img").toString();
String user = obj.get("user").toString();

ArrayList<String> userl =  usersplit(user);

float user_id  = Float.parseFloat(userl.get(0));
String username  =userl.get(1);
String mdp  =userl.get(2);
int nbp =(int)Float.parseFloat(userl.get(3));
String email  =userl.get(4);
String role  =userl.get(6);
String img_u=userl.get(7);
Badge b= new Badge();
User u = new User((int)user_id,username,email,mdp,role,img_u,b,nbp);

float id =  Float.parseFloat(obj.get("id").toString());

//(int id, String username, String email, String mdp, String role, String img, Badge badge, int nbp)
//User u = new User();
Article a = new Article((int)id,titre,descr,datea,img,u);
              
                      articles.add(a);
                  
               }
            }catch(Exception ex){
            
            }
           
    
            
       
            
        return articles;
    }
}
