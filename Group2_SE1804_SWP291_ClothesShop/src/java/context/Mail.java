/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import com.sun.jdi.connect.Transport;
import java.net.PasswordAuthentication;
import java.util.Properties;
import org.apache.catalina.Session;

/**
 *
 * @author Putaa
 */
public class Mail {
    static private Session prepare() {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", MailConfig.HOST_NAME);
        props.put("mail.smtp.socketFactory.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", MailConfig.SSL_PORT);
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 
        // get Session
        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MailConfig.APP_EMAIL, MailConfig.APP_PASSWORD);
            }
        });
    }
    
    static public void sendVerifyEmail(String verificationLink, String email) {
        Session session = Mail.prepare();
        
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Verify Your Email");
            message.setText("Please click the following link to verify your email: " + verificationLink);
 
            // send message
            Transport.send(message);
 
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    static public void sendChangePasswordEmail(String newPassword, String email) {
        Session session = Mail.prepare();
        
        // compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Orgasm Website - Change your password");
            message.setText("Your new password is: " + newPassword + ". Please change login and change your password again.");
 
            // send message
            Transport.send(message);
 
            System.out.println("Message sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        Mail.sendVerifyEmail("asdf", "asdjfasd@baksdf.casoduf");
    }
}
