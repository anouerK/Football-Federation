/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.entities;

/**
 *
 * @author ksaay
 */
public class User {
    int id;
    String username;
      String email;
    String mdp;
    String role;
    String img;
    Badge badge;
    
    int nbp;
public User()
{
    
}

    public User(String username, String email, String mdp) {
        this.username = username;
        this.email = email;
        this.mdp = mdp;
    }

    public User(String username, String mdp) {
        this.username = username;
        this.mdp = mdp;
    }
    public User(String username, String email, String mdp, String role, String img, Badge badge, int nbp) {
        this.username = username;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.img = img;
        this.badge = badge;
        this.nbp = nbp;
    }

    public User(int id, String username, String email, String mdp, String role, String img, Badge badge, int nbp) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.img = img;
        this.badge = badge;
        this.nbp = nbp;
    }
    public User(User u )
    {
        this.id = u.id;
        this.username = u.username;
        this.email = u.email;
        this.mdp = u.mdp;
        this.role = u.role;
        this.img = u.img;
        this.badge = u.badge;
        this.nbp = u.nbp;
        
    }
    

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
  

   

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public void setId(int id) {
        this.id = id;
    }

    

    public void setNbp(int nbp) {
        this.nbp = nbp;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMdp() {
        return mdp;
    }

    public String getRole() {
        return role;
    }

    public String getImg() {
        return img;
    }

    public Badge getBadge() {
        return badge;
    }

    

    public int getNbp() {
        return nbp;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", email=" + email + ", mdp=" + mdp + ", role=" + role + ", img=" + img + ", badge=" + badge + ", nbp=" + nbp + '}';
    }

   
    
}
