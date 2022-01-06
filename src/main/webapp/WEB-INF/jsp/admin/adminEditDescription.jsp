<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Edit Description Page</title>
        <meta charset="UTF-8">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.changeInfoProduct" var="changeInfoProduct" />
        <fmt:message bundle="${loc}" key="local.changeInfo" var="changeInfo" />
        <fmt:message bundle="${loc}" key="local.product" var="product" />
    </head>
    <body>
        <div>
            <h1><c:out value="${changeInfoProduct}" /></h1>
            <div>
                <form action="FrontController" method="get">
                <input type="hidden" name="command" value="CHOOSE_CAR_FOR_CHANGING_INFO_COMMAND">
                <h3><c:out value="${product}" /></h3>
                 <select name="selectName" style="width: 35%;">
                     <c:forEach var="car" items="${requestScope.cars}" >
                         <option>
                            <c:out value="${car.getNameOfMark()}" />
                         </option>
                     </c:forEach>
                 </select>
                 <button><c:out value="${changeInfo}" /></button>
                 </form>
             </div>
        </div>
    </body>
</html>