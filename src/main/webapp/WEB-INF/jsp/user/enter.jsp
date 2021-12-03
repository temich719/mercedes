<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <c:set var="script" scope="request" value="${requestScope.script}" />

<html>

<head>
    <title>Enter</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/enter.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.enter" var="enter" />
    <fmt:message bundle="${loc}" key="local.password" var="password" />
    <fmt:message bundle="${loc}" key="local.toEnter" var="toEnter" />
    <fmt:message bundle="${loc}" key="local.notRegYet" var="notRegYet" />
    <fmt:message bundle="${loc}" key="local.forgot" var="forgot" />
    <fmt:message bundle="${loc}" key="local.registration" var="registration" />
</head>

<body style="background-image:url(img/back.jpg)">
    <div id="mainDiv">
        <form method="get" action="FrontController">
            <br><br><br><br>
            <h1><c:out value="${enter}" /></h1>
            <br><br>
            <input id="firstInput" name="email" placeholder="example@gmail.com"><br>
            <br><br>
            <input id="secondInput" type="password" name="password" placeholder="${password}" maxlength="16">
            <p><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/codeConfirmNewPassword"><c:out value="${forgot}" /></a></p>
            <br><br>
            <input type="hidden" name="command" value="ENTER_COMMAND">
            <p style="color: red;">${requestScope.error}</p>
            <button id="button" name="submit" type="submit"><c:out value="${toEnter}" /></button>
            <br><br>
            <i><p style="font-size: 13px;"><c:out value="${notRegYet}" /><a id="hre" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/firstStep"><c:out value="${registration}" /></a></p></i>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/noScroll.js"></script>
    <c:if test="${not empty script}">
        <script src="js/wrongCodeAlert.js"></script>
    </c:if>
</body>
</html>