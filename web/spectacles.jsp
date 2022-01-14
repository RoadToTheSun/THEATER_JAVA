<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Spectacles</title>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<%--<%@include file="header.jsp"%>--%>
<c:import url="header.jsp"/>
<table class="spectacles">
    <thead>
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Продолжительность</th>
        <th>Жанр</th>
        <th>Возраст</th>
        <th>Рейтинг</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${playbill}" var="spectacle">
        <tr>
            <th><a href="/spectacles/${spectacle.name}">${spectacle.name}</a></th>
            <th>${spectacle.description}</th>
            <th>${spectacle.duration} ч</th>
            <th>${spectacle.genre}</th>
            <th>${spectacle.age}</th>
            <th>${spectacle.rating}</th>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
