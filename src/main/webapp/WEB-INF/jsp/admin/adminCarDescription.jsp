<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Car Description</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>Характеристики машины</h1>
        <p style="color: red;">${requestScope.error}</p>
        <div>
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="CHANGE_INFO_COMMAND">
                <input type="hidden" name="img" value=${requestScope.image}>
                <div style="float:left;margin-right:3%;">
                    <h3>Марка</h3>
                    <h3><c:out value="${requestScope.mark}" /></h3>
                    <input type="hidden" name="mark" value="${requestScope.mark}">
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Цена</h3>
                    <input type="text" name="price" value=${requestScope.price}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Мощность</h3>
                    <input type="text" name="power" value=${requestScope.power}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Разгон до 100 км/ч</h3>
                    <input type="text" name="acceleration" value=${requestScope.acceleration}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Расход</h3>
                    <input type="text" name="consumption" value=${requestScope.consumption}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Объем двигателя</h3>
                    <input type="text" name="engineVolume" value=${requestScope.engineVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Объем бака</h3>
                    <input type="text" name="tankVolume" value=${requestScope.tankVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Объем багажника</h3>
                    <input type="text" name="trunkVolume" value=${requestScope.trunkVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Макс. скорость</h3>
                    <input type="text" name="maxSpeed" value=${requestScope.maxSpeed}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Картинка</h3>
                    <input type="text" name="image" value=${requestScope.image}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3>Тип</h3>
                    <input type="text" name="type" value=${requestScope.type}>
                </div>
                <button style="position:absolute;border:none;margin-top:15%;margin-left:-50%;width:300px;height:50px;background:orange;color:white;">Изменить</button>
            </form>
        </div>
    </body>
</html>