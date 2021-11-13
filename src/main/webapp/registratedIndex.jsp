<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

        <c:set var="count" scope="session" value="${sessionScope.count}" />

<html>
    <head>
        <title>${sessionScope.nameAccount}|${sessionScope.emailAccount}</title>
        <link rel="stylesheet" href="css/registratedIndex.css" type="text/css">
        <style type="text/css">
            button{ -moz-transition: all 0.3s 0.01s ease;
                     -o-transition: all 0.3s 0.01s ease;
                     -webkit-transition: all 0.3s 0.01s ease;color:white;background:black;margin-top:0%;margin-left:-8%;border:none;font-size:14px;font-family:Verdana;height: 72.36px;width: 255px;}
            button:hover{ background:#dcdcdc;
                              color: #1e90ff;
                              cursor:pointer;}
        </style>
                <fmt:setLocale value="${sessionScope.locale}" />
                <fmt:setBundle basename="localization.local" var="loc" />
                <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_btn" />
                <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_btn" />
                <fmt:message bundle="${loc}" key="local.locbutton.name.ch" var="ch_btn" />
                <fmt:message bundle="${loc}" key="local.otherSections" var="otherSections" />
                <fmt:message bundle="${loc}" key="local.cars" var="cars" />
                <fmt:message bundle="${loc}" key="local.trucks" var="trucks" />
                <fmt:message bundle="${loc}" key="local.allCars" var="allCars" />
                <fmt:message bundle="${loc}" key="local.contacts" var="contacts" />
                <fmt:message bundle="${loc}" key="local.enter" var="enter" />
                <fmt:message bundle="${loc}" key="local.registration" var="registration" />
                <fmt:message bundle="${loc}" key="local.future" var="future" />
                <fmt:message bundle="${loc}" key="local.legacy" var="legacy" />
                <fmt:message bundle="${loc}" key="local.minibus" var="minibus" />
                <fmt:message bundle="${loc}" key="local.testDrive" var="testDrive" />
                <fmt:message bundle="${loc}" key="local.service" var="service" />
                <fmt:message bundle="${loc}" key="local.autoHouse" var="autoHouse" />
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
                <fmt:message bundle="${loc}" key="local.logOff" var="logOff" />
    </head>
    <body id="body">

    <div id="maindiv">
        <div>
         <nav>
             <img src="img/icon.jpg" alt="icon" id="logo">
              <ul class="menu">
                  <li>
                      <a href=""><c:out value="${otherSections}" /></a>
                      <ul>
                          <li><a href="cars.jsp"><c:out value="${cars}" /></a></li>
                          <li><a href="truck.jsp"><c:out value="${trucks}"/></a></li>
                      </ul>
                  </li>
                  <li><a href="allCars.jsp"><c:out value="${allCars}"/></a></li>
                  <li style="margin-right: auto;"><a href="">
                        <form action="FrontController" method="get">
                           <input type="hidden" name="command" value="ACCOUNT_PAGE_COMMAND">
                           <button><c:out value="${sessionScope.nameAccount}  ${sessionScope.emailAccount}" /></button>
                        </form>
                  </a></li>
                  <li style="margin-right: auto;"><a href="">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="command" value="LOG_OFF_COMMAND">
                            <button><c:out value="${logOff}" /></button>
                        </form>
                  </a></li>
              </ul>
         </nav>

         <!--<span>${sessionScope.count}</span>-->
          <span style="color: white;background: red;width: 30px;border-radius: 100%;position: absolute;text-align: center;margin-top: -4.5%;margin-left: 70%;">
             <c:out value="${count}" />
          </span>

            <video pip="false" style="z-index: -1;" src="videos/gt63.mp4" autoplay loop width="100%" muted id="video"></video>
            <p id="gt63">Mercedes-Benz GT63</p>
            <p id="future">
                <c:out value="${future}" /><br><c:out value="${legacy}" />
            </p>
            <br><br>
            <span id="images">

                <table class="tabl">
                    <tr>
                        <th> <img pip="false" src="img/redmerc.jpg" alt="" height="210px" width="330px"></th>
                        <th> <img pip="false" src="img/marsh.jpg" alt="" height="210px" width="360px"></th>
                        <th> <img pip="false" src="img/gruz.jpg" alt="" height="210px" width="330px"></th>
                    </tr>
                    <tr>
                        <th><a class="ref" href="cars.jsp"><c:out value="${cars}" /></a></th>
                        <th style="font-size: 19px;"><a class="ref" href="minibus.jsp"><c:out value="${minibus}" /></a></th>
                        <th><a class="ref" href="truck.jsp"><c:out value="${trucks}" /></a></th>
                    </tr>
                </table>
                <p style="margin-left: 9%;color: #A9A9A9;">
         ________________________________________________________________________________________________________________________________________
                </p>
                <table class="tabl">
                    <tr>
                        <th> <img pip="false" src="img/contacts.png" alt="" height="210px" width="330px"></th>
                        <th> <img pip="false" src="img/copy.jpg" alt="" height="210px" width="360px"></th>
                        <th> <img pip="false" src="img/service.png" alt="" height="210px" width="330px"></th>
                    </tr>
                    <tr>
                        <th><a class="ref" href="contacts.jsp"><c:out value="${contacts}" /></a></th>
                        <th style="font-size: 19px;"><a class="ref" href="testDriveOrder.jsp"><c:out value="${testDrive}" /></a></th>
                        <th><a class="ref" href="serviceOrder.jsp"><c:out value="${service}" /></a></th>
                    </tr>
                </table>
                <p style="margin-left: 9%;color: #A9A9A9;">
                    ________________________________________________________________________________________________________________________________________
                           </p>
                <div style="height: 50px;background-color: white;width: 82.5%;margin-left: 9%;">
                    <table>
                        <tr>
                            <th><a class="network" href="https://www.facebook.com/mbbelarus"><img src="img/facebook.png" height="45px" width="40px" alt=""></a></th>
                            <th><a class="network" href="https://www.instagram.com/mbbelarus/"><img src="img/in.jpg" height="47px" width="49px" alt=""></a></th>
                            <th><a class="network" href="https://www.youtube.com/user/mbbelarus"><img src="img/yot.jpg" height="43px" width="49px" alt=""></a></th>
                        </tr>
                    </table>
                </div>
                <p style="margin-left: 9%;color: #A9A9A9;">
                    ________________________________________________________________________________________________________________________________________
               </p>
               <br>
                   <table style="margin-left: 7%;">
                   <tr>
                       <th style="width: 350px;text-align:left;vertical-align:top;">
                        <div style="margin-left: 10%;">
                            <p  style="color: white;"><c:out value="${autoHouse}" /></p>
                            <p  style="color: white;"><c:out value="${spare}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${service}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${cars}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${email}" />:<span style="color: deepskyblue;">&nbsp;info@mercedes-benz.by</span></p>
                        </div>
                       </th>
                       <th style="width: 300px;text-align: left;vertical-align:top;">
                        <div style="margin-left: 10%;padding-top:0px;">
                            <p style="color: white;"><c:out value="${hours}" /></p>
                            <p><span style="color: deepskyblue;" id="isOpened">Сейчас открыто</span></p>
                            <br>
                            <p  style="color: white;"><c:out value="${monday}" />:&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${tuesday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${wednesday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${thursday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${friday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${saturday}" />:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;"><c:out value="${sunday}" />:&nbsp;&nbsp;08.00-17.00</p>
                        </div>
                       </th>
                       <th style="width: 300px;text-align: left;vertical-align:top;">
                        <div style="margin-left: 10%;color: white;">
                            <c:out value="${howToFindUs}" />
                            <p><c:out value="${street}" /></p>
                            <p><c:out value="${cityCode}" /></p>
                            <p><c:out value="${phone}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                        </div>
                       </th>
                   </tr>
               </table>
               <p style="margin-left: 9%;color: #A9A9A9;">
                ________________________________________________________________________________________________________________________________________
           </p>
           <p style="margin-left: 9%;">©Mercedes-Benz <c:out value="${belarus}" />, 2021</p>
        </span>
        </div>
    </div>
    ${script}
    </body>
 </html>