<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Order</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/carInfo.css" type="text/css">
    </head>
    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <div style="margin-left: 30%;">
                <img src="img/icon.jpg" alt="icon" id="icon">
                <ul>
                    <li>
                        <form action="FrontController" class="ft">
                            <button class="types">
                                <a style="text-decoration:none;color:white;" href="cars.jsp">Автомобили</a>
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="FrontController" method="get" class="ft">
                            <input type="hidden" name="command" value="DEFINITE_TEST_DRIVE_COMMAND">
                            <input type="hidden" name="markToList" value=${requestScope.img}>
                            <button class="types">
                                Тест-Драйв
                            </button>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
        <br>
        <div>
            <div style="margin-left: 40%;position: absolute;margin-top: 5%;">
                <span style="color: black;font-size: 40px;">${requestScope.nameOfMark}</span>
            </div>
            <div>
                <img src="${requestScope.img}" alt="car" class="carImage">
                <span style="color: grey;position: absolute;margin-top: 12%;margin-left: 10%;font-size: 20px;">Цена</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 10%;">${requestScope.price}</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 12%;margin-left: 25%;">Мощность</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 25%;">${requestScope.power} л.с.</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 12%;margin-left: 40%;">Разгон до 100 км/ч</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 40%;">${requestScope.acceleration} сек.</span>

                <span style="color: grey;position: absolute;margin-top: 17%;margin-left: 10%;font-size: 20px;">Расход на 100 км</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 10%;">${requestScope.consumption} л.</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 17%;margin-left: 25%;">Объем двигателя</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 25%;">${requestScope.engine} л.</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 17%;margin-left: 40%;">Объем бака</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 40%;">${requestScope.tank} л.</span>

                <span style="color: grey;position: absolute;margin-top: 22%;margin-left: 10%;font-size: 20px;">Объем багажника</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 10%;">${requestScope.trunk} л.</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 22%;margin-left: 25%;">Макс. скорость</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 25%;">${requestScope.speed} км/ч</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 22%;margin-left: 40%;">Тип</span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 40%;">${requestScope.type}</span>

            </div>
            <div>
                <div id="orderButton">
                    <form action="FrontController" method="get">
                        <input type="hidden" name="command" value="FORM_ORDER_COMMAND">
                        <input type="hidden" name="img" value=${requestScope.img}>
                        <button style="height: 50px;width: 200px;background-color: blue;color: white;border: none;border-radius: 2%;">
                            Оформить заказ
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>