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

//мб вынести общие куски в отдельный прайват метод
public class Mail {
    public static void sendMessage(String receiver, String code) throws IOException, MessagingException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\mail.properties");
        final Properties properties = new Properties();
        properties.load(fileInputStream);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("artembadkov73@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));//адрес получателя
        message.setSubject("Код подтверждения");
        message.setText(code);
        Transport transport = mailSession.getTransport();
        transport.connect("artembadkov73@gmail.com", "007953hh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendOrder(String receiver, String nameOfCar, String price) throws IOException, MessagingException {
        FileInputStream fileInputStream = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\mail.properties");
        final Properties properties = new Properties();
        properties.load(fileInputStream);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("artembadkov73@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));//адрес получателя
        message.setSubject("Ваш заказ:");
        message.setText("Название автомобиля: " + nameOfCar + "\nЦена: " + price);
        Transport transport = mailSession.getTransport();
        transport.connect("artembadkov73@gmail.com", "007953hh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendTestDriveOrder(String receiver, String nameOfCar, String price)throws IOException, MessagingException{
        FileInputStream fileInputStream = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\mail.properties");
        final Properties properties = new Properties();
        properties.load(fileInputStream);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("artembadkov73@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));//адрес получателя
        message.setSubject("Запись на тест-драйв:");
        message.setText("Вы записались на тест-драйв автомобиля: " + nameOfCar + "\nЦена услуги: " + price);
        Transport transport = mailSession.getTransport();
        transport.connect("artembadkov73@gmail.com", "007953hh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void sendServiceOrder(String receiver, String nameOfCar, String price)throws IOException, MessagingException{
        FileInputStream fileInputStream = new FileInputStream("C:\\Projects\\Servlet\\src\\main\\resources\\mail.properties");
        final Properties properties = new Properties();
        properties.load(fileInputStream);
        Session mailSession = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("artembadkov73@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));//адрес получателя
        message.setSubject("Запись на сервис:");
        message.setText("Вы записались на сервис автомобиля: " + nameOfCar + "\nЦена услуги: " + price);
        Transport transport = mailSession.getTransport();
        transport.connect("artembadkov73@gmail.com", "007953hh");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}