<%-- 
    Document   : Reservatie
    Created on : 17-dec-2019, 22:15:22
    Author     : r0631
--%>

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
        <h1>Hier komt reservatie pagina</h1>
        <form method="post" action=<c:url value="/controller.do"/>>
            <input type="submit" name="knop" value="Overzicht"/>
        </form>
        <p> ik wil een reservatie maken met machine ${machine.mnr} met naam ${machine.mnaam}</p>
        <br>
        <h2>Gereserveerde momenten</h2>
        <table>
            <tr>
                <th>MomID</th>
                <th>start uur</th>
                <th>duurtijd</th>
                <th>Datum</th>
                <th>Gereserveerd door</th>
            </tr>
            <c:forEach var="resmom" items="${resmom}">
                <tr>
                    <td>${resmom.momid}</td>
                    <td>${resmom.strt}</td>
                    <td>${resmom.duur}</td>
                    <td>${resmom.datum}</td>
                    <c:forEach var="user" items="${user}">
                        <td>${user}</td>
                    </c:forEach>
                </tr>
            </c:forEach>

        </table>
        <br>
        <h2>Vrije momenten</h2>
        <table>
            <tr>
                <th>MomID</th>
                <th>start uur</th>
                <th>duurtijd</th>
                <th>Datum</th>
            </tr>
            <c:forEach var="vrijmom" items="${vrijmom}">
                <tr>
                    <td>${vrijmom.momid}</td>
                    <td>${vrijmom.strt}</td>
                    <td>${vrijmom.duur}</td>
                    <td>${vrijmom.datum}</td>
                </tr>
            </c:forEach>
        </table>
        
        
    </body>
</html>
<%@include file="footer.jsp"%>

