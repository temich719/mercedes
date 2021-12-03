<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title>Confirmation</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/confirmation.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.confirmation" var="confirmation" />
    <fmt:message bundle="${loc}" key="local.inputCode" var="inputCode" />
    <fmt:message bundle="${loc}" key="local.continue" var="continue" />
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="get" action="FrontController">
            <br><br><br><br>
            <h1><c:out value="${confirmation}" /></h1>
            <br>
            <h3><c:out value="${inputCode}" /></h3>
            <br><br>
            <input name="confirmation">
            <br>
            <p style="color: red;">${requestScope.error}</p>
            <br><br><br><br><br>
            <input type="hidden" name="command" value="CHECK_CODE_COMMAND">
            <input type="hidden" name="codeOfConfirm" value=${sessionScope.codeOfConfirm}>
            <button name="submit" type="submit"><c:out value="${continue}" /></button>
            <br><br><br><br>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/index.js"></script>
</body>
</html>