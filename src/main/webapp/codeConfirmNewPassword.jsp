<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

 <!DOCTYPE html>
 <html>

 <head>
     <title>Forgot password</title>
     <meta charset="UTF-8">
     <link rel="stylesheet" href="css/codeConfirmNewPassword.css" type="text/css">
 </head>

 <body style="background-image: url(img/back.jpg);">
     <div id="mainDiv">
         <form method="get" action="FrontController">
             <br><br><br><br>
             <h1>Введите ваш email</h1>
             <br>
             <input name="email">
             <p style="color: red">${requestScope.error}</p>
             <br>
             <br><br>
             <input type="hidden" name="command" value="SEND_EMAIL_COMMAND">
             <button name="submit" type="submit">Далее</button>
             <br>
         </form>
         <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
     </div>
     <script src="js/index.js"></script>
 </body>
 </html>