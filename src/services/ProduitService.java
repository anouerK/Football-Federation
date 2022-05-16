/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.produit;
import entities.categories;
import entities.marques;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.MyDB;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Lord
 */
public class ProduitService implements IService<produit> {
    
    
    Connection cnx;
    Image image;
    ImageView img ;

    public ProduitService() {
        cnx = MyDB.getInstance().getConnection();
    }

    @Override
    public void ajouter(produit t) {

        DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
              LocalDateTime now =  LocalDateTime.now();
        
        try {
   String req = "insert into produit(categorie_id,nom_p,taille,couleur,prix,descr,qte,img,marquep_id,taille2,date_ajout)"
   + "values( '" + t.getCat().getId()+ "' , '" + t.getNom()+ "' , '" + t.getTaille()+ "' , '" + t.getCouleur()+ "' , '" + t.getPrix()+ "' , '" + t.getDesc()+ "' , '" + t.getQte()+ "' , '" + t.getImg()+ "' ,'" + t.getMar().getId()+ "','" + t.getTaille2()+ "' , '" + date.format(now) + "' )";
   
   
   
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        }

    @Override
    public void modifier(produit t) {
 try {
     
          DateTimeFormatter date =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
          LocalDate now =  LocalDate.parse(t.getDate());
      
            String req = "update produit set categorie_id= ? , nom_p=? ,taille=? , couleur=? , prix=? , descr=? , qte=? , img=? , marquep_id=? , taille2=? , date_ajout=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            
            ps.setInt(1, t.getCat().getId());
            ps.setString(2,t.getNom());
            ps.setString(3,t.getTaille());
            ps.setString(4,t.getCouleur());  
            ps.setFloat(5, t.getPrix());
            ps.setString(6,t.getDesc());
            ps.setInt(7, t.getQte());
            ps.setString(8,t.getImg());
            ps.setInt(9, t.getMar().getId());
            ps.setString(10,t.getTaille2());
            ps.setString(11,date.format(now));
     
           
            ps.setInt(12, t.getId());
            
            ps.executeUpdate();
            System.out.println("Produit modifiee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
       
    }
    
    }

    @Override
    public void supprimer(int id) {
 try {
            String req = "delete from produit  where id=" + " '"+ id + "' ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.executeUpdate();
         
          
            System.out.println("Produit deleted");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    
    }

//    @Override
    public ObservableList<produit> recuperer() {
ObservableList<produit> personnes = FXCollections.observableArrayList();

  
        try {
            
            String req = "select c.* ,p.id as idp,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
           
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("idp"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
                
             
                 
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
        }   
    
    
    
    
    public ObservableList<produit> filter(int pr , String color,String categorie,String nom) {
ObservableList<produit> personnes = FXCollections.observableArrayList();

        System.out.println("nommmmmmmmmmmmmmmm: "+nom );
        System.out.println("prrrrrrrrrrrrrrrrr: "+pr );
        System.out.println("catttttttttttttttt: "+categorie );
        System.out.println("collllllllllllllll: "+color );
       
        
        try {
            if(categorie!="")
            {
            if(pr==1 && color!="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "' and p.nom_p like '"+"%"+  nom  + "%"+"' order by prix ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
            
            
            if(pr==1 && color!="" && nom=="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "'  order by prix ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
            
            
             if(pr==1 && color=="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where  c.type_c like '"+  categorie  + "' and p.nom_p like '"+"%"+  nom  + "%"+"' order by prix ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }

         
         
   
            
           
            if(pr==2 && color=="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where c.type_c like '"+  categorie  + "' and p.nom_p like '"+"%"+  nom  + "%"+ "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
              
               if(pr==1 && color=="" && nom=="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where c.type_c like  '"+  categorie  + "' order by prix ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
             
               
               
              if(pr==2 && color=="" && nom=="")
            {
                System.out.println("kol vide");
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where c.type_c like '"+  categorie  + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
               
               if(pr==0 && color!="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "' and p.nom_p like '"+"%"+  nom  + "%" + "' order by prix desc ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
               
               if(pr==0 && color!="" && nom=="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "'  order by prix desc ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
               
                  if(pr==0 && color=="" && nom=="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where  c.type_c like '"+  categorie  + "' order by prix desc ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
               
               
               if(pr==2 && color!="" && nom=="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
               
               
                if(pr==2 && color!="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.couleur like '"+"%"+ color +"%" + "' and c.type_c like '"+  categorie  + "' and p.nom_p like '"+"%"+  nom  + "%" + "' ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }
               
        
                
            }//categorie
            if(categorie=="")
            {
            if(pr==2 && color=="" && nom!="")
            {
            String req = "select c.*,p.*,m.* from produit p inner join categorie c on p.categorie_id=c.id inner join marques m on p.marquep_id=m.id where p.nom_p like '"+"%"+  nom  + "%" + "'  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            
            while (rs.next()) {
              
                produit p = new produit();
                
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_p"));
                p.setCouleur(rs.getString("couleur"));
                p.setTaille(rs.getString("taille"));
                p.setTaille2(rs.getString("taille2"));
                p.setDesc(rs.getString("descr"));
                p.setImg(rs.getString("img"));
                p.setPrix(rs.getFloat("prix"));
                p.setQte(rs.getInt("qte"));
                
               
        
            categories cat = new categories(rs.getInt("categorie_id"),rs.getString("type_c")); 
            marques mar = new marques(rs.getInt("marquep_id"),rs.getString("nom_m"));
            
                p.setCat(cat);
                p.setMar(mar);
                
                personnes.add(p);
            }
            }}

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return personnes;
        }   
    
    
    
    
}
