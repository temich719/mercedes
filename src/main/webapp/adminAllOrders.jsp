<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>All orders page</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1 style="margin-left:40%;margin-top:3%;">Список заказов</h1>
        <table style="margin-left:1%;margin-top:2%;">
            <tr>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Имя</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Фамилия</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Email</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Услуга</span>
                </th>
                <th style="width: 300px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Марка машины</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Цена</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Телефон</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Дата</span>
                </th>
            </tr>
            <c:forEach var="order" items="${requestScope.orders}" >
                <tr>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getName()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getSurname()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getEmail()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getService()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getMark()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getPrice()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getPhone()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${order.getDate()}" /></span>
                    </th>
                    <th style="width: 30px;height:50px;text-align: center;">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="service" value="${order.getService()}">
                            <input type="hidden" name="mark" value="${order.getMark()}">
                            <input type="hidden" name="price" value="${order.getPrice()}">
                            <input type="hidden" name="date" value="${order.getDate()}">
                            <input type="hidden" name="phone" value="${order.getPhone()}">
                            <input type="hidden" name="name" value="${order.getName()}">
                            <input type="hidden" name="surname" value="${order.getSurname()}">
                            <input type="hidden" name="email" value="${order.getEmail()}">
                            <input type="hidden" name="command" value="DELETE_ORDER_FROM_ADMIN_COMMAND">
                            <button>
                                <span>❌</span>
                            </button>
                        </form>
                    </th>
                </tr>
            </c:forEach>
        </table>
        <a href="adminPage.jsp" style="display:block;text-decoration:none;width:300px;height:30px;background:orange;color:white;text-align:center;margin-top:3%;margin-left:39%;padding-top:1%;">Назад</a>
    </body>
</html>