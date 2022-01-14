<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 26.12.2021
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Логин</title>
    <link rel="stylesheet" href="/css/login.css">
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<c:import url="header.jsp"/>
<form action="" method="post" >
    <input type="text" checked id="login">
    <input type="password" checked id="password">
    <input type="submit" id="check">
</form>
</body>
</html>
