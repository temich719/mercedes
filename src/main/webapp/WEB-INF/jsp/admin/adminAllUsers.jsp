<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>All users page</title>
        <meta charset="UTF-8">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.usersList" var="usersList" />
        <fmt:message bundle="${loc}" key="local.accessType" var="accessType" />
        <fmt:message bundle="${loc}" key="local.upgradeToAdmin" var="upgradeToAdmin" />
        <fmt:message bundle="${loc}" key="local.name" var="name" />
        <fmt:message bundle="${loc}" key="local.surname" var="surname" />
    </head>
    <body>
        <h1 style="margin-left:35%;margin-top:3%;"><c:out value="${usersList}" /></h1>
        <table style="margin-left:20%;margin-top:2%;">
            <tr>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;"><c:out value="${name}" /></span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;"><c:out value="${surname}" /></span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;">Email</span>
                </th>
                <th style="width: 170px;height:50px;text-align: center;">
                    <span style="font-size:30px;"><c:out value="${accessType}" /></span>
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
                            <input type="hidden" name="name" value="${user.getName()}">
                            <input type="hidden" name="surname" value="${user.getSurname()}">
                            <input type="hidden" name="email" value="${user.getEmail()}">
                            <input type="hidden" name="accessType" value="${user.getAccessType()}">
                            <input type="hidden" name="command" value="DELETE_USER_FROM_ADMIN_COMMAND">
                            <button>
                                <span>❌</span>
                            </button>
                        </form>
                    </th>
                    <th style="width: 300px;height:50px;text-align: center;">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="name" value="${user.getName()}">
                            <input type="hidden" name="surname" value="${user.getSurname()}">
                            <input type="hidden" name="email" value="${user.getEmail()}">
                            <input type="hidden" name="id" value="${user.getId()}">
                            <input type="hidden" name="accessType" value="${user.getAccessType()}">
                            <input type="hidden" name="command" value="UPGRADE_USER_TO_ADMIN_COMMAND">
                            <button>
                                <span><c:out value="${upgradeToAdmin}" /></span>
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
                        <input type="hidden" name="command" value="USERS_NUMBER_OF_PAGE_COMMAND">
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