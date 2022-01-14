<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 27.12.2021
  Time: 1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Билет № ${ticket.number}</title>
    <link rel="stylesheet" href="/css/session.css">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<c:import url="header.jsp"/>
<h2 style="text-align: center">Билет № ${ticket.number} на сеанс ${session.id}</h2>
<table>
    <thead>
        <tr>
            <th>Номер</th>
            <th>Описание</th>
            <th>Место</th>
            <th>Цена</th>
            <th>Купить</th>
        </tr>
    </thead>
    <tr>
        <th>${ticket.number}</th>
        <th>
            Спектакль: ${session.spectacleName}<br>
            Дата: ${session.date}<br>
            Время: ${session.time}<br>
            Сеанс: ${session.id}<br>
        </th>
        <th>${ticket.spot}</th>
        <th>${ticket.getCurrentPrice().price}</th>
        <th><input type="checkbox" name="number" value="${ticket.number}"></th>
    </tr>
</table>
<c:import url="booking_form.jsp"/>
</body>
</html>
