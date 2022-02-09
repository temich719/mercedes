<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <title>new Password</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/forgetPassword.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.changePassword" var="confirmation" />
    <fmt:message bundle="${loc}" key="local.newPassword" var="newPassword" />
    <fmt:message bundle="${loc}" key="local.confirmNewPass" var="confirmNewPass" />
    <fmt:message bundle="${loc}" key="local.change" var="change" />
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="post" action="FrontController">
            <br><br><br>
            <h1><c:out value="${changePassword}" /></h1>
            <p><c:out value="${newPassword}" /></p>
            <input type="password" name="newPassword">
            <p style="color: red;">${requestScope.err}</p>
            <br><br>
            <p><c:out value="${confirmNewPass}" /></p>
            <input type="password" name="confirmNewPassword">
            <p style="color: red;">${requestScope.error}</p>
            <br><br><br><br>
            <input type="hidden" name="emailUpdate" value = "${sessionScope.emailUpdate}">
            <input type="hidden" name="command" value="FORGET_PASSWORD_COMMAND">
            <button name="submit" type="submit"><c:out value="${change}" /></button>
            <br><br><br>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/index.js"></script>
</body>
</html>