/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.gui;

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;

import com.codename1.util.CallbackAdapter;

/*

import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.util.CallbackAdapter;

/**
 * Simple API for sending an email via sendgrid
 *
 * @author Shai Almog
 */
public class SendGrid {
    private String token;
    private SendGrid(String token) {
        this.token = token;
    }
    
    /**
     * You need the API token from send grid to use this class, it's available 
     * by signing up to their service
     * @param token the API token
     * @return the class instance
     */
    public static SendGrid create(String token) {
        return new SendGrid(token);
    }

    /**
     * Sends an email synchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendSync(String to, String from, String subject, String body) {
        Response<String> s = Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsString();        
    }

    /**
     * Sends an email synchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email in html format
     */
    public void sendHtmlSync(String to, String from, String subject, String body) {
        Response<String> s = Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/html\", \"value\": \"" + 
                body + "\"}]}").getAsString();        
    }

    /**
     * Sends an email asynchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email
     */
    public void sendASync(String to, String from, String subject, String body) {
        Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/plain\", \"value\": \"" + 
                body + "\"}]}").getAsStringAsync(new CallbackAdapter<>());
    }

     /**
     * Sends an email asynchronously 
     * @param to to email
     * @param from from email
     * @param subject email subject
     * @param body the body of the email in html format
     */
    public void sendHtmlASync(String to, String from, String subject, String body) {
        Rest.post("https://api.sendgrid.com/v3/mail/send").
            jsonContent().
            header("Authorization", "Bearer "+ token).
            body("{\"personalizations\": [{\"to\": [{\"email\": \"" + to + 
                "\"}]}],\"from\": {\"email\": \"" + from + 
                "\"},\"subject\": \"" + subject + 
                "\",\"content\": [{\"type\": \"text/html\", \"value\": \"" + 
                body + "\"}]}").getAsStringAsync(new CallbackAdapter<>());
    }
   
}