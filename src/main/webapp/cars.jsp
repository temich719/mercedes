<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.impl.CarDAOImpl" %>

     <c:set var="com" scope="request" value="<%=CarDAOImpl.getCars()%>" />
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
    </head>

    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <img id="icon" src="img/icon.jpg" alt="icon">
            <ul>
                <li>
                    <form action="FrontController" method="get" style="${requestScope.sedanForm}" class="ft">
                        <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                        <input type="hidden" name="carButton" value="sedan">
                        <button class="types" style="${requestScope.sedanButton}">Седан</button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.hatchbackForm}" class="ft">
                        <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                        <input type="hidden" name="carButton" value="hatchback">
                        <button class="types" style="${requestScope.hatchbackButton}">Хэтчбэк</button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.coupeForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="coupe">
                         <button class="types" style="${requestScope.coupeButton}">Купе</button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.crossForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="cross">
                         <button class="types" style="${requestScope.crossButton}">Кроссовер</button>
                    </form>
                </li>
                <li>
                   <form action="FrontController" method="" style="${requestScope.offRoadForm}" class="ft">
                       <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                       <input type="hidden" name="carButton" value="offRoad">
                       <button class="types" style="${requestScope.offRoadButton}">Внедорожник</button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="" style="${requestScope.minivanForm}" class="ft">
                         <input type="hidden" name="command" value="SELECT_CAR_TYPE_COMMAND">
                         <input type="hidden" name="carButton" value="minivan">
                         <button class="types" style="${requestScope.minivanButton}">Минивен</button>
                    </form>
                </li>
            </ul>
        </div>
        <br><br><br><br>
        <div id="model">
            <h1>
                Модельный ряд
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