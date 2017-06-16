package com.hnyp.ahp.core.services.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Required;

import com.hnyp.ahp.core.services.EmailService;
import com.hnyp.ahp.core.services.data.EmailMessage;

public class DefaultEmailService implements EmailService {

    private Properties properties;
    private String username;
    private String password;
    
    @Override
    public void sendEmail(EmailMessage emailMessage) {
        Session session = getSession();
        try {
            Message message = createMessage(emailMessage, session);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Message createMessage(EmailMessage emailMessage, Session session)
            throws MessagingException, AddressException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailMessage.getSender()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailMessage.getRecipient()));
        message.setSubject(emailMessage.getSubject());
        message.setText(emailMessage.getBody());
        return message;
    }

    private Session getSession() {
        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                      return new PasswordAuthentication(username, password);
                  }
                });
        return session;
    }

    @Required
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Required
    public void setUsername(String username) {
        this.username = username;
    }

    @Required
    public void setPassword(String password) {
        this.password = password;
    } 

}
