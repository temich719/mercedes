<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Thanks</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/thanks.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.thanks" var="thanks" />
        <fmt:message bundle="${loc}" key="local.onEmail" var="onEmail" />
        <fmt:message bundle="${loc}" key="local.details" var="details" />
        <fmt:message bundle="${loc}" key="local.backToMain" var="backToMain" />
    </head>
    <body style="background-image:url(img/back.jpg)">
        <div id="mainDiv">
            <span id="thk">
                <b>
                    <c:out value="${thanks}" />!
                </b>
            </span>
            <span id="mail">
                <c:out value="${onEmail}" /> <span style="color: deepskyblue;">${requestScope.email}</span> <c:out value="${details}" />
            </span>
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="BACK_FROM_ALL_CARS_COMMAND">
                <button> <c:out value="${backToMain}" /></button>
            </form>
        </div>
        <script src="js/noScroll.js"></script>
    </body>
</html>