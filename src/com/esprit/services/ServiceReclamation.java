/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.util.DateUtil;
import com.esprit.main.MyApplication;
import com.esprit.projet.entities.Reclamation;
import com.esprit.utils.Statics;

/**
 *
 * @author Rahmouni
 */
public class ServiceReclamation {
	private ConnectionRequest request;
	public boolean resultOK;

    private boolean responseResult;
    public ArrayList<Reclamation> Reclamations;

	public ServiceReclamation() {
		request = DataSource.getInstance().getRequest();
	}

	public ArrayList<Reclamation> getAllReclamation() {
        String url = Statics.BASE_URL + "mesReclamationsJson";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Reclamations = parseReclamation(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reclamations;
    }
	
	public ArrayList<Reclamation> parseReclamation(String jsonText)  {
        try {
            Reclamations = new ArrayList<>();
            Reclamation p = new Reclamation();
            JSONParser jp = new JSONParser();
            
           //String s = "["+jsonText+"]";
		   System.out.println(jsonText);
            Map<String, Object> ProjectListJson = jp.parseJSON(new CharArrayReader(find_(jsonText).toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjectListJson.get("root");
            for (Map<String, Object> obj : list) {
              
				int idr = (int)Float.parseFloat(obj.get("idr").toString());
                String objet=obj.get("objet").toString();
                String descR = obj.get("descR").toString();
                String status = obj.get("status").toString();
                
              

                Reclamations.add(new Reclamation(idr, objet, descR, status) );
            }


        } catch (IOException ex) {
			
        }

        return Reclamations;
    }
	
	public String  find_(String a){
			if(!a.substring(0,1).equals("[")){
                a = "["+a+"]";
			}
        return a;
    }
	
	public ArrayList<Reclamation> findReclamation(int id) {
        String url = Statics.BASE_URL + "/"+id;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(request.getResponseData()));
                Reclamations = parseReclamation(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return Reclamations;
    }    
	
	public boolean deleteReclamation(int id) {
        String url = Statics.BASE_URL + "supprimerReclamationJson?idr="+id;
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200; //Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
     
	public void ajouterReclamation(Reclamation p) {
       //try {
            
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"ajouterReclamationJson");
            cr.setPost(false);
            cr.addArgument("objet", p.getObjet());
            cr.addArgument("descR", p.getdescR());
            cr.addArgument("user_id", MyApplication.u_c.getId()+"");
            cr.addArgument("status", p.getStatus());
            //->substring(0,10)
           // String start_date = parseDate(p.getDateofcreation().toString() ,"EEE MMM dd HH:mm:ss zzz yyyy", "yyyy-MM-dd");
			
					
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Add","Reclamation added " +  p.getObjet(), "OK",null);

         
            });
            NetworkManager.getInstance().addToQueueAndWait(cr);
            
        /*   }
        catch (ParseException e1) {
         e1.printStackTrace();
         }*/
    }
	
	private String modifyDateLayout(String inputDate) throws ParseException{
		Date date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy").parse(inputDate);
		return new SimpleDateFormat("MM-dd-yyyy").format(date);
	}
	
	 public String parseDate(String dateTime,String inputPattern, String outputPattern) throws ParseException {

       SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
       SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
	   
       Date date = inputFormat.parse(dateTime);

       String str = outputFormat.format(date);

       return str;
	}
	
	 public void updateReclamation(Reclamation p,int id) {
       
             
            MultipartRequest cr = new MultipartRequest();
            cr.setUrl(Statics.BASE_URL+"updateReclamationJson");
        cr.setPost(false);
System.out.println(p);
            cr.addArgument("idr", String.valueOf(p.getId()));
            cr.addArgument("objet", p.getObjet());
            cr.addArgument("descR", p.getdescR());
            cr.addArgument("status", p.getStatus());
            cr.addArgument("user_id", String.valueOf(MyApplication.u_c.getId()));
            
            cr.addResponseListener(e -> {
             

                if(cr.getResponseCode() == 200)
                    Dialog.show("Update","Reclamation update " +  p.getObjet(), "OK",null);

         
            });     
            NetworkManager.getInstance().addToQueueAndWait(cr);
           
    }
	
}
