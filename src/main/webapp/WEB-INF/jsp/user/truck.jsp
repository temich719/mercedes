<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

      <c:set var="account" scope="request" value="${sessionScope.nameAccount}" />

      <c:choose>
         <c:when test="${empty account}">
             <c:set var="backLink" scope="request" value="FrontController?command=GO_TO_PAGE_COMMAND&pageName=index" />
         </c:when>
         <c:otherwise>
             <c:set var="backLink" scope="request" value="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/registratedIndex" />
         </c:otherwise>
      </c:choose>

<html>
    <head>
        <title>Trucks</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/truck.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.backToMain" var="backToMain" />
        <fmt:message bundle="${loc}" key="local.ourTrucks" var="ourTrucks" />
        <fmt:message bundle="${loc}" key="local.moreThenEconomical" var="moreThenEconomical" />
        <fmt:message bundle="${loc}" key="local.reviewOfCh" var="reviewOfCh" />
        <fmt:message bundle="${loc}" key="local.engineSpecifications" var="engineSpecifications" />
        <fmt:message bundle="${loc}" key="local.euro6" var="euro6" />
        <fmt:message bundle="${loc}" key="local.truckPower" var="truckPower" />
        <fmt:message bundle="${loc}" key="local.torque" var="torque" />
        <fmt:message bundle="${loc}" key="local.tanks" var="tanks" />
        <fmt:message bundle="${loc}" key="local.combination" var="combination" />
        <fmt:message bundle="${loc}" key="local.fitted" var="fitted" />
        <fmt:message bundle="${loc}" key="local.vol" var="vol" />
        <fmt:message bundle="${loc}" key="local.makeOrder" var="makeOrder" />
        <fmt:message bundle="${loc}" key="local.service" var="service" />
    </head>
    <body>
        <div id="mainDiv">
            <div id="divImage">
                 <img src="img/icon.jpg" alt="icon" id="icon">
            </div>
            <div id="divBackButton">
                <span id="backButton">
                    <a href="${backLink}" style="color: white;"><c:out value="${backToMain}" /></a>
                </span>
            </div>
        </div>
        <div>
            <div>
                <img src="img/tr.jpg" alt="minibus" id="truckImage">
                <div id="overImage">
                    <span style="font-size: 20px;"><c:out value="${ourTrucks}" /></span>
                    <br>
                    <span>______</span>
                    <br>
                    <br>
                    <span style="font-size: 38px;">Actros.</span>
                    <br><br>
                    <span style="font-size: 20px;"><c:out value="${moreThenEconomical}" /> – Actros</span>
                </div>
            </div>
        </div>
        <div>
            <div id="tech">
                <span style="color: white;padding-top: 0;font-size: 38px;"><b>______</b></span>
                <br><br><br>
                <span style="color: white;font-size: 38px;"><c:out value="${reviewOfCh}" />.</span>
                <br><br>
                <div class="divImg">
                    <img src="img/engine.jpg" alt="engine" class="images"><br>
                    <span style="color: white;font-size: 23px;">Actros – <c:out value="${engineSpecifications}" />.</span><br><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${euro6}" /></span><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${truckPower}" />,</span><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${torque}" />.</span>
                </div>
                <div class="divImg">
                    <img src="img/tank.jpg" alt="tank" class="images"><br>
                    <span style="color: white;font-size: 23px;"><c:out value="${tanks}" />.</span><br><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${combination}" /></span><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${fitted}" /></span><br>
                    <span style="color: white;font-size: 15px;"><c:out value="${vol}" />.</span>
                </div>
                <br>
            </div>
        </div>
        <div>
            <div id="orderButton">
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="TRUCK_ORDER_COMMAND">
                    <button style="height: 50px;width: 200px;background-color: blue;color: white;border: none;border-radius: 2%;">
                        <c:out value="${makeOrder}" />
                    </button>
                </form>
            </div>
            <div id="serviceButton">
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="TRUCK_SERVICE_ORDER_COMMAND">
                    <input type="hidden" name="truckMark" value="Actros">
                    <button style="height: 50px;width: 200px;background: white;color: black;border: none;border-radius: 2%;">
                        <c:out value="${service}" />
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>