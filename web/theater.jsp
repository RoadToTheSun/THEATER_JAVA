<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 25.12.2021
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Theater Home</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
<%--<header>--%>
<%--    <div><a href="${pageContext.request.contextPath}/">ГЛАВНАЯ</a></div>--%>
<%--    <div><a href="${pageContext.request.contextPath}/theater">${name}♛THEATER</a></div>--%>
<%--    <div><a href="${pageContext.request.contextPath}/spectacles/">СПЕКТАКЛИ</a></div>--%>
<%--    <div><a href="${pageContext.request.contextPath}/login">ВОЙТИ</a></div>--%>
<%--</header>--%>
<c:import url="header.jsp"/>
<table class="spectacles">
    <thead>
    <tr>
        <th hidden>Id</th>
        <th>Дата</th>
        <th>Спектакль</th>
        <th>Время</th>
        <th>Редактирование</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${playbill}" var="session">
        <tr class="session-row">
            <th hidden>${session.id}</th>
            <th>${session.getDay()}.${session.getMonth()}.${session.getYear()}</th>
            <th><a href="/spectacles/${session.spectacleName}">${session.spectacleName}</a></th>
            <th>${session.time}</th>
            <th>
                <div class="dropdown">
                    <div>Наведите</div>
                    <div class="dropdown-menu">
                        <c:set var='update' value='${session.id}' scope='request'/>
                        <a href="#">Изменить</a>
                        <a href="#">Удалить</a>
                    </div>
                </div>
            </th>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%--<label for="sort">Сортировать по</label>--%>
<%--<select name="sort" id="sort">--%>
<%--    <c:forEach begin="1" end="4" varStatus="counter">--%>
<%--        <option value="${counter.count}">${counter.current}</option>--%>
<%--    </c:forEach>--%>
<%--</select>--%>
<%--<label for="sort">столбцу</label>--%>
<h6>for test...</h6>
</body>
</html>
