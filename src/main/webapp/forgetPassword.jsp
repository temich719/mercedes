<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<html>

<head>
    <title>new Password</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/forgetPassword.css" type="text/css">
</head>

<body style="background-image: url(img/back.jpg);">
    <div id="mainDiv">
        <form method="get" action="FrontController">
            <br><br><br>
            <h1>Смена пароля</h1>
            <p>Введите новый пароль</p>
            <input type="password" name="newPassword">
            <p style="color: red;">${requestScope.err}</p>
            <br><br>
            <p>Подтвердите пароль</p>
            <input type="password" name="confirmNewPassword">
            <p style="color: red;">${requestScope.error}</p>
            <br><br><br><br>
            <input type="hidden" name="emailUpdate" value = "${sessionScope.emailUpdate}">
            <input type="hidden" name="command" value="FORGET_PASSWORD_COMMAND">
            <button name="submit" type="submit">Заменить</button>
            <br><br><br>
        </form>
        <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
    </div>
    <script src="js/index.js"></script>
</body>
</html>