<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

 <html>

 <head>
     <title>Forgot password</title>
     <meta charset="UTF-8">
     <link rel="stylesheet" href="css/codeConfirmNewPassword.css" type="text/css">
     <fmt:setLocale value="${sessionScope.locale}" />
     <fmt:setBundle basename="localization.local" var="loc" />
     <fmt:message bundle="${loc}" key="local.enterEmail" var="enterEmail" />
     <fmt:message bundle="${loc}" key="local.continue" var="continue" />
 </head>

 <body style="background-image: url(img/back.jpg);">
     <div id="mainDiv">
         <form method="get" action="FrontController">
             <br><br><br><br>
             <h1><c:out value="${enterEmail}" /></h1>
             <br>
             <input name="email">
             <p style="color: red">${requestScope.error}</p>
             <br>
             <br><br>
             <input type="hidden" name="command" value="SEND_EMAIL_COMMAND">
             <button name="submit" type="submit"><c:out value="${continue}" /></button>
             <br>
         </form>
         <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
     </div>
     <script src="js/index.js"></script>
 </body>
 </html>