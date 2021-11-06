<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.impl.CarDAOImpl" %>

     <c:set var="cars" scope="request" value="<%=CarDAOImpl.getAllCars()%>" />
     <c:set var="select" scope="request" value="${requestScope.select}" />

<html>
    <head>
        <title>Fill in the order</title>
        <meta charset="UTF-8">
        <style>
            button:hover{
                cursor:pointer;
            }
        </style>
    </head>

    <body>
        <div id="mainDiv">
            <span style="font-size: 30px;position: absolute;margin-top: 2%;margin-left: 23%;"><b>Запись на сервис</b></span>
            <div style="width: 50%;position: absolute;border: 1px solid grey;height: 80%;margin-left: 23%;margin-top: 7%;">
                <div style="position: absolute;margin-left: 50%;margin-top: 13%;">
                   <!-- <img src="images/hatchback.png" alt="" style=" width: 657px;width: 345px; padding-top: 4px;padding-left: 5px;">-->
                </div>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="MAKE_SERVICE_ORDER_COMMAND">
                    <input type="hidden" name="mark" value=${select}>
                    <c:choose>
                        <c:when test="${empty select}">
                             <span style="position: absolute;margin-top: 5%;margin-left: 60%;">
                                   <span style="font-size: 18px;">
                                        Выберете машину<span style="color: red;">*</span>
                                    </span>
                             </span>
                        </c:when>
                        <c:otherwise>
                             <span style="position: absolute;margin-top: 5%;margin-left: 60%;">
                                <span style="font-size: 18px;">
                                     Название машины:
                                 </span>
                             </span>
                        </c:otherwise>
                    </c:choose>
                    <c:choose>
                       <c:when test="${empty select}">
                          <select name="selectName" style="margin-top: 9%;margin-left: 60%;width: 35%;position: absolute;">
                             <c:forEach var="car" items="${requestScope.cars}" >
                                 <option>
                                    <c:out value="${car.getNameOfMark()}" />
                                 </option>
                             </c:forEach>
                          </select>
                       </c:when>
                        <c:otherwise>
                          <span style="margin-top: 9%;margin-left: 60%;width: 35%;position: absolute;">
                            <span style="font-size: 18px;"><c:out value="${select}" /></span>
                            <input type="hidden" name="select" value=${select}>
                          </span>
                        </c:otherwise>
                    </c:choose>
                    <span style="position: absolute;margin-top: 5%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            Имя:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 9%;margin-left: 7%;width: 40%;" type="text" name="name" placeholder="Иван"
                    value=${sessionScope.accountName}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            Фамилия:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="surname" placeholder="Иванов"
                    value=${sessionScope.accountSurname}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            E-mail:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="email" placeholder="example@mail.ru"
                    value=${sessionScope.emailAccount}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            Телефон:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="phone" placeholder="+375251111111"><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            Дата:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="date" name="date"><br>
                    <span style="position:absolute;margin-left:9%;margin-top:5%;font-size:20px;border:none;color:red;width:350px;">
                        ${requestScope.error}
                    </span>
                    <button style="margin-top:15%;margin-left:9%;width:200px;height: 30px;border:none;background: orange;color: white;">
                        Заказать
                    </button>
                    <span style="position: absolute;margin-left: 9%;margin-top: 16%;">
                        <span style="color: red;">*</span><span style="font-size: 15px;">обязательные поля</span>
                    </span>
                </form>
            </div>
        </div>
    </body>
</html>