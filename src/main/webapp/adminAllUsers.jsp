<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>All users page</title>
    </head>
    <body>
        <h1 style="margin-left:35%;margin-top:3%;">Список пользователей</h1>
        <table style="margin-left:20%;margin-top:2%;">
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
                    <span style="font-size:30px;">Тип доступа</span>
                </th>
            </tr>
            <c:forEach var="user" items="${requestScope.users}" >
                <tr>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${user.getName()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${user.getSurname()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${user.getEmail()}" /></span>
                    </th>
                    <th style="width: 170px;height:50px;text-align: center;">
                        <span><c:out value="${user.getAccessType()}" /></span>
                    </th>
                    <th style="width: 30px;height:50px;text-align: center;">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="service" value="${order.getService()}">
                            <input type="hidden" name="mark" value="${order.getMark()}">
                            <input type="hidden" name="price" value="${order.getPrice()}">
                            <input type="hidden" name="date" value="${order.getDate()}">
                            <input type="hidden" name="phone" value="${order.getPhone()}">
                            <input type="hidden" name="name" value="${order.getName()}">
                            <input type="hidden" surname="surname" value="${order.getSurname()}">
                            <input type="hidden" email="email" value"${order.getEmail()}">
                            <input type="hidden" name="command" value="DELETE_USER_FROM_ADMIN_COMMAND">
                            <button>
                                <span>❌</span>
                            </button>
                        </form>
                    </th>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>