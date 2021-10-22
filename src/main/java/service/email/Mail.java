package service.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Mail {

    private static final String propertyPath = "C:\\Projects\\Servlet\\src\\main\\resources\\mail.properties";
    private static final String internetAddress = "artembadkov73@gmail.com";
    private static final String password = "007953hh";

    private static Session getMailSession()throws IOException{
        FileInputStream fileInputStream = new FileInputStream(propertyPath);
        final Properties properties = new Properties();
        properties.load(fileInputStream);
        return Session.getDefaultInstance(properties);
    }

    private static void doTransport(Session mailSession, MimeMessage message)throws MessagingException{
        Transport transport = mailSession.getTransport();
        transport.connect(internetAddress, password);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendMessage(String receiver, String code) throws IOException, MessagingException {
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject("Код подтверждения");
        message.setText(code);
        doTransport(mailSession, message);
    }

    public static void sendOrder(String receiver, String nameOfCar, String price) throws IOException, MessagingException {
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject("Ваш заказ:");
        message.setText("Название автомобиля: " + nameOfCar + "\nЦена: " + price);
        doTransport(mailSession, message);
    }

    public static void sendTestDriveOrder(String receiver, String nameOfCar, String date, String price)throws IOException, MessagingException{
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject("Запись на тест-драйв:");
        message.setText("Вы записались на тест-драйв автомобиля:\n" + nameOfCar + "\nЦена услуги: " + price +
                "\nДата: " + date);
        doTransport(mailSession, message);
    }

    public static void sendServiceOrder(String receiver, String nameOfCar, String date,String price)throws IOException, MessagingException{
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        message.setSubject("Запись на сервис:");
        message.setText("Вы записались на сервис автомобиля:\n" + nameOfCar + "\nЦена услуги: " + price +
                "\nДата: " + date);
        doTransport(mailSession, message);
    }
}