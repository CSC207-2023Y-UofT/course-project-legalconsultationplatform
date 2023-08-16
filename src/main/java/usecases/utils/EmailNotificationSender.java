package usecases.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * This class provides emailing service for other usecase.
 */
public class EmailNotificationSender{

    private static final String address ="2066465318@qq.com";
    private static final String password ="qunbxhfcdnlzfccc";
    private static final int port =465;

    /**
     *
     * @param email The email address of the user, should be in valid format.
     * @param title The tile of the email.
     * @param content The specific content of the email.
     */
    public static void sendEmail(String email, String title, String content) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.qq.com");
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.debug", "true");

        Session session = Session.getInstance(properties);

        Message message = new MimeMessage(session);

        message.setFrom(new InternetAddress(address));

        message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(email)});

        message.setSubject(title);



        message.setText(content);

        Transport transport = session.getTransport();

        transport.connect(address, password);

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}