<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 25.12.2021
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${spectacle.name} (${spectacle.age}+)</title>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<%@include file="header.jsp"%>
<h1>${spectacle.name}<span style="font-size: 0.7rem; vertical-align: top">(${spectacle.age}+)</span></h1>
<h2>Описание: ${empty spectacle.description ? "Не добавлено" : spectacle.description}</h2>
<h3>Продолжительность: ${spectacle.duration}</h3>
<h3>Жанр: ${spectacle.genre ? spectacle.genre : "Не определен"}</h3>
<h3>Рейтинг: ${spectacle.rating}</h3>
<hr>
<div style="text-align: center;">Сеансы</div>
<form method="get" action="/sessions">
    <table>
    <c:forEach items="${sessions}" var="session">
        <c:set var="id" value="${session.id}" />
        <tr>
            <th>${session.date}</th>
            <th>${session.time}</th>
            <th>
                <button name="session-id" value="${session.id}" type="submit">купить</button>
            </th>
        </tr>
    </c:forEach>
    </table>
</form>
</body>
</html>

<%! String check(String s) {
    return s.isEmpty() ? "" : s;
    }
%>
