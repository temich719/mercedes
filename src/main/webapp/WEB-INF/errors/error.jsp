<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>ERROR PAGE</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1>
            ERROR!
        </h1>
        <br>
        <h3>
            <span style="color:red;">${requestScope.error}</span>
        </h3>
    </body>
</html>