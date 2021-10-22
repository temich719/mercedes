<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<html>

<head>
    <title>Confirmation</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/confirmation.css" type="text/css">
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="get" action="FrontController">
            <br><br><br><br>
            <h1>Подтверждение</h1>
            <br>
            <h3>Введите код отправленный вам на почту</h3>
            <br><br>
            <input name="confirmation">
            <br>
            <p style="color: red;">${requestScope.error}</p>
            <br><br><br><br><br>
            <input type="hidden" name="command" value="CONFIRM_COMMAND">
            <input type="hidden" name="code" value=${sessionScope.code}>
            <input type="hidden" name="name" value=${sessionScope.name}>
            <input type="hidden" name="surname" value=${sessionScope.surname}>
            <input type="hidden" name="email" value=${sessionScope.email}>
            <input type="hidden" name="password" value=${sessionScope.password}>
            <button name="submit" type="submit">Зарегистрироваться</button>
            <br><br><br><br>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/index.js"></script>
</body>
</html>