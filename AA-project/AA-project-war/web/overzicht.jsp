<%-- 
    Document   : overzicht
    Created on : 13-nov-2019, 16:10:49
    Author     : r0631103
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overzicht Page</title>
    </head>
    <style>
        td,th {
            padding: 6px;
        }
        table{
            padding-top:10px;
        }
        .container{
            width: auto;
            max-width: 640px;
        }
    </style>
    <body>
        <div class="container">
            <div style="float:right">
            <%@include file="footer.jsp"%>
            </div>
            
            <h1>Overzichtspagina</h1>
            <h2>Welkom ${sessionScope.gebruiker}! </h2>
            <h3>U bent ingelogd als ${sessionScope.groep}.</h3>

        
            <table>
                <tr>
                    <th>Naam</th>
                    <th>Locatie</th>
                    <th>Opleiding</th>
                    <th>Korte omschrijving</th>
                </tr>
                <c:forEach var="machine" items="${machines}">
                    <form method= "post" action=<c:url value="/controller.do"/> >
                        <tr>
                            <td>${machine.mnaam}</td>
                            <td>${machine.mloc}</td>
                            <td>${machine.opleiding}</td>
                            <td>${machine.omschrijving}</td>
                            <input type="hidden" name="details" value="${machine.mnr}"/>
                            <td>
                                <c:if test="${groep != 'extern'}">
                                <input type="submit" name="knop" value="Details"/>
                                </c:if>
                            </td>
                            <td>
                                <input type="submit" name="knop" value="Reserveer"/>
                            </td>
                            <c:if test="${sessionScope.groep == 'Docent' && machine.opleiding == sessionScope.opleiding}">
                                <td>
                                    <input type="submit" name="knop" value="Wijzig"/>
                                </td>
                            </c:if>
                        </tr>
                    </form>
                </c:forEach>
            </table>
            <div style="padding-top: 15px">
            <c:if test="${sessionScope.groep == 'Docent'}">
                <form method= "post" action=<c:url value="/controller.do"/>>
                   <input type="submit" name="knop" value="Nieuwe machine"/>
                </form>
            </c:if>
            </div>
        </div>
    </body>
</html>

