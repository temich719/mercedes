<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Add Product</title>
        <meta charset="UTF-8">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.price" var="price" />
        <fmt:message bundle="${loc}" key="local.mark" var="mark" />
        <fmt:message bundle="${loc}" key="local.power" var="power" />
        <fmt:message bundle="${loc}" key="local.accel" var="accel" />
        <fmt:message bundle="${loc}" key="local.consumption" var="consumption" />
        <fmt:message bundle="${loc}" key="local.engine" var="engine" />
        <fmt:message bundle="${loc}" key="local.tank" var="tank" />
        <fmt:message bundle="${loc}" key="local.trunk" var="trunk" />
        <fmt:message bundle="${loc}" key="local.speed" var="speed" />
        <fmt:message bundle="${loc}" key="local.type" var="type" />
        <fmt:message bundle="${loc}" key="local.imagePath" var="imagePath" />
        <fmt:message bundle="${loc}" key="local.addCar" var="addCar" />
        <fmt:message bundle="${loc}" key="local.addMinibus" var="addMinibus" />
        <fmt:message bundle="${loc}" key="local.load" var="load" />
        <fmt:message bundle="${loc}" key="local.weight" var="weight" />
        <fmt:message bundle="${loc}" key="local.carAddition" var="carAddition" />
        <fmt:message bundle="${loc}" key="local.minibusAddition" var="minibusAddition" />
        <fmt:message bundle="${loc}" key="local.productAddition" var="productAddition" />
    </head>
    <body>
        <h1 style="margin-left:37%;"><c:out value="${productAddition}" /></h1>
        <div>
            <h3><c:out value="${carAddition}" /></h3>
            <form action="FrontController" method = "get">
                <input type="hidden" name="command" value="ADD_CAR_COMMAND">
                <input type="text" name="mark" placeholder="${mark}">
                <input type="text" name="price" placeholder="${price}">
                <input type="text" name="power" placeholder="${power}">
                <input type="text" name="acceleration" placeholder="${accel}">
                <input type="text" name="consumption" placeholder="${consumption}">
                <input type="text" name="engineVolume" placeholder="${engine}">
                <input type="text" name="tankVolume" placeholder="${tank}">
                <input type="text" name="trunkVolume" placeholder="${trunk}">
                <input type="text" name="maxSpeed" placeholder="${speed}">
                <input type="text" name="image" placeholder="${imagePath}">
                <input type="text" name="type" placeholder="${type}">
                <button>
                    <c:out value="${addCar}" />
                </button>
            </form>
        </div>
        <div>
            <h3><c:out value="${minibusAddition}" /></h3>
            <form action="FrontController" method = "get">
                <input type="hidden" name="command" value="ADD_MINIBUS_COMMAND">
                <input type="text" name="mark" placeholder="${mark}">
                <input type="text" name="price" placeholder="${price}">
                <input type="text" name="image" placeholder="${imagePath}">
                <input type="text" name="load" placeholder="${load}">
                <input type="text" name="weight" placeholder="${weight}">
                <button>
                    <c:out value="${addMinibus}" />
                </button>
            </form>
            <div>
                <span style="color:red;">${requestScope.error}</span>
            </div>
        </div>
    </body>
</html>