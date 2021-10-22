<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Contacts</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/contacts.css" type="text/css">
    </head>
    <body>
       <div id="menu">
            <div class="topMenu" id="image">
                <img src="img/icon.jpg" alt="icon" id="icon">
            </div>
            <div class="topMenu" id="housePhone">
                <span id="house1">
                    Автомобильный дом "Минск"
                </span>
                <br>
                <span id="phone">
                    Телефон: +375 17 3099999
                </span>
            </div>
       </div>
       <br>
       <div>
        <div id="whiteDiv">
            <div id="contacts">
                Контакты
            </div>
            <img src="img/co.jpg" alt="contacts" id="houseImage">
            <span id="house2">
                Автомобильный дом "Минск"
            </span>
            <br><br><br><br><br>
            <span id="adress">
                г.Минск, ул.Тимирязева, 68
            </span>
            <br><br>
            <a href="https://www.google.com/maps/d/u/0/viewer?mid=1YEWmgELuwqFMNSS7r2wYg-Ep1tjpyHyV&ll=53.92198748333897%2C27.515708141555795&z=17"><span id="how">Как проехать</span></a>
            <br><br><br>
            <span id="legkService">
                Запись на легковой сервис:<br>
                <span style="padding-left: 20%;color: #1E90FF;font-size: 18px;">+375 29 3099955</span>
            </span>
            <br><br><br>
            <span id="legkService">
                Запись на грузовой сервис:<br>
                <span style="padding-left: 20%;color: #1E90FF;font-size: 18px;">+375 17 3099959</span>
            </span>
            <br><br><br><br>
            <div style="height: 50px;background-color: white;margin-left: 40%;">
                <table>
                    <tr>
                        <th><a href=""><img src="img/facebook.png" height="45px" width="40px" alt=""></a></th>
                        <th><a href=""><img src="img/in.jpg" height="47px" width="49px" alt=""></a></th>
                        <th><a href=""><img src="img/yot.jpg" height="43px" width="49px" alt=""></a></th>
                    </tr>
                </table>
            </div>
        </div>
       </div>
       <p style="margin-left: 9%;color: #A9A9A9;">
        _______________________________________________________________________________________________________________________________________________________
       </p>
       <br>
       <table style="margin-left: 7%;">
        <tr>
            <th style="width: 300px;text-align:left;">
                <div style="margin-left: 10%;">
                    <p style="color: white;">Автомобильный дом "Минск"</p>
                    <p style="color: white;" >Запчасти:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;">Сервис:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;">Автомобили:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;">Email:<span style="color: deepskyblue;">&nbsp;info@mercedes-benz.by</span></p>
                    <br><br><br><br><br><br><br><br><br>
                </div>
            </th>
            <th style="width: 300px;text-align: left;">
                <div style="margin-left: 10%;">
                    <p style="color: white;">Часы работы</p>
                    <p><span style="color: deepskyblue;" id="isOpened">Сейчас открыто</span></p><!--скрипт для времени закрыто/открыто-->
                    <br>
                    <p style="color: white;">Понедельник:&nbsp;08.00-20.00</p>
                    <p style="color: white;">Вторник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;">Среда:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;">Четверг:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;">Пятница:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;">Суббота:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;">Воскресенье:&nbsp;&nbsp;08.00-17.00</p>
                </div>
            </th>
            <th style="width: 300px;text-align: left;">
                <div style="margin-left: 10%;color: white;">
                     Как нас найти
                    <p>ул.Тимирязева, 68</p>
                    <p>220035 Minsk</p>
                    <p>Тел:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <br><br><br><br><br><br><br><br><br><br>
                </div>
            </th>
        </tr>
    </table>
    <p style="margin-left: 9%;color: #A9A9A9;">
        _______________________________________________________________________________________________________________________________________________________
    </p>
    <p style="margin-left: 9%;color: white;margin-bottom: 2%;">©Mercedes-Benz Беларусь, 2021</p>
    <script src="js/index.js" type="text/javascript"></script>
    </body>
</html>