<%-- 
    Document   : details
    Created on : 13-nov-2019, 16:10:49
    Author     : Max
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detailpagina</title>
    </head>
    <style>
        td,th {
            padding: 6px;
        }
        table,tr,td, th { border: 1px solid black;}
    </style>
    <body>
        <h1>Detailpagina van ${machine.mnaam}</h1>
        <h2>Omschrijving</h2>
        <p>${machine.omschrijving}</p>
        <h2>Details</h2>
        <table>
            <tr><td>Machinenummer:</td><td>${machine.mnr}</td></tr>
            <tr><td>Serienummer: </td><td>${machine.serienr}</td></tr>
            <tr><td>Locatie:</td><td>${machine.mloc}</td></tr>
            <tr><td>Opleiding:</td><td>${machine.opleiding}</td></tr>
            <tr><td>Aankoopprijs:</td><td>€ ${machine.aankoopprijs}</td></tr>
            <tr><td>Huurprijs(1u):</td><td>€ ${machine.huurprijs}</td></tr>
        </table>
        </br>
        <form method="post" action=<c:url value="/controller.do"/>>
            <input type="hidden" name="details" value="${machine.mnr}"/>

            <c:if test="${groep == 'Docent' && machine.opleiding == opleiding}">
                <input type="submit" name="knop" value="Wijzig"/></td>
            </c:if>
            <input type="submit" name="knop" value="Reserveer"/>
            
            <input style="font-size:20px" type="submit" name="knop" value="Overzicht"/>
            
            <c:if test="${groep == 'Docent'}">
                 <input type="submit" name="knop"  value="MachineMoment" />
            </c:if>
                 
            <c:if test="${groep == 'Docent'}">
                <br/>
                <h2> Moment toevoegen:</h2>
                <p>
                 Startuur:<input type="text" name="start" value="" />
                 Duurtijd<input type="text" name="duur" value="" />
                 datum:<input type="date" name="datum" value="" />
                 <input type="submit" name="knop"  value="Moment toevoegen" />
                </p>
            </c:if>   
        </form>
         <c:if test="${test == 'oke'}">
             <h2> Machine Momenten:</h2>
             <br/>
             <table>
             <tr>
                <th>moment ID</th>
                <th>start tijd</th>
                <th>duur</th>
                <th>datum</th>
            </tr>
                <c:forEach var="moment" items="${machinemom}">
                <tr>
                    <td>${moment.momid}</td>
                    <td>${moment.strt}</td>
                    <td>${moment.duur}</td>
                    <td>${moment.datum}</td>
                </tr>
                </c:forEach>
             </table>
            </c:if>  
    </body>
</html>
