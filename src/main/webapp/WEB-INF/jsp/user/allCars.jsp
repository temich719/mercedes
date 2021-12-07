<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <c:set var="com" scope="request" value="${requestScope.allCars}" />
     <c:set var="filter" scope="request" value="${requestScope.filtered}" />
     <c:set var="flag" scope="request" value="${requestScope.flag}" />
     <c:set var="typeOfCarsOnThisPage" scope="request" value="${requestScope.typeOfCarsOnThisPage}" />

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
        <link rel="stylesheet" href="css/adminAllOrders.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.cars" var="legk" />
        <fmt:message bundle="${loc}" key="local.minibus" var="mini" />
        <fmt:message bundle="${loc}" key="local.trucks" var="tr" />
        <fmt:message bundle="${loc}" key="local.back" var="backPage" />
        <fmt:message bundle="${loc}" key="local.modelRow" var="lineup" />
    </head>

    <body style="background-color: #DCDCDC;overflow-x:hidden;">
        <div id="topMenu">
            <img id="icon" src="img/icon.jpg" alt="icon">
            <ul>
                <li>
                   <form action="FrontController" method="get" class="ft" style="${requestScope.carForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="car">
                        <button class="types" style="${requestScope.carButton}"><c:out value="${legk}" /></button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft" style="${requestScope.minibusForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="minibus">
                        <button class="types" style="${requestScope.minibusButton}"><c:out value="${mini}" /></button>
                    </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft" style="${requestScope.truckForm}">
                        <input type="hidden" name="command" value="Filter_All_Cars_COMMAND">
                        <input type="hidden" name="type" value="truck">
                        <button class="types" style="${requestScope.truckButton}"><c:out value="${tr}" /></button>
                   </form>
                </li>
                <li>
                    <form action="FrontController" method="get" class="ft">
                        <input type="hidden" name="command" value="BACK_FROM_ALL_CARS_COMMAND">
                        <button class="types"><c:out value="${backPage}" /></button>
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

         <div style="margin-top: 52%;margin-left: 40%;align-items: center;align-content: center;">
            <div style="align-items: center;align-content: center;text-align: center;">
                <c:forEach var="pageNumber" items="${requestScope.numbers}" >
                    <form action="FrontController" method="get" style="float:left;margin-right: 2%;">

                        <c:choose>
                            <c:when test="${empty typeOfCarsOnThisPage}">
                                  <input type="hidden" name="command" value="ALL_CARS_NUMBER_OF_PAGE_COMMAND">
                            </c:when>
                            <c:otherwise>
                                 <c:choose>
                                    <c:when test="${typeOfCarsOnThisPage == 'cars'}">
                                        <input type="hidden" name="command" value="CARS_NUMBER_OF_PAGE_COMMAND">
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="command" value="MINIBUSES_NUMBER_OF_PAGE_COMMAND">
                                    </c:otherwise>
                                 </c:choose>
                            </c:otherwise>
                        </c:choose>

                        <!--<input type="hidden" name="command" value="ALL_CARS_NUMBER_OF_PAGE_COMMAND">-->
                        <input type="hidden" name="number_of_page" value="${pageNumber}">
                        <button class="pages" type="submit" style="border: none;background-color: #DCDCDC;font-size: 20px;">
                            <c:out value="${pageNumber}" />
                        </button>
                    </form>
                </c:forEach>
            </div>
         </div>

    </body>
</html>