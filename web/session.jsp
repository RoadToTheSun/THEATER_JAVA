<%--
  Created by IntelliJ IDEA.
  User: Monster
  Date: 26.12.2021
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/css/index.css"/>
    <link rel="stylesheet" type="text/css" href="/css/session.css"/>
    <title>Сеанс № ${session.id}</title>
</head>
<body>
<c:import url="header.jsp"/>
<div style="text-align: center">
    <h2><a href="/spectacles/${session.spectacleName}">${session.spectacleName}&nbsp;</a></h2>
    ${session.getDay()}.${session.getMonth()}.${session.getYear()}&nbsp;(${session.time})
    <table class="tickets-table">
        <%@page import="Theater.Model.Theater" %>
        <c:set var="spot" value="0"/>
        <c:forEach begin="1" end="${Theater.SPOTS_NUMBER/5}" varStatus="row">
            <tr>
                <c:forEach begin="1" end="5" varStatus="col">
                    <c:set var="spot" value="${spot+1}"/>
                    <th>
                        <div class="dropdown">

                            <c:set var="available" value="0"/>
                            <c:set var="taken" value="0"/>

                            <c:forEach items="${tickets}" var="ticket">
                                <c:if test="${ticket.spot==spot}">
<%--                                    <c:set var="ticketNumber" value="${ticket.number}"/>--%>
                                    <c:set var="href" value="/tickets/${ticket.number}?session-id=${session.id}"/>
                                    <form name="ticket-alter-form" id="ticket-alter-form" action="${href}&" method="post">
                                    <c:if test="${ticket.customerID<1}">
                                        <c:set var="available" value="1"/>

                                        <%--                                <c:set var="sessionId" value="${session.id}" scope="request"/>--%>
                                        <div class="spot">${ticket.getCurrentPrice().price}</div>
                                        <div class="dropdown-menu">
<%--                                            <a href="${href}&delete=1"></a>--%>
                                            <a href="${href}">Купить</a>
                                            <button type="submit" <%--form="ticket-alter-form"--%> name="update" value="${ticket.number}">Изменить</button>
                                            <button type="submit" <%--form="ticket-alter-form"--%> name="delete" value="${ticket.number}">Удалить</button>
                                        </div>
                                    </c:if>

                                    <c:if test="${ticket.customerID>=1}">
                                        <c:set var="taken" value="1"/>
                                    </c:if>

                                    <c:if test="${taken==1}">
                                        <div class="spot taken-spot">Занято</div>
                                        <div class="dropdown-menu">
                                            <button type="submit" <%--form="ticket-alter-form"--%> name="update" value="${ticket.number}">Изменить</button>
                                            <button type="submit" <%--form="ticket-alter-form"--%> name="delete" value="${ticket.number}">Удалить</button>
                                        </div>
                                    </c:if>
                                    </form>
                                </c:if>
                            </c:forEach>
<%--                            <c:if test="${available==1}">--%>

                            <c:if test="${available==0 && taken==0}">
                                <div class="spot">&mdash;</div>
                                <div class="dropdown-menu">
                                    <button type="submit" form="ticket-alter-form" name="insert" value="${spot}">Добавить</button>
                                </div>
                            </c:if>
<%--                            </c:if>--%>
                        </div>
                    </th>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
