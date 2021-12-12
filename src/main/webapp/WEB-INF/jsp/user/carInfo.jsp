<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Order</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/carInfo.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.auto" var="auto" />
        <fmt:message bundle="${loc}" key="local.price" var="pr" />
        <fmt:message bundle="${loc}" key="local.power" var="pow" />
        <fmt:message bundle="${loc}" key="local.ls" var="ls" />
        <fmt:message bundle="${loc}" key="local.accel" var="accel" />
        <fmt:message bundle="${loc}" key="local.sec" var="sec" />
        <fmt:message bundle="${loc}" key="local.consumption" var="consumption" />
        <fmt:message bundle="${loc}" key="local.liter" var="liter" />
        <fmt:message bundle="${loc}" key="local.engine" var="engine" />
        <fmt:message bundle="${loc}" key="local.tank" var="tank" />
        <fmt:message bundle="${loc}" key="local.trunk" var="trunk" />
        <fmt:message bundle="${loc}" key="local.speed" var="speed" />
        <fmt:message bundle="${loc}" key="local.kmh" var="kmh" />
        <fmt:message bundle="${loc}" key="local.type" var="type" />
        <fmt:message bundle="${loc}" key="local.makeOrder" var="makeOrder" />
        <fmt:message bundle="${loc}" key="local.td" var="td" />

    </head>
    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <div style="margin-left: 30%;">
                <img src="img/icon.jpg" alt="icon" id="icon">
                <ul>
                    <li>
                        <form action="FrontController" class="ft">
                            <button class="types">
                                <a style="text-decoration:none;color:white;" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/cars"><c:out value="${auto}" /></a>
                            </button>
                        </form>
                    </li>
                    <li>
                        <form action="FrontController" method="get" class="ft">
                            <input type="hidden" name="command" value="DEFINITE_TEST_DRIVE_COMMAND">
                            <input type="hidden" name="nameOfMark" value="${requestScope.nameOfMark}">
                            <button class="types">
                                <c:out value="${td}" />
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
                <span style="color: grey;position: absolute;margin-top: 12%;margin-left: 10%;font-size: 20px;"><c:out value="${pr}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 10%;">${requestScope.price}</span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 12%;margin-left: 25%;"><c:out value="${pow}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 25%;">${requestScope.power} <c:out value="${ls}" /></span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 12%;margin-left: 40%;"><c:out value="${accel}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 14%;margin-left: 40%;">${requestScope.acceleration} <c:out value="${sec}" /></span>

                <span style="color: grey;position: absolute;margin-top: 17%;margin-left: 10%;font-size: 20px;"><c:out value="${consumption}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 10%;">${requestScope.consumption} <c:out value="${liter}" /></span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 17%;margin-left: 25%;"><c:out value="${engine}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 25%;">${requestScope.engineVolume} <c:out value="${liter}" /></span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 17%;margin-left: 40%;"><c:out value="${tank}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 19%;margin-left: 40%;">${requestScope.tankVolume} <c:out value="${liter}" /></span>

                <span style="color: grey;position: absolute;margin-top: 22%;margin-left: 10%;font-size: 20px;"><c:out value="${trunk}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 10%;">${requestScope.trunkVolume} <c:out value="${liter}" /></span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 22%;margin-left: 25%;"><c:out value="${speed}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 25%;">${requestScope.maxSpeed} <c:out value="${kmh}" /></span>
                <span style="color: grey;position: absolute;font-size: 20px;margin-top: 22%;margin-left: 40%;"><c:out value="${type}" /></span>
                <span style="color: black;position: absolute;font-size: 20px;margin-top: 24%;margin-left: 40%;">${requestScope.type}</span>

            </div>
            <div>
                <div id="orderButton">
                    <form action="FrontController" method="get">
                        <input type="hidden" name="command" value="FORM_ORDER_COMMAND">
                        <input type="hidden" name="img" value=${requestScope.img}>
                        <input type="hidden" name="id" value="${requestScope.id}">
                        <button style="height: 50px;width: 200px;background-color: blue;color: white;border: none;border-radius: 2%;">
                            <c:out value="${makeOrder}" />
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>