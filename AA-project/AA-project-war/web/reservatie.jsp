<%-- 
    Document   : Reservatie
    Created on : 17-dec-2019, 22:15:22
    Author     : r0631
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservatie Page</title>
    </head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
        }
        td, th {
            border: 1px solid black;
            text-align: center;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
    <body>
        <jsp:useBean id="date" class="java.util.Date"/>
        <h1>Reservatie van ${machine.mnaam}</h1>
        <form method="post" action=<c:url value="/controller.do"/>>
            <input type="submit" name="knop" value="Overzicht"/>
        </form>
        <c:if test="${sessionScope.groep == 'Docent'}">
        <h2>Gereserveerde momenten</h2>
        <table>
            <tr>
                <th>MomID</th>
                <th>Startuur</th>
                <th>Duurtijd</th>
                <th>Datum</th>
                <th>Gereserveerd door</th>
            </tr>
            <c:set var="i" scope="session" value="0"/>
            <c:forEach var="resmom" items="${resmom}">
                <tr>
                    <td>${resmom.momid}</td>
                    <td>${resmom.strt}</td>
                    <td>${resmom.duur}</td>
                    <td><fmt:formatDate value="${resmom.datum}" type="date" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                    <td>${user[i]}</td>
                    <c:set var="i" scope="session" value="${i+1}"/>
                </tr>
            </c:forEach>
        </table>
        <br>
        </c:if>
        <h2>Vrije momenten</h2>
        <table>
            <tr>
                <th>MomID</th>
                <th>Startuur</th>
                <th>Duurtijd</th>
                <th>Datum</th>
            </tr>
            <c:forEach var="vrijmom" items="${vrijmom}">
                <form method="post" action=<c:url value="/controller.do"/>>
                <tr>
                    <td>${vrijmom.momid}</td>
                    <td>${vrijmom.strt}</td>
                    <td>${vrijmom.duur}</td>
                    <td><fmt:formatDate value="${vrijmom.datum}" type="date" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                    <td><input type="submit" name="knop" value="Reserveer machine"/></td>
                    <input type="hidden" name="momid" value="${vrijmom.momid}"/>
                </tr>
                </form>
            </c:forEach>
        </table>
        
        
    </body>
</html>
<%@include file="footer.jsp"%>

