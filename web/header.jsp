<%@ page import="Theater.Model.Theater" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<link rel="stylesheet" href="css/index.css">--%>
<%--<style>
    header {
        height: 50px;
        display: flex;
        flex-wrap: wrap;
        align-items: stretch;
        align-content: space-evenly;
        justify-content: space-evenly;
        flex-direction: row;
        box-shadow: -2px 2px 18px 0 grey;
    }
</style>--%>
<header>
    <div><a href="${pageContext.request.contextPath}/">ГЛАВНАЯ</a></div>
<%--    <div><a href="${pageContext.request.contextPath}/theater"><%=Theater.NAME%>♛THEATER</a></div>--%>
    <div><a href="${pageContext.request.contextPath}/theater"><c:out value="${Theater.NAME}"/>♛THEATER</a></div>
    <div><a href="${pageContext.request.contextPath}/spectacles/">СПЕКТАКЛИ</a></div>
    <div><a href="${pageContext.request.contextPath}/login">ВОЙТИ</a></div>
</header>
