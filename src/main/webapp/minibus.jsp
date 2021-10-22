<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.database.impl.DataBaseImpl" %>

     <c:set var="account" scope="request" value="${sessionScope.nameAccount}" />

     <c:choose>
         <c:when test="${empty account}">
              <c:set var="backLink" scope="request" value="index.jsp" />
         </c:when>
         <c:otherwise>
              <c:set var="backLink" scope="request" value="registratedIndex.jsp" />
         </c:otherwise>
     </c:choose>

<html>
    <head>
        <title>Minibus</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/minibus.css" type="text/css">
    </head>
    <body>
        <div id="mainDiv">
           <div id="divImage">
                <img src="img/icon.jpg" alt="icon" id="icon">
           </div>
           <div id="divBackButton">
               <span id="backButton">
                   <a href="${backLink}" style="color: white;">Вернуться на главную</a>
               </span>
           </div>
        </div>
        <div>
            <div>
                <img src="img/minibus.jpg" alt="minibus" id="minibusImage">
            </div>
            <div id="overImage">
                <span style="color: #DCDCDC;font-size: 48px;">
                    Обзор моделей малотоннажных
                </span>
                <br>
                <span style="color: #DCDCDC;font-size: 42px;padding-left: 7%;">
                    автомобилей Mercedes-Benz
                </span>
            </div>
        </div>
        <br><br>
        <div style="margin-bottom: 19%;">
            <div style="margin-top: 37%;padding-left: 11%;"> </div>
            <br>
            <div style="width: 100%;">
                <c:forEach var="minibus" items="<%=DataBaseImpl.getMinibuses()%>" >
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
    </body>
</html>