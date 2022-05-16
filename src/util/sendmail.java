/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import static javax.mail.Message.RecipientType.TO;
import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ksaay
 */
public class sendmail {
   
     public static void sendEmail(String to ,String titre,String descr,String username_s){
  
        String from = "aaa@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "ksaay2000@gmail.com";
        final String password = "DEG123456789";

        //setup mail server

        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("New Article ["+titre+"]");
            m.setText("Good Morning Sir "+username_s+"\n New Article [ "+titre+" ] Has been added check it when you are free\n"+descr);

            //send mail

            Transport.send(m);
           // sentBoolValue.setVisible(true);
            System.out.println("Message sent to ["+username_s+ "] !");

        }   catch (MessagingException e){
            e.printStackTrace();
        }

    }
}
