/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Legon
 */
public class JavaMailUtil {

    public static void sendMail(String recepient,String mess) throws Exception {
        System.out.println("Preparing to send mail ");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        
        String myAccountEmail = "dungndhe163971@fpt.edu.vn";
        String password = "0989903203Aa";
        Session session = Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
            
                return new PasswordAuthentication(myAccountEmail,password);
              //  return super.getPasswordAuthentication(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
            
            
        });
         System.out.println("Preparing to send mail 1");
        Message message = prepareMessage(session,myAccountEmail, recepient,mess);
         System.out.println("Preparing to send mail 2");
        Transport.send(message);
        System.out.println("Message sned successfully");
        
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail,String recepient,String mess ) {
        try {
            //  throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.CC, new InternetAddress(recepient));
            message.setSubject("S-Course Training System:");
            message.setText(mess);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
