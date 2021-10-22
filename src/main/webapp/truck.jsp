<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <c:set var="account" scope="request" value="${sessionScope.nameAccount}" />

      <c:choose>
         <c:when test="${empty account}">
             <c:set var="backLink" scope="request" value="index.jsp" />
         </c:when>
         <c:otherwise>
             <c:set var="backLink" scope="request" value="registratedIndex.jsp" />
         </c:otherwise>
      </c:choose>

<html>
    <head>
        <title>Trucks</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/truck.css" type="text/css">
    </head>
    <body>
        <div id="mainDiv">
            <div id="divImage">
                 <img src="img/icon.jpg" alt="icon" id="icon">
            </div>
            <div id="divBackButton">
                <span id="backButton">
                    <a href="${backLink}" style="color: white;">Вернуться на главную</a>
                </span>
            </div>
        </div>
        <div>
            <div>
                <img src="img/tr.jpg" alt="minibus" id="truckImage">
                <div id="overImage">
                    <span style="font-size: 20px;">Наши грузовые автомобили</span>
                    <br>
                    <span>______</span>
                    <br>
                    <br>
                    <span style="font-size: 38px;">Actros.</span>
                    <br><br>
                    <span style="font-size: 20px;">Больше, чем просто экономичный – Actros</span>
                </div>
            </div>
        </div>
        <div>
            <div id="tech">
                <span style="color: white;padding-top: 0;font-size: 38px;"><b>______</b></span>
                <br><br><br>
                <span style="color: white;font-size: 38px;">Краткий обзор технических характеристик.</span>
                <br><br>
                <div class="divImg">
                    <img src="img/engine.jpg" alt="engine" class="images"><br>
                    <span style="color: white;font-size: 23px;">Actros – технические характеристики двигателя.</span><br><br>
                    <span style="color: white;font-size: 15px;">Экономичные, надежные двигатели Euro VI – четыре варианта рабочего</span><br>
                    <span style="color: white;font-size: 15px;">объема. Инновационные технологии, мощность до 460 кВт (625 л. с.),</span><br>
                    <span style="color: white;font-size: 15px;">максимальный крутящий момент 3000 Нм.</span>
                </div>
                <div class="divImg">
                    <img src="img/tank.jpg" alt="tank" class="images"><br>
                    <span style="color: white;font-size: 23px;">Варианты топливных баков.</span><br><br>
                    <span style="color: white;font-size: 15px;">Широкие возможности сочетания – при одинаковом профиле с левой и</span><br>
                    <span style="color: white;font-size: 15px;">правой стороны могут устанавливаться топливные баки различного</span><br>
                    <span style="color: white;font-size: 15px;">объема.</span>
                </div>
                <br>
            </div>
        </div>
        <div>
            <div id="orderButton">
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="TRUCK_ORDER_COMMAND">
                    <button style="height: 50px;width: 200px;background-color: blue;color: white;border: none;border-radius: 2%;">
                        Оформить заказ
                    </button>
                </form>
            </div>
            <div id="serviceButton">
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="TRUCK_SERVICE_ORDER_COMMAND">
                    <input type="hidden" name="truckMark" value="Actros">
                    <button style="height: 50px;width: 200px;background: white;color: black;border: none;border-radius: 2%;">
                        Запись на сервис
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>