<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Fill in the order</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/formOfOrder.css" type="text/css">
    </head>

    <body>
        <div id="mainDiv">
            <span style="font-size: 30px;position: absolute;margin-top: 2%;margin-left: 23%;"><b>Оформление заказа</b></span>
            <div style="width: 50%;position: absolute;border: 1px solid grey;height: 80%;margin-left: 23%;margin-top: 7%;">
                <span style="position: absolute;margin-left: 63%;margin-top: 10%;font-size: 25px;width:70%;">${requestScope.mark}</span>
                <div style="position: absolute;margin-left: 50%;margin-top: 13%;">
                   <img src="${requestScope.img}" alt="car" style=" width: 657px;width: 345px; padding-top: 4px;padding-left: 5px;">
                </div>
                <span style="position: absolute;margin-left: 70%;margin-top: 38%;font-size: 25px;width:70%;">${requestScope.price}</span>
                <span style="position: absolute;margin-left: 70%;margin-top: 50%;font-size: 25px;width:70%;">${requestScope.money}</span>
                <form action="FrontController" method="get">
                    <input type="hidden" name="command" value="MAKE_ORDER_COMMAND">
                    <input type="hidden" name="img" value=${requestScope.img}>
                    <input type="hidden" name="money" value=${requestScope.money}>
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
                    <span style="position:absolute;margin-left:9%;margin-top:5%;font-size:20px;border:none;color:red;width:350px;">
                        ${requestScope.error}
                    </span>
                    <button style="margin-top:20%;margin-left:9%;width:200px;height: 30px;border:none;background: orange;color: white;">
                        Заказать
                    </button>
                    <span style="position: absolute;margin-left: 9%;margin-top: 21%;">
                        <span style="color: red;">*</span><span style="font-size: 15px;">обязательные поля</span>
                    </span>
                </form>
            </div>
        </div>
    </body>
</html>