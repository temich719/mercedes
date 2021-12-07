<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <c:set var="account" scope="request" value="${sessionScope.nameAccount}" />
     <c:set var="miniBus" scope="request" value="${requestScope.miniBus}" />

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
        <title>Minibus</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/minibus.css" type="text/css">
        <link rel="stylesheet" href="css/adminAllOrders.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.backToMain" var="backToMain" />
        <fmt:message bundle="${loc}" key="local.reviewLowTonnage" var="reviewLowTonnage" />
        <fmt:message bundle="${loc}" key="local.autoMB" var="autoMB" />
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
                <img src="img/minibus.jpg" alt="minibus" id="minibusImage">
            </div>
            <div id="overImage">
                <span style="color: #DCDCDC;font-size: 48px;">
                    <c:out value="${reviewLowTonnage}" />
                </span>
                <br>
                <span style="color: #DCDCDC;font-size: 42px;padding-left: 7%;">
                    <c:out value="${autoMB}" />
                </span>
            </div>
        </div>
        <br><br>
        <div style="margin-bottom: 19%;">
            <div style="margin-top: 37%;padding-left: 11%;"> </div>
            <br>
            <div style="width: 100%;">
                <c:forEach var="minibus" items="${miniBus}" >
                    <div class="forms">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="command" value="MINIBUS_ORDER_COMMAND">
                            <input type="hidden" name="img" value="${minibus.getImagePath()}">
                            <button>
                                <div style="text-align:left;">
                                    <img src="${minibus.getImagePath()}" alt="minibus" class="im">
                                    <span style="font-size:18px;margin-left:5%;">
                                        <b><c:out value="${minibus.getNameOfMark()}" /></b>
                                    </span>
                                </div>
                            </button>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div style="margin-top: 35%;margin-left: 40%;align-items: center;align-content: center;">
            <div style="align-items: center;align-content: center;text-align: center;">
                <c:forEach var="pageNumber" items="${requestScope.numbers}" >
                    <form action="FrontController" method="get" style="float:left;margin-right: 2%;">
                        <input type="hidden" name="command" value = "MINIBUS_NUMBER_OF_PAGE">
                        <input type="hidden" name="number_of_page" value="${pageNumber}">
                        <button class="pages" type="submit" style="border: none;font-size: 20px;">
                            <c:out value="${pageNumber}" />
                        </button>
                    </form>
                </c:forEach>
            </div>
        </div>
    </body>
</html>