/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxapplication1;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

        
/**
 *
 * @author Lord
 */
public class AhmedMail {
    public static void sendMail(String msg,String recepient) throws MessagingException
    {
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccount = "yaga77328@gmail.com";
        String password="BabaYaga12345";
        
        Session session = Session.getInstance(properties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
               return new  PasswordAuthentication(myAccount,password);
            }
        });
        
        
        
            MimeMessage message = prepareMessage(session,myAccount,password,recepient,msg);
            Transport.send(message);
       
    }

    private static MimeMessage prepareMessage(Session session, String myAccount, String password, String recepient, String msg){
     
      
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Turbo devs");
            message.setText(msg);
            return(message);
            
            
        } catch (Exception ex) {
            Logger.getLogger(AhmedMail.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
        

    }
    
}
