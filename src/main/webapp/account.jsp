<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.impl.OrderDAOImpl" %>

     <c:set var="orders" scope="request" value="<%=OrderDAOImpl.getListOfOrders()%>" />

<html>
    <head>
        <title>Name Surname</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/account.css" type="text/css">
    </head>

    <body>
        <div id="topMenu">
            <ul>
                <li>
                    <a href="registratedIndex.jsp">На главную</a>
                </li>
            </ul>
        </div>
            <div id="files" style="margin-top: 10%;">
                <form method="get" action="FrontController" enctype="multipart/data">
                    <input type="hidden" name="command" value="UPLOAD_AVATAR_COMMAND">
                    <!--<label for="ava"><img style="width:400px;height:400px;" src="img/avatar.jpg" id="avatar" /></label>-->
                    <label for="ava"><img style="width:400px;height:400px;" src="${requestScope.avatarImage}" id="avatar" /></label>
                    <input type="file" id="ava" name="ava" /><br/>
                    <input type="hidden" name="command" value="UPLOAD_AVATAR_COMMAND">
                    <input style="width:400px;" type="submit" id="upload" value="Загрузить аватар"/>
                </form>
            </div>
            <div style="margin-left:5%;">
                <p class="userName"><c:out value="${sessionScope.nameAccount}"/></p>
                <span style="color:white;position:absolute;  margin-top: 7%;margin-left:28%;font-size: 30px;">История заказов</span>
            </div>
            <div>
                <table style="position: absolute;margin-top: 12%;margin-left: 35%;border:3px solid white;border-right:none;">
                    <tr>
                        <th style="width: 200px;height:50px;text-align: center;border: 1px solid white;font-size: 20px;">
                            <span style="color: white;">Услуга</span>
                        </th>
                        <th style="width: 200px;height:50px;text-align: center;border: 1px solid white;font-size: 20px;">
                            <span style="color: white;">Марка машины</span>
                        </th>
                        <th style="width: 200px;height:50px;text-align: center;border: 1px solid white;font-size: 20px;">
                            <span style="color: white;">Цена</span>
                        </th>
                        <th style="width: 200px;height:50px;text-align: center;border: 1px solid white;font-size: 20px;">
                            <span style="color: white;">Дата</span>
                        </th>
                    </tr>
                    <c:forEach var="order" items="${orders}" >
                    <c:if test="${sessionScope.emailAccount == order.getEmail()}">
                        <tr>

                        <c:choose>
                            <c:when test="${order.getStatus() eq 'unread'}">
                               <th class="row">
                                    <span style="color: white;"><c:out value="${order.getService()}" /></span>
                               </th>
                               <th class="row">
                                    <span style="color: white;"><c:out value="${order.getMark()}" /></span>
                               </th>
                               <th class="row">
                                    <span style="color: white;"><c:out value="${order.getPrice()}" /></span>
                               </th>
                               <th class="row">
                                    <span style="color: white;"><c:out value="${order.getDate()}" /></span>
                               </th>
                            </c:when>
                            <c:otherwise>
                                 <th class="readRow">
                                    <span style="color: white;"><c:out value="${order.getService()}" /></span>
                                 </th>
                                 <th class="readRow">
                                    <span style="color: white;"><c:out value="${order.getMark()}" /></span>
                                 </th>
                                 <th class="readRow">
                                    <span style="color: white;"><c:out value="${order.getPrice()}" /></span>
                                 </th>
                                 <th class="readRow">
                                    <span style="color: white;"><c:out value="${order.getDate()}" /></span>
                                 </th>
                            </c:otherwise>
                        </c:choose>
                            <th style="width: 75px;height:50px;text-align: center;font-size: 20px;">
                                <form action="FrontController" method="get">
                                    <input type="hidden" name="service" value="${order.getService()}">
                                    <input type="hidden" name="mark" value="${order.getMark()}">
                                    <input type="hidden" name="command" value="MARK_AS_READ_COMMAND">
                                    <button class="but" data-title="Пометить как прочитанное"><span>✅</span></button>
                                </form>
                            </th>
                            <th style="width: 30px;height:50px;text-align: center;font-size: 20px;">
                                <form action="FrontController" method="get">
                                <input type="hidden" name="service" value="${order.getService()}">
                                <input type="hidden" name="mark" value="${order.getMark()}">
                                <input type="hidden" name="price" value="${order.getPrice()}">
                                <input type="hidden" name="date" value="${order.getDate()}">
                                <input type="hidden" name="phone" value="${order.getPhone()}">
                                    <input type="hidden" name="command" value="DELETE_ORDER_FROM_ACCOUNT_COMMAND">
                                    <button class="but" data-title="Убрать заказ из профиля"><span>❌</span></button>
                                </form>
                            </th>
                        </tr>
                    </c:if>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>