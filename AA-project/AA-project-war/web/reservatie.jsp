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
        th, td{
            border: 1px solid black;
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
            </tr>
            <c:forEach var="resmom" items="${List<Momenten>resmom}">
                <tr>
                    <td>${resmom.momid}</td>
                    <td>${resmom.strt}</td>
                    <td>${resmom.duur}</td>
                    <td>${resmom.datum}</td>
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
            <c:forEach var="Vrijmom" items="${vrijMom}">
                <tr>
                    <td>${VrijMom.momid}</td>
                    <td>${VrijMom.strt}</td>
                    <td>${VrijMom.duur}</td>
                    <td>${VrijMom.datum}</td>
                </tr>
            </c:forEach>
        </table>
        
        
    </body>
</html>
<%@include file="footer.jsp"%>

