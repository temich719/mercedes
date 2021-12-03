<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Contacts</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/contacts.css" type="text/css">
        <fmt:setLocale value="${sessionScope.locale}" />
        <fmt:setBundle basename="localization.local" var="loc" />
        <fmt:message bundle="${loc}" key="local.autoHouse" var="autoHouse" />
        <fmt:message bundle="${loc}" key="local.cityCode" var="cityCode" />
        <fmt:message bundle="${loc}" key="local.phone" var="phone" />
        <fmt:message bundle="${loc}" key="local.contacts" var="contacts" />
        <fmt:message bundle="${loc}" key="local.minsk" var="minsk" />
        <fmt:message bundle="${loc}" key="local.legServ" var="legServ" />
        <fmt:message bundle="${loc}" key="local.grServ" var="grServ" />
        <fmt:message bundle="${loc}" key="local.spare" var="spare" />
        <fmt:message bundle="${loc}" key="local.servicePhone" var="servicePhone" />
        <fmt:message bundle="${loc}" key="local.email" var="email" />
        <fmt:message bundle="${loc}" key="local.hours" var="hours" />
        <fmt:message bundle="${loc}" key="local.opened" var="opened" />
        <fmt:message bundle="${loc}" key="local.monday" var="monday" />
        <fmt:message bundle="${loc}" key="local.tuesday" var="tuesday" />
        <fmt:message bundle="${loc}" key="local.wednesday" var="wednesday" />
        <fmt:message bundle="${loc}" key="local.thursday" var="thursday" />
        <fmt:message bundle="${loc}" key="local.friday" var="friday" />
        <fmt:message bundle="${loc}" key="local.saturday" var="saturday" />
        <fmt:message bundle="${loc}" key="local.sunday" var="sunday" />
        <fmt:message bundle="${loc}" key="local.friday" var="friday" />
        <fmt:message bundle="${loc}" key="local.howToFindUs" var="howToFindUs" />
        <fmt:message bundle="${loc}" key="local.street" var="street" />
        <fmt:message bundle="${loc}" key="local.cityCode" var="cityCode" />
        <fmt:message bundle="${loc}" key="local.phone" var="phone" />
        <fmt:message bundle="${loc}" key="local.belarus" var="belarus" />
        <fmt:message bundle="${loc}" key="local.script" var="script" />
        <fmt:message bundle="${loc}" key="local.how" var="how" />
    </head>
    <body>
       <div id="menu">
            <div class="topMenu" id="image">
                <img src="img/icon.jpg" alt="icon" id="icon">
            </div>
            <div class="topMenu" id="housePhone">
                <span id="house1">
                    <c:out value="${autoHouse}" />
                </span>
                <br>
                <span id="phone">
                    <c:out value="${phone}" /> +375 17 3099999
                </span>
            </div>
       </div>
       <br>
       <div>
        <div id="whiteDiv">
            <div id="contacts">
                <c:out value="${contacts}" />
            </div>
            <img src="img/co.jpg" alt="contacts" id="houseImage">
            <span id="house2">
                <c:out value="${autoHouse}" />
            </span>
            <br><br><br><br><br>
            <span id="adress">
                <c:out value="${minsk}" />, <c:out value="${street}" />
            </span>
            <br><br>
            <a href="https://www.google.com/maps/d/u/0/viewer?mid=1YEWmgELuwqFMNSS7r2wYg-Ep1tjpyHyV&ll=53.92198748333897%2C27.515708141555795&z=17"><span id="how"><c:out value="${how}" /></span></a>
            <br><br><br>
            <span id="legkService">
                <c:out value="${legServ}" />:<br>
                <span style="padding-left: 20%;color: #1E90FF;font-size: 18px;">+375 29 3099955</span>
            </span>
            <br><br><br>
            <span id="legkService">
                <c:out value="${grServ}" />:<br>
                <span style="padding-left: 20%;color: #1E90FF;font-size: 18px;">+375 17 3099959</span>
            </span>
            <br><br><br><br>
            <div style="height: 50px;background-color: white;margin-left: 40%;">
                <table>
                    <tr>
                        <th><a href="https://www.facebook.com/mbbelarus"><img src="img/facebook.png" height="45px" width="40px" alt=""></a></th>
                        <th><a href="https://www.instagram.com/mbbelarus/"><img src="img/in.jpg" height="47px" width="49px" alt=""></a></th>
                        <th><a href="https://www.youtube.com/user/mbbelarus"><img src="img/yot.jpg" height="43px" width="49px" alt=""></a></th>
                    </tr>
                </table>
            </div>
        </div>
       </div>
       <p style="margin-left: 9%;color: #A9A9A9;">
        _______________________________________________________________________________________________________________________________________________________
       </p>
       <br>
       <table style="margin-left: 7%;">
        <tr>
            <th style="width: 300px;text-align:left;">
                <div style="margin-left: 10%;">
                    <p style="color: white;"><c:out value="${autoHouse}" /></p>
                    <p style="color: white;" ><c:out value="${spare}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;"><c:out value="${service}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;"><c:out value="${cars}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <p style="color: white;"><c:out value="${email}" />:<span style="color: deepskyblue;">&nbsp;info@mercedes-benz.by</span></p>
                    <br><br><br><br><br><br><br><br><br>
                </div>
            </th>
            <th style="width: 300px;text-align: left;">
                <div style="margin-left: 10%;">
                    <p style="color: white;"><c:out value="${hours}" /></p>
                    <p><span style="color: deepskyblue;" id="isOpened"></span></p>
                    <br>
                    <p style="color: white;"><c:out value="${monday}" />:&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${tuesday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${wednesday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${thursday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${friday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${saturday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                    <p style="color: white;"><c:out value="${sunday}" />:&nbsp;&nbsp;08.00-17.00</p>
                </div>
            </th>
            <th style="width: 300px;text-align: left;">
                <div style="margin-left: 10%;color: white;">
                     <c:out value="${howToFindUs}" />
                    <p><c:out value="${street}" /></p>
                    <p><c:out value="${cityCode}" /></p>
                    <p><c:out value="${phone}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                    <br><br><br><br><br><br><br><br><br><br>
                </div>
            </th>
        </tr>
    </table>
    <p style="margin-left: 9%;color: #A9A9A9;">
        _______________________________________________________________________________________________________________________________________________________
    </p>
    <p style="margin-left: 9%;color: white;margin-bottom: 2%;">©Mercedes-Benz <c:out value="${belarus}" />, 2021</p>
    ${script}
    </body>
</html>