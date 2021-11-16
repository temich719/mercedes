<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Admin</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/adminPage.css" type="text/css">
    </head>

    <body>
        <span style="position: absolute;font-size: 40px;margin-left: 34%;margin-top: 3%;">Страница администратора</span>
        <div style="margin-top: 10%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_GET_ALL_USERS_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but">Получить список пользователей</button>
            </form>
        </div>
        <div style="margin-top: 15%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_GET_ORDERS_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but">Получить список заказов</button>
            </form>
        </div>
        <div style="margin-top: 20%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_ADD_CAR_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but">Добавить товар</button>
            </form>
        </div>
        <div style="margin-top: 25%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_DELETE_CAR_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but">Удалить товар</button>
            </form>
        </div>
        <div style="margin-top: 30%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_EDIT_CAR_DESCRIPTION_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but">Изменить описание товара</button>
            </form>
        </div>
        <script src="js/index.js"></script>
    </body>
</html>