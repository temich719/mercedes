<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Delete product</title>
        <meta charset="UTF-8">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.del" var="delete" />
        <fmt:message bundle="${loc}" key="local.deleteProduct" var="deleteProduct" />
        <fmt:message bundle="${loc}" key="local.cars" var="cars" />
        <fmt:message bundle="${loc}" key="local.minibus" var="minibus" />
    </head>
    <body>
        <div>
            <h1 style="margin-left:37%;"><c:out value="${deleteProduct}" /></h1>
            <div>
                <h3><c:out value="${cars}" /></h3>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="DELETE_CAR_COMMAND">
                    <select name="selectName" style="width: 35%;">
                         <c:forEach var="car" items="${requestScope.cars}" >
                             <option>
                                <!--<input type="hidden" name="imagePath" value="${car.getImagePath()}">-->
                                <c:out value="${car.getNameOfMark()}" />
                             </option>
                         </c:forEach>
                    </select>
                    <button>
                        <c:out value="${delete}" />
                    </button>
                </form>
            </div>
            <div>
                <h3><c:out value="${minibus}" /></h3>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="DELETE_MINIBUS_COMMAND">
                    <select name="selectName" style="width: 35%;">
                         <c:forEach var="minibus" items="${requestScope.minibuses}" >
                             <option>
                                <!--<input type="hidden" name="imagePath" value="${minibus.getImagePath()}">-->
                                <c:out value="${minibus.getNameOfMark()}" />
                             </option>
                         </c:forEach>
                    </select>
                    <button>
                        <c:out value="${delete}" />
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>