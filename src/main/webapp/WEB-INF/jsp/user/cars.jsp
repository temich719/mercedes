<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <c:set var="com" scope="request" value="${requestScope.automobiles}" />
     <c:set var="filter" scope="request" value="${requestScope.cars}" />
     <c:set var="flag" scope="request" value="${requestScope.flag}" />

     <c:choose>
        <c:when test="${empty flag}">
             <c:set var="cars" scope="request" value="${com}" />
        </c:when>
        <c:otherwise>
             <c:set var="cars" scope="request" value="${filter}" />
        </c:otherwise>
     </c:choose>


<html>
    <head>
        <title>Cars</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/cars.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.sedan" var="sed" />
        <fmt:message bundle="${loc}" key="local.hatchback" var="hatch" />
        <fmt:message bundle="${loc}" key="local.coupe" var="coup" />
        <fmt:message bundle="${loc}" key="local.crossOver" var="cross" />
        <fmt:message bundle="${loc}" key="local.SUV" var="SUV" />
        <fmt:message bundle="${loc}" key="local.minivan" var="van" />
        <fmt:message bundle="${loc}" key="local.modelRow" var="lineup" />
    </head>

    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <img id="icon" src="img/icon.jpg" alt="icon">
            <ul>
                <li>
                    <form action="FrontController" method="get" style="${requestScope.sedanForm}" class="ft">
                        <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                        <input type="hidden" name="carButton" value="sedan">
                        <button class="types" style="${requestScope.sedanButton}"><c:out value="${sed}" /></button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.hatchbackForm}" class="ft">
                        <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                        <input type="hidden" name="carButton" value="hatchback">
                        <button class="types" style="${requestScope.hatchbackButton}"><c:out value="${hatch}" /></button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.coupeForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="coupe">
                         <button class="types" style="${requestScope.coupeButton}"><c:out value="${coup}" /></button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.crossForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="cross">
                         <button class="types" style="${requestScope.crossButton}"><c:out value="${cross}" /></button>
                    </form>
                </li>
                <li>
                   <form action="FrontController" method="" style="${requestScope.offRoadForm}" class="ft">
                       <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                       <input type="hidden" name="carButton" value="offRoad">
                       <button class="types" style="${requestScope.offRoadButton}"><c:out value="${SUV}" /></button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.minivanForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="minivan">
                         <button class="types" style="${requestScope.minivanButton}"><c:out value="${van}" /></button>
                    </form>
                </li>
            </ul>
        </div>
        <br><br><br><br>
        <div id="model">
            <h1>
                <c:out value="${lineup}" />
            </h1>
        </div>
        <div style="margin-left:4%;">
             <c:forEach var="car" items="${requestScope.cars}" >
               <form action="FrontController" method="get" class="formCarButton">
               <input type="hidden" name="command" value="VIEW_CAR_COMMAND">
               <input type="hidden" name="nameOfMark" value="${car.getNameOfMark()}">
                   <button class="carButton">
                        <div class="car" style="text-align: left;">
                             <span class="mark">
                                <span><c:out value="${car.getNameOfMark()}" /></span>
                             </span>
                             <span class="spanPrice">
                                <p class="price"><c:out value="${car.getPrice()}" /></p>
                             </span>
                             <img src="${car.getImagePath()}" alt="car" class="carImage">
                        </div>
                   </button>
               </form>
             </c:forEach>
        </div>
    </body>
</html>