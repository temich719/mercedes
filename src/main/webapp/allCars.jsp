<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.impl.CarDAOImpl" %>

     <c:set var="com" scope="request" value="<%=CarDAOImpl.getAllCars()%>" />
     <c:set var="filter" scope="request" value="${requestScope.filtered}" />
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
        <link rel="stylesheet" href="css/allCars.css" type="text/css">
    </head>

    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <img id="icon" src="img/icon.jpg" alt="icon">
            <ul>
                <li>
                   <form action="FrontController" method="get" class="ft" style="${requestScope.carForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="car">
                        <button class="types" style="${requestScope.carButton}">Легковые</button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft" style="${requestScope.minibusForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="minibus">
                        <button class="types" style="${requestScope.minibusButton}">Малотоннажные</button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft" style="${requestScope.truckForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="truck">
                        <button class="types" style="${requestScope.truckButton}">Грузовые</button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft">
                        <input type="hidden" name="command" value="BACK_FROM_ALL_CARS_COMMAND">
                        <button class="types">Назад</button>
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
        <div style="margin-left: 4%;">

            <c:forEach var="car" items="${requestScope.cars}" >
            <form action="FrontController" method="get" class="formCarButton">
                <input type="hidden" name="command" value="FORM_ORDER_COMMAND">
                <input type="hidden" name="img" value=${car.getImagePath()}>
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