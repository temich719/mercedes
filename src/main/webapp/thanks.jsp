<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Thanks</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/thanks.css" type="text/css">
    </head>
    <body style="background-image:url(img/back.jpg)">
        <div id="mainDiv">
            <span id="thk">
                <b>
                    Спасибо за заказ!
                </b>
            </span>
            <span id="mail">
                На почту <span style="color: deepskyblue;">${requestScope.email}</span> отправлено письмо, в котором указаны детали заказа
            </span>
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="BACK_FROM_ALL_CARS_COMMAND">
                <button>Вернуться на главную</button>
            </form>
        </div>
        <script src="js/noScroll.js"></script>
    </body>
</html>