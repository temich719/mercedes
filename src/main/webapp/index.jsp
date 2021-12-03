<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

     <c:choose>
         <c:when test="${empty locale}">
               <c:set var="locale" scope="session" value="ru" />
         </c:when>
         <c:otherwise>

         </c:otherwise>
     </c:choose>

<html>
    <head>
        <title>Mercedes-Benz</title>
        <link rel="stylesheet" href="css/index.css" type="text/css">
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
        <fmt:message bundle="${loc}" key="local.ru" var="ru" />
        <fmt:message bundle="${loc}" key="local.ch" var="ch" />
        <fmt:message bundle="${loc}" key="local.en" var="en" />
    </head>
    <body id="body">

    <div id="maindiv">
        <div>
         <nav>
             <img src="img/icon.jpg" alt="icon" id="logo">
              <ul class="menu">
                  <li>
                      <a href=""> <c:out value="${otherSections}" /> </a>
                      <ul>
                          <li><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/cars"> <c:out value="${cars}" /> </a></li>
                          <li><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/truck"> <c:out value="${trucks}"/> </a></li>
                      </ul>
                    </li>
                  <li><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/allCars"> <c:out value="${allCars}"/> </a></li>
                  <li><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/contacts"> <c:out value="${contacts}" /> </a></li>
                  <li><a href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/enter"><c:out value="${enter}" /></a></li>
                  <li><a style="border: none;" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/firstStep"> <c:out value="${registration}" /> </a></li>
              </ul>
         </nav>

            <video pip="false" style="z-index: -1;" src="videos/gt63.mp4" autoplay loop width="100%" muted id="video"></video>
            <p id="gt63">Mercedes-Benz GT63</p>
            <p id="future">
                <c:out value="${future}" />
                <br>
                <c:out value="${legacy}" />
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
                        <th><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/cars"><c:out value="${cars}" /></a></th>
                        <th style="font-size: 19px;"><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/minibus"><c:out value="${minibus}" /></a></th>
                        <th><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/truck"><c:out value="${trucks}" /></a></th>
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
                        <th><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/contacts"><c:out value="${contacts}" /></a></th>
                        <th style="font-size: 19px;"><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/testDriveOrder"><c:out value="${testDrive}" /></a></th>
                        <th><a class="ref" href="FrontController?command=GO_TO_PAGE_COMMAND&pageName=jsp/user/serviceOrder"><c:out value="${service}" /></a></th>
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

               <table style="margin-left: 7%;">
                   <tr>
                       <th style="width: 470px;text-align:left;vertical-align:top;">
                        <div style="margin-left: 10%;">
                            <p  style="color: white;"><c:out value="${autoHouse}" />:</p>
                            <p  style="color: white;"><c:out value="${spare}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${service}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${cars}" />:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;"><c:out value="${email}" />:<span style="color: deepskyblue;">&nbsp;info@mercedes-benz.by</span></p>
                        </div>
                       </th>
                       <th style="width: 370px;text-align: left;vertical-align:top;">
                        <div style="margin-left: 10%;padding-top:0px;">
                            <p style="color: white;"><c:out value="${hours}" /></p>
                            <p>
                                <span style="color: deepskyblue;" id="isOpened" name="open">
                                </span>
                            </p>
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
           <div style="margin-left: 9%;display:inline;">©Mercedes-Benz <c:out value="${belarus}" />, 2021</div>
           <div style = "margin-left:42%;display:inline;">
                <form action="FrontController" method="POST" style="display:inline;">
                        <input type="hidden" name="command" value="CHANGE_LANGUAGE_COMMAND">
                        <input type="hidden" name="locale" value="ch">
                        <button id="butt" type="submit" style="cursor: pointer;width:8%;color:white;background-color:black;border:none;border-bottom:1px solid white;">
                        <c:out value="${ch}" />
                        </button>
                </form>
                <form action="FrontController" method="POST" style="display:inline;">
                        <input type="hidden" name="command" value="CHANGE_LANGUAGE_COMMAND">
                        <input type="hidden" name="locale" value="ru">
                        <button id="butt" type="submit" style="cursor: pointer;width:8%;color:white;background-color:black;border:none;border-bottom:1px solid white;">
                        <c:out value="${ru}" />
                        </button>
                </form>
                <form action="FrontController" method="POST" style="display:inline;">
                        <input type="hidden" name="command" value="CHANGE_LANGUAGE_COMMAND">
                        <input type="hidden" name="locale" value="en">
                        <button id="butt" type="submit" style="cursor: pointer;width:8%;color:white;background-color:black;border:none;border-bottom:1px solid white;">
                        <c:out value="${en}" />
                        </button>
                </form>
           </div>
           <br><br>
        </div>
    </div>
    ${script}
    </body>
 </html>
