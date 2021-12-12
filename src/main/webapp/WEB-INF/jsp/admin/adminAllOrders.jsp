<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>All orders page</title>
        <meta charset="UTF-8">
          <link rel="stylesheet" href="css/adminAllOrders.css" type="text/css">
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
                            <input type="hidden" name="id" value="${order.getId()}">
                            <input type="hidden" name="pageNumber" value="${requestScope.pageNumber}">
                            <input type="hidden" name="command" value="DELETE_ORDER_FROM_ADMIN_COMMAND">
                            <button>
                                <span>❌</span>
                            </button>
                        </form>
                    </th>
                </tr>
            </c:forEach>
        </table>

        <div style="margin-top: 2%;margin-left: 41%;align-items: center;align-content: center;">
            <div style="align-items: center;align-content: center;text-align: center;">
                <c:forEach var="pageNumber" items="${requestScope.numbers}" >
                    <form action="FrontController" method="get" style="float:left;margin-right: 2%;background-color:white;">
                        <input type="hidden" name="command" value="ORDERS_NUMBER_OF_PAGE_COMMAND">
                        <input type="hidden" name="number_of_page" value="${pageNumber}">
                        <button class="pages" type="submit" style="border: none;font-size: 20px;background-color:white;">
                            <c:out value="${pageNumber}" />
                        </button>
                    </form>
                </c:forEach>
        </div>
        <br><br><br>


        <a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/admin/adminPage" style="display:block;text-decoration:none;width:300px;height:30px;background:orange;color:white;text-align:center;margin-top:3%;margin-left:0%;padding-top:1%;">Назад</a>
    </body>
</html>