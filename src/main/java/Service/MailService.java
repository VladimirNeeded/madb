package Service;

import utils.RandomHelper;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    public String sendMail(String userMail){
        final String username = "voha.tretyak96@gmail.com";
        final String password = "vgp30091996t";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("voha.tretyak96@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userMail)
            );
            message.setSubject("CODE!");
            String randomCode = RandomHelper.getRandomCode();
            message.setText("Your code is " + randomCode);
            Transport.send(message);
            return randomCode;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "Error";
    }
}