package com.fundingForAll.www.utils;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class EmailUtil {
    private static String myEmailId;
    private static String myEmailPassword;

    public void sendEmail(String receiverEmail) throws IOException {
        //init email info
        Properties myEmailProperties = new Properties();
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/application.properties");
        myEmailProperties.load(resourceAsStream);

        myEmailId = myEmailProperties.getProperty("myEmailId");
        myEmailPassword = myEmailProperties.getProperty("myEmailPassword");

        String randomCode = UUID.randomUUID().toString();

        //set properties
        Properties smtpProperties = new Properties();
        smtpProperties.put("mail.transport.protocol", "smtp");
        smtpProperties.put("mail.smtp.host","smtp.naver.com");
        smtpProperties.put("mail.smtp.port","465");
        smtpProperties.put("mail.smtp.ssl.enable", "true");
        smtpProperties.put("mail.smtp.ssl.trust", "smtp.naver.com");
        smtpProperties.put("mail.smtp.auth", "true");

        //generate session
        Session session = Session.getDefaultInstance(smtpProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailId, myEmailPassword);
            }
        });

        //generate message
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myEmailId));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
            message.setSubject("verification your code");
            message.setText("your code is [" + randomCode + "]");

            Transport.send(message);
        } catch (AddressException e) {
            System.out.println(e.getMessage());
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("finish send mail");
    }
}
