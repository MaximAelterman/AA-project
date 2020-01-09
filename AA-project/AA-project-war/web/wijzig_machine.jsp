<%-- 
    Document   : wijzig_machine
    Created on : 15-dec-2019, 16:10:49
    Author     : Max
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
    <style>
        <%@include file="styles.css"%>
    </style>
    <body>
        <h1>Wijzig machine</h1>
        <h2>Gelieve volgende gegevens op te geven.</h2>
        
        <div class="container">
            <c:if test="${groep == 'Docent'}">
            <form method="post" name="wijzigmachine" action=<c:url value="/controller.do"/>>
                <table>
                    <tr><td>Naam</td><td><input type="text" name="naam" value="${machine.mnaam}" required/></td></tr>
                    <tr><td>Serienummer</td><td><input type="text" name="serienr" value="${machine.serienr}" required/></td></tr>
                    <tr><td>Locatie:</td><td><input type="text" name="locatie" value="${machine.mloc}" required/></td></tr>
                    <tr><td>Opleiding:</td><td><input type="text" name="opleiding" value="${machine.opleiding}" readonly/></td></tr>
                    <tr><td>Aankoopprijs:</td><td><input type="text" name="aankoopprijs" value="${machine.aankoopprijs}" required/></td></tr>
                    <tr><td>Huurprijs(1u):</td><td><input type="text" name="huurprijs" value="${machine.huurprijs}" required/></td></tr>
                    <tr><td>Omschrijving:</td><td><textarea cols="40" rows="6" name="omschrijving" required>${machine.omschrijving}</textarea></td></tr>
                    <tr>
                        <td class="knoppenbalk">
                            <input type="hidden" name="mnr" value="${machine.mnr}"/>
                            <input type="submit" name="knop" value="Wijzigingen opslaan"/>
                        </td>
                        <td class="knoppenbalk">
                            <input type="submit" name="knop" value="Annuleer" formnovalidate/>
                        </td>
                    </tr>
                </table>   
            </form>
            </c:if>
        </div>
    </body>
</html>
