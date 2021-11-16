<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Delete product</title>
    </head>
    <body>
        <div>
            <h1 style="margin-left:37%;">Удаление товара</h1>
            <div>
                <h3>Легковые автомобили</h3>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="DELETE_CAR_COMMAND">
                    <select name="selectName" style="width: 35%;">
                         <c:forEach var="car" items="${requestScope.cars}" >
                             <option>
                                <c:out value="${car.getNameOfMark()}" />
                             </option>
                         </c:forEach>
                    </select>
                    <button>
                        Удалить
                    </button>
                </form>
            </div>
            <div>
                <h3>Малотоннажные автомобили</h3>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="DELETE_MINIBUS_COMMAND">
                    <select name="selectName" style="width: 35%;">
                         <c:forEach var="minibus" items="${requestScope.minibuses}" >
                             <option>
                                <c:out value="${minibus.getNameOfMark()}" />
                             </option>
                         </c:forEach>
                    </select>
                    <button>
                        Удалить
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>