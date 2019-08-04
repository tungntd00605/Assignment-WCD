package com.assignment.util;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailUtil {
    private final static String username = "thanhtung1993817@gmail.com";
    private final static Logger LOGGER = Logger.getLogger(MailUtil.class.getName());

    public static void sendMail(String email, String code){

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        String htmlBody = "Click here to active your account:" +
                "<a href=\"https://html-nal-180106.appspot.com/user/active?code=\"" + code + "></a>";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username , "thanhtung1993817@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email));
            msg.setSubject("ActivationCode");
            msg.setContent(htmlBody, "text/html");
            Transport.send(msg);
            LOGGER.info("EMAIL SENT");
        } catch (MessagingException | UnsupportedEncodingException e) {
            LOGGER.log(Level.SEVERE, "Error send mail.", e);
        }
    }
}
