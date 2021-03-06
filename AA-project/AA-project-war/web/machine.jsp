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
        <title>Machine toevoegen</title>
    </head>
    <body>
        <h1>Nieuwe machine</h1>
        <h2>Gelieve volgende gegevens op te geven.</h2>
        
        <div class="container">
            <c:if test="${groep == 'Docent'}">
            <form method="post" name="nieuwemachine" action=<c:url value="/controller.do"/>>
                <table>
                    <tr><td>Naam:</td><td><input type="text" name="naam" required/></td></tr>
                    <tr><td>Serienummer:</td><td><input type="text" name="serienr" required/></td></tr>
                    <tr><td>Locatie:</td><td><input type="text" name="locatie" required/></td></tr>
                    <tr><td>Opleiding:</td><td><input type="text" name="opleiding" value = "${opleiding}" readonly/></td></tr>
                    <tr><td>Aankoopprijs:</td><td><input type="text" name="aankoopprijs" required/></td></tr>
                    <tr><td>Huurprijs(1u):</td><td><input type="text" name="huurprijs" required/></td></tr>
                    <tr><td>Omschrijving:</td><td><textarea cols="40" rows="6" name="omschrijving" required></textarea></td></tr>
                    <tr>
                        <td><input type="submit" name="knop" value="Annuleer" formnovalidate/></td>
                        <td><input type="submit" name="knop" value="Machine toevoegen"/></td>
                    </tr>
                </table>
            </form>
            </c:if>
        </div>
    </body>
</html>
