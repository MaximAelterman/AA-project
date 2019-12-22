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
    <body>
        <h1>Detailpagina van ${sessionScope.machine.mnaam}</h1>
        <h2>Omschrijving</h2>
        <p>${sessionScope.machine.omschrijving}</p>
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
        <form method="post" action="controller.do" >
            <input style="font-size:20px" type="submit" name="knop" value="Overzicht"/> 
        </form>
    </body>
</html>
