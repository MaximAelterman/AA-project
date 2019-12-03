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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Overzichtspagina</h1>
        <h2>Welkom! U bent ingelogd als ${sessionScope.groep}.</h2>
        
        <div class="container">
            <table>
                <tr><th>Naam</th><th>Locatie</th><th>Opleiding</th><th></th></tr>
                <c:forEach var="machine" items="${applicationScope.machines}">
                    <form method= "post" action=<c:url value="/controller.do"/>>
                    <tr><td>${machine.mnaam}</td><td>${machine.mloc}</td><td>${machine.opleiding}</td><td><input type="submit" name="knop" value="Details"/><input type="hidden" name="details" value="${machine.mnr}"/></td></tr>
                    </form>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
