package fr.norsys.einvoice.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api")
public class EmailController {



        @PostMapping("/sendEmail")
        public String sendEmail(@RequestParam("to") String to,
                                @RequestParam("subject") String subject,
                                @RequestParam("message") String message,
                                @RequestParam(value = "attachment", required = false) MultipartFile attachment) {

            final String senderEmail = "it.2000.el@gmail.com";
            final String senderPassword = "ncynuqyksgvzcked";
            final String smtpHost = "smtp.gmail.com";
            final int smtpPort = 587;

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(senderEmail, senderPassword);
                }
            });

            try {
                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(senderEmail));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                mimeMessage.setSubject(subject);

                MimeBodyPart messageBodyPart = new MimeBodyPart();
                messageBodyPart.setContent(message, "text/plain");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(messageBodyPart);

                if (attachment != null && !attachment.isEmpty()) {
                    MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                    attachmentBodyPart.setContent(attachment.getBytes(), attachment.getContentType());
                    attachmentBodyPart.setFileName(attachment.getOriginalFilename());
                    multipart.addBodyPart(attachmentBodyPart);
                }

                mimeMessage.setContent(multipart);

                Transport.send(mimeMessage);

                return "Email sent successfully!";
            } catch (MessagingException | IOException e) {
                e.printStackTrace();
                return "Error sending email: " + e.getMessage();
            }
        }
    }
