<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 29.12.2021
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить билет на ${session.id} сеанс</title>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<form action="" method="post">
<table>
    <thead>
    <tr>
        <th>Сеанс</th>
        <th>Место</th>
        <th>Цена</th>
        <th>Покупатель</th>
    </tr>
    </thead>
    <tr>
        <th><input type="text" name="session-id" placeholder="${session-id}" value="${session-id}" disabled></th>
        <th><input type="text" name="spot" placeholder="${spot}" value="${spot}" disabled></th>
        <th><input type="text" name="price" placeholder="300"></th>
        <th>
            <select name="customer" id="customer">
                <option name="customer-id" value="null">null</option>
            <c:forEach items="${customers}" var="customer">
                <option name="customer-id" value="${customer.id}">${customer.id}</option>
            </c:forEach>
            </select>
        </th>
    </tr>
</table>
<p style="text-align: center"><button name="save" value="1">Сохранить</button></p>
</form>
</body>
</html>
