<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>



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
    </head>
    <body id="body">

    <div id="maindiv">
        <div>
         <nav>
             <img src="img/icon.jpg" alt="icon" id="logo">
              <ul class="menu">
                  <li>
                      <a href="">Другие разделы</a>
                      <ul>
                          <li><a href="cars.jsp">Легковые автомобили</a></li>
                          <li><a href="truck.jsp">Грузовые автомобили</a></li>
                      </ul>
                  </li>
                  <li><a href="allCars.jsp">Автомобили</a></li>
                  <li id="account"><a href="account.jsp">${sessionScope.nameAccount} | ${sessionScope.emailAccount}</a></li>
                  <li style="margin-right: auto;"><a href="">
                        <form action="FrontController" method="get">
                            <input type="hidden" name="command" value="LOG_OFF_COMMAND">
                            <button>Выйти</button>
                        </form>
                  </a></li>
              </ul>
         </nav>

            <video pip="false" style="z-index: -1;" src="videos/gt63.mp4" autoplay loop width="100%" muted id="video"></video>
            <p id="gt63">Mercedes-Benz GT63</p>
            <p id="future">
                FUTURE IS OUR<br>LEGACY
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
                        <th><a class="ref" href="cars.jsp">Легковые автомобили</a></th>
                        <th style="font-size: 19px;"><a class="ref" href="minibus.jsp">Малотоннажные автомобили</a></th>
                        <th><a class="ref" href="truck.jsp">Грузовые автомобили</a></th>
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
                        <th><a class="ref" href="contacts.jsp">Контакты</a></th>
                        <th style="font-size: 19px;"><a class="ref" href="testDriveOrder.jsp">Заявка на тест-драйв</a></th>
                        <th><a class="ref" href="serviceOrder.jsp">Запись на сервис</a></th>
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
                            <p  style="color: white;">Автомобильный дом "Минск"</p>
                            <p  style="color: white;">Запчасти:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;">Сервис:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;">Автомобили:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                            <p  style="color: white;">Email:<span style="color: deepskyblue;">&nbsp;info@mercedes-benz.by</span></p>
                        </div>
                       </th>
                       <th style="width: 300px;text-align: left;vertical-align:top;">
                        <div style="margin-left: 10%;padding-top:0px;">
                            <p style="color: white;">Часы работы</p>
                            <p><span style="color: deepskyblue;" id="isOpened">Сейчас открыто</span></p>
                            <br>
                            <p  style="color: white;">Понедельник:&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Вторник:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Среда:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Четверг:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Пятница:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Суббота:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;08.00-20.00</p>
                            <p  style="color: white;">Воскресенье:&nbsp;&nbsp;08.00-17.00</p>
                        </div>
                       </th>
                       <th style="width: 300px;text-align: left;vertical-align:top;">
                        <div style="margin-left: 10%;color: white;">
                            Как нас найти
                            <p>ул.Тимирязева, 68</p>
                            <p>220035 Minsk</p>
                            <p>Тел:<span style="color: deepskyblue;">&nbsp;+375 17 3099999</span></p>
                        </div>
                       </th>
                   </tr>
               </table>
               <p style="margin-left: 9%;color: #A9A9A9;">
                ________________________________________________________________________________________________________________________________________
           </p>
           <p style="margin-left: 9%;">©Mercedes-Benz Беларусь, 2021</p>
        </span>
        </div>
    </div>
    <script src="js/index.js" type="text/javascript"></script>
    </body>
 </html>