package service.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
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

    public static void sendMessage(String receiver, String code, HttpServletRequest req) throws IOException, MessagingException {
        final String subject;
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        if (req.getSession().getAttribute("locale").equals("ru"))subject = "Код подтверждения";
        else if (req.getSession().getAttribute("locale").equals("ch"))subject = "驗證碼";
        else subject = "Confirmation code";
        message.setSubject(subject);
        message.setText(code);
        doTransport(mailSession, message);
    }

    public static void sendOrder(String receiver, String nameOfCar, String price, HttpServletRequest req) throws IOException, MessagingException {
        final String order;
        final String nameOfMark;
        final String money;
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        if (req.getSession().getAttribute("locale").equals("ru")){
            order = "Ваш заказ:";
            nameOfMark = "Название автомобиля: ";
            money = "\nЦена: ";
        }
        else if (req.getSession().getAttribute("locale").equals("ch")){
            order = "您的訂單:";
            nameOfMark = "車輛名稱: ";
            money = "\n價錢: ";
        }
        else {
            order = "Your order:";
            nameOfMark = "Vehicle name: ";
            money = "\nPrice: ";
        }
        message.setSubject(order);
        message.setText(nameOfMark + nameOfCar + money + price);
        doTransport(mailSession, message);
    }

    public static void sendTestDriveOrder(String receiver, String nameOfCar, String date, String price, HttpServletRequest req)throws IOException, MessagingException{
        final String testDrive;
        final String testAuto;
        final String money;
        final String when;
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        if (req.getSession().getAttribute("locale").equals("ru")){
            testDrive = "Запись на тест-драйв:";
            testAuto = "Вы записались на тест-драйв автомобиля:\n";
            money = "\nЦена услуги: ";
            when = "\nДата: ";
        }
        else if (req.getSession().getAttribute("locale").equals("ch")){
            testDrive = "註冊試駕:";
            testAuto = "您註冊了試駕:\n";
            money = "\n服務價格: ";
            when = "\n日期: ";
        }
        else {
            testDrive = "Registration for a test drive:";
            testAuto = "You signed up for a test drive:\n";
            money = "\nService price: ";
            when = "\nDate: ";
        }
        message.setSubject(testDrive);
        message.setText(testAuto + nameOfCar + money + price + when + date);
        doTransport(mailSession, message);
    }

    public static void sendServiceOrder(String receiver, String nameOfCar, String date,String price, HttpServletRequest req)throws IOException, MessagingException{
        final String service;
        final String serviceAuto;
        final String money;
        final String when;
        Session mailSession = getMailSession();
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(internetAddress));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
        if (req.getSession().getAttribute("locale").equals("ru")){
            service = "Запись на сервис:";
            serviceAuto = "Вы записались на сервис автомобиля:\n";
            money = "\nЦена услуги: ";
            when = "\nДата: ";
        }
        else if (req.getSession().getAttribute("locale").equals("ch")){
            service = "服務註冊:";
            serviceAuto = "您註冊了汽車服務:\n";
            money = "\n服務價格: ";
            when = "\n日期: ";
        }
        else {
            service = "Service registration:";
            serviceAuto = "You signed up for a car service:\n";
            money = "\nService price: ";
            when = "\nDate: ";
        }
        message.setSubject(service);
        message.setText(serviceAuto + nameOfCar + money + price + when + date);
        doTransport(mailSession, message);
    }
}