<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <title>Add Product</title>
        <meta charset="UTF-8">
    </head>
    <body>
        <h1 style="margin-left:37%;">Добавление товаров</h1>
        <div>
            <h3>Добавление легкового автомобиля</h3>
            <form action="FrontController" method = "get">
                <input type="hidden" name="command" value="ADD_CAR_COMMAND">
                <input type="text" name="mark" placeholder="Марка">
                <input type="text" name="price" placeholder="Цена">
                <input type="text" name="power" placeholder="Мощность">
                <input type="text" name="acceleration" placeholder="Разгон">
                <input type="text" name="consumption" placeholder="Расход">
                <input type="text" name="engineVolume" placeholder="Объем двигателя">
                <input type="text" name="tankVolume" placeholder="Объем бака">
                <input type="text" name="trunkVolume" placeholder="Объем багажника">
                <input type="text" name="maxSpeed" placeholder="Макс.скорость">
                <input type="text" name="image" placeholder="Путь к картинке">
                <input type="text" name="type" placeholder="Тип">
                <button>
                    Добавить легковую машину
                </button>
            </form>
        </div>
        <div>
            <h3>Добавление малотоннажного автомобиля</h3>
            <form action="FrontController" method = "get">
                <input type="hidden" name="command" value="ADD_MINIBUS_COMMAND">
                <input type="text" name="mark" placeholder="Марка">
                <input type="text" name="price" placeholder="Цена">
                <input type="text" name="image" placeholder="Путь к картинке">
                <input type="text" name="load" placeholder="Полная загрузка">
                <input type="text" name="weight" placeholder="Снаряженная масса">
                <button>
                    Добавить малотоннажную машину
                </button>
            </form>
        </div>
    </body>
</html>