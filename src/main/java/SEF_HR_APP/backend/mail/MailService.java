package SEF_HR_APP.backend.mail;

/**
 * Some of the code from here was copied from Stackoverflow : https://stackoverflow.com/questions/3649014/send-email-using-java
 * It is not secure, do not put own mail and password!
 */

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import SEF_HR_APP.backend.datamodels.user.User;


public class MailService {

    private static Session session;

    public static void initialize(){
        final String username = "sef.test.hrapp@gmail.com";
        final String password = "mylifeworries";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }

        });
    }


    public static boolean sendNewUserMessage(User user,String password){
        

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sef.test.hrapp@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(user.getEmail()));
            message.setSubject("New SEF_HR_APP account");
            message.setText("Dear "+user.getName()+",\nYour new SEF_HR_APP account has been created!\n\nUsername : "+user.getUsername() +"\nPassword : "+
            password + "\n\nPlease do not reply to this email!");

            Transport.send(message);

            System.out.println("Mail sent!");
            return true;

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }
}