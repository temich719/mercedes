<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <title>Registration</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/firstStep.css" type="text/css">
    <fmt:setLocale value="${sessionScope.locale}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.name" var="name" />
    <fmt:message bundle="${loc}" key="local.surname" var="surname" />
    <fmt:message bundle="${loc}" key="local.regAlready" var="regAlready" />
    <fmt:message bundle="${loc}" key="local.enter" var="enter" />
    <fmt:message bundle="${loc}" key="local.further" var="further" />
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="post" action="FrontController">
            <br><br><br><br>
            <h1><c:out value="${registration}" /></h1>
            <br><br>
            <input class="input" type="text" name="name" placeholder="${name}"><br>
            <br><br>
            <input class="input" type="text" name="surname" placeholder="${surname}">
            <br><br><br><br><br>
            <input type="hidden" name="command" value="FIRST_STEP_COMMAND">
            <button id="button"  name="submit" type="submit">
                <c:out value="${further}" />
            </button>
            <br><br><br><br>
            <i><p style="font-size: 13px;"><c:out value="${regAlready}" /><a id="hre" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/enter"><c:out value="${enter}" /></a></p></i>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/noScroll.js"></script>
</body>

</html>