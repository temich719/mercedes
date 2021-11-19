<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
     <%@ page import="dao.impl.CarDAOImpl" %>

     <c:set var="cars" scope="request" value="<%=CarDAOImpl.getAllCars()%>" />
     <c:set var="select" scope="request" value="${requestScope.select}" />
     <c:set var="sel" scope="request" value="${requestScope.sel}" />
     <c:set var="def" scope="request" value="${requestScope.defImage}" />

<html>
    <head>
        <title>Fill in the order</title>
        <meta charset="UTF-8">
        <style>
            button:hover{
                cursor:pointer;
            }
        </style>
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.testDrive" var="testDrive" />
        <fmt:message bundle="${loc}" key="local.makeOrder" var="makeOrder" />
        <fmt:message bundle="${loc}" key="local.name" var="name" />
        <fmt:message bundle="${loc}" key="local.surname" var="surname" />
        <fmt:message bundle="${loc}" key="local.email" var="email" />
        <fmt:message bundle="${loc}" key="local.phone" var="phone" />
        <fmt:message bundle="${loc}" key="local.date" var="date" />
        <fmt:message bundle="${loc}" key="local.toOrder" var="toOrder" />
        <fmt:message bundle="${loc}" key="local.requiredFields" var="requiredFields" />
        <fmt:message bundle="${loc}" key="local.chooseCar" var="chooseCar" />
        <fmt:message bundle="${loc}" key="local.nameOfCar" var="nameOfCar" />
        <fmt:message bundle="${loc}" key="local.exampleName" var="exName" />
        <fmt:message bundle="${loc}" key="local.exampleSurname" var="exSurname" />
    </head>

    <body>
        <div id="mainDiv">
            <span style="font-size: 30px;position: absolute;margin-top: 2%;margin-left: 23%;"><b><c:out value="${testDrive}" /></b></span>
            <div style="width: 50%;position: absolute;border: 1px solid grey;height: 80%;margin-left: 23%;margin-top: 7%;">
                <div style="position: absolute;margin-left: 50%;margin-top: 13%;">
                   <!-- <img src="images/hatchback.png" alt="" style=" width: 657px;width: 345px; padding-top: 4px;padding-left: 5px;">-->
                </div>
                <form action="FrontController" method="get">
                    <input type="hidden" name="defImage" value=${def}>
                    <input type="hidden" name="command" value="MAKE_TEST_DRIVE_ORDER_COMMAND">
                    <input type="hidden" name="mark" value=${select}>
                    <c:choose>
                        <c:when test="${empty select}">
                             <span style="position: absolute;margin-top: 5%;margin-left: 60%;">
                                   <span style="font-size: 18px;">
                                        <c:out value="${chooseCar}" /><span style="color: red;">*</span>
                                    </span>
                             </span>
                        </c:when>
                        <c:otherwise>
                             <span style="position: absolute;margin-top: 5%;margin-left: 60%;">
                                <span style="font-size: 18px;">
                                     <c:out value="${nameOfCar}" />:
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
                            <span style="font-size: 18px;"><c:out value="${sel}" /></span>
                            <input type="hidden" name="sel" value=${sel}>
                          </span>
                        </c:otherwise>
                    </c:choose>
                    <span style="position: absolute;margin-top: 5%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            <c:out value="${name}" />:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 9%;margin-left: 7%;width: 40%;" type="text" name="name" placeholder=${exName}
                    value=${sessionScope.accountName}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            <c:out value="${surname}" />:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="surname" placeholder=${exSurname}
                    value=${sessionScope.accountSurname}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            <c:out value="${email}" />:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="email" placeholder="example@mail.ru"
                    value=${sessionScope.emailAccount}><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            <c:out value="${phone}" />:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="text" name="phone" placeholder="+375251111111"><br>
                    <span style="position: absolute;margin-top: 3%;margin-left: 7%;">
                        <span style="font-size: 18px;">
                            <c:out value="${date}" />:<span style="color: red;">*</span>
                        </span>
                    </span>
                    <input style="margin-top: 7%;margin-left: 7%;width: 40%;" type="date" name="date"><br>
                    <span style="position:absolute;margin-left:9%;margin-top:5%;font-size:20px;border:none;color:red;width:350px;">
                          ${requestScope.error}
                    </span>
                    <button style="margin-top:15%;margin-left:9%;width:200px;height: 30px;border:none;background: orange;color: white;">
                        <c:out value="${toOrder}" />
                    </button>
                    <span style="position: absolute;margin-left: 9%;margin-top: 16%;">
                        <span style="color: red;">*</span><span style="font-size: 15px;"><c:out value="${requiredFields}" /></span>
                    </span>
                </form>
            </div>
        </div>
    </body>
</html>