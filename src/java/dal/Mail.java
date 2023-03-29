/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import models.AccountDAO;
import models.ContactDAO;
import models.CoursesDAO;

/**
 *
 * @author DELL
 */
public class Mail {

    private String text;

    public static void Mail(String email, String subject, String text) {
        final String username = "courseshay1@gmail.com";
        final String password = "ozhyrkwfcolkbxng";

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
            message.setFrom(new InternetAddress("courseshay1@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void SenMailResetPass(String email, String newpassword, Account acc) {
        String text = "Dear " + new ContactDAO().getAccContactById(acc.getAccountID()).getFullName()
                + "\n\n Courses Hay send you new password: " + newpassword;
        Mail(email, "RESET PASSWORD !", text);
    }

    public static void SenMailCourseCode(int accId, String rtext) {
        Account acc = new AccountDAO().getAccountById(accId);
        String text = "Dear " + new ContactDAO().getAccContactById(accId).getFullName() + "\n\n" + rtext;
        Mail(acc.getEmail(), "CODE OF COURSE !", text);
    }

    public static void SendMailBan(int accId, String rtext) {
        Account acc = new AccountDAO().getAccountById(accId);
        String text = "Dear " + new ContactDAO().getAccContactById(accId).getFullName() + "\n\n" + rtext;
        Mail(acc.getEmail(), "Ban Account", text);
    }

    public static void SendMailUnBan(int accId, String rtext) {
        Account acc = new AccountDAO().getAccountById(accId);
        String text = "Dear " + new ContactDAO().getAccContactById(accId).getFullName() + "\n\n" + rtext;
        Mail(acc.getEmail(), "Unban Account", text);
    }

}
