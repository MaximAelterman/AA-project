<%-- 
    Document   : bevestig
    Created on : 5-jan-2020, 21:24:05
    Author     : r0631
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.BigInteger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import ="beans.Momenten" %>
<% Momenten Res = (Momenten)session.getAttribute("Res");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservaties bevestigingspagina</title>
    </head>
    <body>
        <h1>Bevestig uw reservatie</h1>
        <% 
            double prijs = Res.getMnr().getHuurprijs().doubleValue();
            double duur = Res.getDuur().doubleValue();
            double totalprijs= prijs*duur;  
        %>
   
        <table>
            <tr><td>Naam: </td><td>${sessionScope.machine.getMnaam()}</td></tr>
            <tr><td>Serienummer: </td><td>${sessionScope.machine.getSerienr()}</td></tr>
            <tr><td>Locatie: </td><td>${sessionScope.machine.getMloc()}</td></tr>
            <tr><td>Datum: </td><td><fmt:formatDate value="${sessionScope.Res.getDatum()}" type="date" pattern="dd/MM/yyyy"></fmt:formatDate></td></tr>
            <tr><td>Starttijd: </td><td>${sessionScope.Res.getStrt()}u</td></tr>
            <tr><td>Duur: </td><td>${sessionScope.Res.getDuur()}u</td></tr>
            <c:choose>
                <c:when test="${groep == 'Extern'}">
                    <tr><td>Prijs: </td><td><%=totalprijs%> euro</td></tr>
                </c:when>
                <c:otherwise>
                    <tr><td>Prijs: </td><td>Gratis</td></tr>
                </c:otherwise>
            </c:choose>
            <br>
            <tr>
                <td>
                    <form method= "post" action=<c:url value="/controller.do"/> >
                        <input type="hidden" name="details" value="${machine.mnr}"/>
                        <input type="hidden" name="knop" value="Reserveer"/>
                        <input type="submit" name="terugknop" value="Annuleer"/>
                    </form>
                </td>
                <td>
                    <form method= "post" action=<c:url value="/controller.do"/> >
                        <input type="hidden" name="momid" value="${Res.momid}"/>
                        <input type="submit" name="knop" value="Reserveer moment"/>
                    </form>
                </td>
            </tr>
        </table>
    </body>
</html>
