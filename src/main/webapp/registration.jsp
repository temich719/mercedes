<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title>Registration</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/registration.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.password" var="password" />
    <fmt:message bundle="${loc}" key="local.toRegister" var="register" />
    <fmt:message bundle="${loc}" key="local.regAlready" var="regAlready" />
    <fmt:message bundle="${loc}" key="local.enter" var="enter" />
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="get" action="FrontController">
            <br><br><br><br>
            <h1><c:out value="${registration}" /> </h1>
            <br><br>
            <input id="firstInput" name="email" placeholder="example@gmail.com"><br>
            <p style="color: red;">${requestScope.error}</p>
            <br>
            <input id="secondInput" type="password" name="password" placeholder="${password}" maxlength="16">
            <p style="color: red;">${requestScope.err}</p>
            <br>
            <input type="hidden" name="command" value="REGISTRATION_COMMAND">
            <button id="button" name="submit" type="submit"><c:out value="${register}" /></button>
            <br><br><br><br>
            <i><p style="font-size: 13px;"><c:out value="${regAlready}" /><a id="hre" href="enter.jsp"><c:out value="${enter}" /></a></p></i>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/noScroll.js"></script>
</body>

</html>
