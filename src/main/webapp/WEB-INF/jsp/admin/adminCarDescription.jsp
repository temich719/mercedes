<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Car Description</title>
        <meta charset="UTF-8">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.charsOfCar" var="charsOfCar" />
        <fmt:message bundle="${loc}" key="local.mark" var="mark" />
        <fmt:message bundle="${loc}" key="local.price" var="price" />
        <fmt:message bundle="${loc}" key="local.power" var="power" />
        <fmt:message bundle="${loc}" key="local.accel" var="accel" />
        <fmt:message bundle="${loc}" key="local.consumption" var="consumption" />
        <fmt:message bundle="${loc}" key="local.engine" var="engine" />
        <fmt:message bundle="${loc}" key="local.tank" var="tank" />
        <fmt:message bundle="${loc}" key="local.trunk" var="trunk" />
        <fmt:message bundle="${loc}" key="local.speed" var="speed" />
        <fmt:message bundle="${loc}" key="local.imagePath" var="imagePath" />
        <fmt:message bundle="${loc}" key="local.type" var="type" />
    </head>
    <body>
        <h1><c:out value="${charsOfCar}" /></h1>
        <p style="color: red;">${requestScope.error}</p>
        <div>
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="CHANGE_INFO_COMMAND">
                <input type="hidden" name="img" value=${requestScope.image}>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${mark}" /></h3>
                    <h3><c:out value="${requestScope.mark}" /></h3>
                    <input type="hidden" name="mark" value="${requestScope.mark}">
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${price}" /></h3>
                    <input type="text" name="price" value=${requestScope.price}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${power}" /></h3>
                    <input type="text" name="power" value=${requestScope.power}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${accel}" /></h3>
                    <input type="text" name="acceleration" value=${requestScope.acceleration}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${consumption}" /></h3>
                    <input type="text" name="consumption" value=${requestScope.consumption}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${engine}" /></h3>
                    <input type="text" name="engineVolume" value=${requestScope.engineVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${tank}" /></h3>
                    <input type="text" name="tankVolume" value=${requestScope.tankVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${trunk}" /></h3>
                    <input type="text" name="trunkVolume" value=${requestScope.trunkVolume}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${speed}" /></h3>
                    <input type="text" name="maxSpeed" value=${requestScope.maxSpeed}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${imagePath}" /></h3>
                    <input type="text" name="image" value=${requestScope.image}>
                </div>
                <div style="float:left;margin-right:3%;">
                    <h3><c:out value="${type}" /></h3>
                    <input type="text" name="type" value=${requestScope.type}>
                </div>
                <button style="position:absolute;border:none;margin-top:15%;margin-left:-50%;width:300px;height:50px;background:orange;color:white;">Изменить</button>
            </form>
        </div>
    </body>
</html>