<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Admin</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/adminPage.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.adminPage" var="adminPage" />
        <fmt:message bundle="${loc}" key="local.getListOfUsers" var="getListOfUsers" />
        <fmt:message bundle="${loc}" key="local.getListOfOrders" var="getListOfOrders" />
        <fmt:message bundle="${loc}" key="local.addProduct" var="addProduct" />
        <fmt:message bundle="${loc}" key="local.deleteProduct" var="deleteProduct" />
        <fmt:message bundle="${loc}" key="local.changeInfoProduct" var="changeInfoProduct" />
        <fmt:message bundle="${loc}" key="local.back" var="back" />
    </head>

    <body>
        <span style="position: absolute;font-size: 40px;margin-left: 34%;margin-top: 3%;"><c:out value="${adminPage}" /></span>
        <div style="margin-top: 10%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_GET_ALL_USERS_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but"><c:out value="${getListOfUsers}" /></button>
            </form>
        </div>
        <div style="margin-top: 15%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_GET_ORDERS_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but"><c:out value="${getListOfOrders}" /></button>
            </form>
        </div>
        <div style="margin-top: 20%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_TO_ADD_CAR_PAGE_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but"><c:out value="${addProduct}" /></button>
            </form>
        </div>
        <div style="margin-top: 25%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_DELETE_CAR_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but"><c:out value="${deleteProduct}" /></button>
            </form>
        </div>
        <div style="margin-top: 30%;margin-left: 14%;position:absolute;width: 100%;">
            <form action="FrontController" method="get">
                <input type="hidden" name="command" value="ADMIN_EDIT_CAR_DESCRIPTION_COMMAND">
                <button style="width: 70%;height: 70px;text-align: center;" class="but"><c:out value="${changeInfoProduct}" /></button>
            </form>
        </div>
         <div style="margin-top: 40%;margin-left: 40%;position:absolute;width: 100%;">
            <a style="padding-top:1%;display:block;background:orange;color:white;text-decoration:none;width: 250px;height: 40px;text-align: center;" class="but" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/registratedIndex">
                <c:out value="${back}" />
            </a>
         </div>
        <script src="js/noScroll.js"></script>
    </body>
</html>