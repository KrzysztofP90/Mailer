package com.krzysztof.mailer;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailerService {

    private String host;
    private int port;
    private String username;
    private String password;
    private int smtpPort;


    public MailerService(String username, String password) {

        this.host = "smtp.gmail.com";
        this.smtpPort = 465;
        this.port = 587;
        this.username = username;
        this.password = password;
    }

    private Properties getProperties() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", this.host);
        prop.put("mail.smtp.port", this.smtpPort);
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", this.port);
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return prop;
    }


    public boolean sendEmail(String fromMail, String toMail, String mailMessage) {

        Properties prop = this.getProperties();
        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromMail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toMail));
            message.setSubject("Mail Subject");

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(mailMessage, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);
            message.setContent(multipart);

            Transport.send(message);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
